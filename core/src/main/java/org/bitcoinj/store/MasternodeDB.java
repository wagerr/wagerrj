package org.bitcoinj.store;

/**
 * Created by Hash Engineering on 2/26/2016.
 */

/** Access to the MN database (mncache.dat)
 */

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.bitcoinj.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class MasternodeDB {
    private static final Logger log = LoggerFactory.getLogger(MasternodeDB.class);
    private static String pathMN;
    private final static String strMagicMessage = "MasternodeCache";
    public enum ReadResult {
        Ok,
        FileError,
        HashReadError,
        IncorrectHash,
        IncorrectMagicMessage,
        IncorrectMagicNumber,
        IncorrectFormat,
        NoResult
    }

    ReadResult lastReadResult = ReadResult.NoResult;

    MasternodeDB(String directory) {
        pathMN = directory + "mncache.dat";
    }
    boolean Write(MasternodeManager mnodemanToSave) {

        try {
            long nStart = Utils.currentTimeMillis();

            // serialize, checksum data up to that point, then append checksum
            UnsafeByteArrayOutputStream stream = new UnsafeByteArrayOutputStream(strMagicMessage.length()+4+mnodemanToSave.getMessageSize());
            stream.write(strMagicMessage.getBytes());
            Utils.uint32ToByteStreamLE(mnodemanToSave.getParams().getPacketMagic(), stream);
            mnodemanToSave.bitcoinSerialize(stream);

            Sha256Hash hash = Sha256Hash.twiceOf(stream.toByteArray());


            // open output file, and associate with CAutoFile

            FileOutputStream fileStream = new FileOutputStream(pathMN);

            //FILE * file = fopen(pathMN.string().c_str(), "wb");
            //CAutoFile fileout (file, SER_DISK, CLIENT_VERSION);
            //if (fileout.IsNull())
            //    return error("%s : Failed to open file %s", __func__, pathMN.string());


            // Write and commit header, data
            fileStream.write(stream.toByteArray());
            fileStream.write(hash.getBytes());



//    FileCommit(fileout);
            fileStream.close();
            //fileout.fclose();

            log.info("Written info to mncache.dat  %dms\n", Utils.currentTimeMillis() - nStart);
            log.info("  %s\n", mnodemanToSave.toString());

            return true;

        }
        catch(IOException x)
        {
            return false;
        }
    }

    MasternodeManager Read(NetworkParameters params, boolean fDryRun) {

        long nStart = Utils.currentTimeMillis();
        MasternodeManager manager = null;
        try {
            // open input file, and associate with CAutoFile

            FileInputStream fileStream = new FileInputStream(pathMN);

            File file = new File(pathMN);

            /*FILE * file = fopen(pathMN.string().c_str(), "rb");
            CAutoFile filein (file, SER_DISK, CLIENT_VERSION);
            if (filein.IsNull()) {
                error("%s : Failed to open file %s", __func__, pathMN.string());
                return FileError;
            }*/

            // use file size to size memory buffer

            long fileSize = file.length();//fileStream.boost::filesystem::file_size(pathMN);
            long dataSize = fileSize - 32;
            // Don't try to resize to a negative number if file is small
            if (dataSize < 0)
                dataSize = 0;

            //vector<unsigned char>vchData;
            //vchData.resize(dataSize);
            byte [] hashIn = new byte[32];
            byte [] vchData = new byte[(int)dataSize];

            try {
                fileStream.read(vchData);
                fileStream.read(hashIn);
            } catch (IOException x)
            {
                lastReadResult =  ReadResult.HashReadError;
                return null;
            }
            fileStream.close();



            //CDataStream ssMasternodes (vchData, SER_DISK, CLIENT_VERSION);



            // verify stored checksum matches input data
            Sha256Hash hashTmp = Sha256Hash.twiceOf(vchData);
            if (!hashIn.equals(hashTmp)) {
                log.error("Checksum mismatch, data corrupted");
                lastReadResult = ReadResult.IncorrectHash;
                return null;
            }

            int pchMsgTmp;
            String strMagicMessageTmp;
            try {
                // de-serialize file header (masternode cache file specific magic message) and ..


                strMagicMessageTmp = new String(vchData, 0, strMagicMessage.length());

                // ... verify the message matches predefined one
                if (strMagicMessage != strMagicMessageTmp) {
                    log.error("Invalid masternode cache magic message");
                    lastReadResult = ReadResult.IncorrectMagicMessage;
                    return null;
                }

                // de-serialize file header (network specific magic number) and ..
                //ssMasternodes >> FLATDATA(pchMsgTmp);
                pchMsgTmp = (int)Utils.readUint32(vchData, strMagicMessage.length());

                // ... verify the network matches ours
                if (pchMsgTmp == params.getPacketMagic()) {
                    log.error("Invalid network magic number");
                    lastReadResult = ReadResult.IncorrectMagicNumber;
                    return null;
                }
                // de-serialize data into CMasternodeMan object

                manager = new MasternodeManager(params, vchData, strMagicMessage.length()+ 4);

            } catch (Exception e){
                //mnodemanToLoad.Clear();
                log.error("Deserialize or I/O error - {}",  e.getMessage());
                lastReadResult = ReadResult.IncorrectFormat;
                return null;
            }

            log.info("Loaded info from mncache.dat  {}ms", Utils.currentTimeMillis() - nStart);
            log.info("  {}", manager.toString());
            if (!fDryRun) {
                log.info("Masternode manager - cleaning....");
                manager.checkAndRemove(true);
                log.info("Masternode manager - result:");
                log.info("  {}", manager.toString());
            }

            lastReadResult = ReadResult.Ok;
            return manager;
        }
        catch(IOException x) {
            lastReadResult = ReadResult.FileError;
            return null;
        }
    }
        MasternodeManager Read(NetworkParameters params) {
            return Read(params, false);
        }
};

