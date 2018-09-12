package org.wagerrj.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hash Engineering Solutions
 * Date: 5/3/14
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "Wagerr";
    public static final String coinTicker = "WGR";
    public static final String coinURIScheme = "wagerr"; // guiutil.cpp
    public static final String cryptsyMarketId = "155";
    public static final String cryptsyMarketCurrency = "WGR";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;

    public static final String UNSPENT_API_URL = "https://chainz.cryptoid.info/wgr/api.dws?q=unspent";
    public enum UnspentAPIType {
        BitEasy,
        Blockr,
        Abe,
        Cryptoid,
    };
    public static final UnspentAPIType UnspentAPI = UnspentAPIType.Cryptoid;

    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://explorer.dash.org/";    //blockr.io
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://test.explorer.dash.org/";

    public static final String DONATION_ADDRESS = "Xdeh9YTLNtci5zSL4DDayRSVTLf299n9jv";  //Hash Engineering donation DASH address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };
    public static final CoinHash coinPOWHash = CoinHash.x11;

    public static boolean checkpointFileSupport = true;

    public static final int TARGET_TIMESPAN = (int)(1 * 60);  //chainparams.cpp 60 seconds per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 60);  // 60 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;    //72 min
    }

    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    public static final long MAX_COINS = 398360470; //chainparams.cpp:  nMaxMoneyOut  Max. Money is calculated by 2x premine value


    public static final long DEFAULT_MIN_TX_FEE = 100000;   // wallet.cpp CFeeRate CWallet::minTxFee = CFeeRate(10000);
    public static final long DUST_LIMIT = 54600; //transaction.h IsDust 5460 uwgr
    public static final long INSTANTX_FEE = 100000; //0.001 DASH (updated for 12.1)
    public static final boolean feeCanBeRaised = false;

    //
    // wagerr 3.1.0.2
    //
    public static final int PROTOCOL_VERSION = 70922;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 70922;        //version.h MIN_PEER_PROTO_VERSION_BEFORE_ENFORCEMENT

    public static final int BLOCK_CURRENTVERSION = 2;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000; //block.h MAX_BLOCK_SIZE_LEGACY


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 55002;       //chainparams.cpp CMainParams nDefaultPort
    public static final int TestPort = 55004;     //chainparams.cpp CTestNetParams nDefaultPort

    /** Zerocoin starting block height */
    public static final long TESTNET_ZEROCOIN_STARTING_BLOCK_HEIGHT = 1;//main.cpp WagerrTor -  our blocks are in version 4 since block 1
    public static final long MAINNET_ZEROCOIN_STARTING_BLOCK_HEIGHT = 1;

    public static final int BIP_44_COIN_TYPE = 7825266;  //776772 https://github.com/satoshilabs/slips/blob/master/slip-0044.md

    //
    //  Production
    //
    public static final int AddressHeader = 73;             // chainparams.cpp PUBKEY_ADDRESS Wagerr addresses start with 'W'
    public static final int p2shHeader = 63;             //chainparams.cpp SCRIPT_ADDRESS  Wagerr script addresses start with '7'
    public static final int privateKeyHeader = 199;     //chainparams.cpp SECRET_KEY
    public static final long PacketMagic = 0x842d61fd;//chainparams.cpp

    //Genesis Block Information from chainparams.cpp
    static public long genesisBlockDifficultyTarget = 0x1e0ffff0;          // chainparams.cpp genesis.nBits
    static public long genesisBlockTime = 1518696181L;                       //chainparams.cpp  genesis.nTime
    static public long genesisBlockNonce = 96620932;                         //chainparams.cpp   genesis.nNonce
    static public String genesisHash = "000007b9191bc7a17bfb6cedf96a8dacebb5730b498361bf26d44a9f9dcc1079";  // chainparams.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "c4d06cf72583752c23b819fa8d8cededd1dad5733d413ea1f123f98a7db6af13"; // chainparams.cpp hashMerkleRoot
    static public int genesisBlockValue = 0;                                                              // chainparams.cpp: txNew.vout[0].nValue
    //chainparams.cpp scriptPubKey
    public static final String genesisTxPubKey = "046013426db3d877adca7cea18ebeca33e88fafc53ab4040e0fe1bd0429712178c10571dfed6b3f1f19bcff0805cdf1c798e7a84ef0f5e0f4459aabd7e94ced9e6";

    //chainparams.cpp vSeeds
    static public String[] dnsSeeds = new String[] {
            "main.seederv1.wgr.host",
            "main.seederv2.wgr.host",
            "main.devseeder1.wgr.host",
            "main.devseeder2.wgr.host"
    };

    public static int minBroadcastConnections = 3;   //0 for default; we need more peers.

    //
    // TestNet - DASH
    //
    public static final boolean supportsTestNet = true;
    public static final int testnetAddressHeader = 65;             //chainparams.cpp
    public static final int testnetp2shHeader = 125;             //chainparams.cpp
    public static final int testnetPrivateKeyHeader = 177;             //chainparams.cpp
    public static final long testnetPacketMagic = 0x879ed199;      //

    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0);          // chainparams.cpp genesis.nBits
    static public long testnetGenesisBlockTime = 1518696182L;                         //chainparams.cpp  genesis.nTime
    static public long testnetGenesisBlockNonce = (75183976);                         //chainparams.cpp   genesis.nNonce
    public static final String testnetGenesisHash =  "00000fdc268f54ff1368703792dc046b1356e60914c2b5b6348032144bcb2de5";





    //main.cpp GetBlockValue(height, fee)
    public static final Coin GetBlockReward(int height)
    {
        Coin nSubsidy;
        if (height == 0) {
            // Genesis block
            nSubsidy = Coin.valueOf(0, 0);
        } else if (height == 1) {
            /* PREMINE: Current available wagerr on DEX marketc 198360471 wagerr
            Info abobut premine:
            Full premine size is 198360471. First 100 blocks mine 250000 wagerr per block - 198360471 - (100 * 250000) = 173360471
            */
            // 87.4 % of premine
            nSubsidy = Coin.valueOf(173360471, 0);
        } else if (height > 1 && height <= 101 && height <= 1001) { // check for last PoW block is not required, it does not harm to leave it *** TODO ***
            // PoW Phase 1 does produce 12.6 % of full premine (25000000 WGR)
            nSubsidy = Coin.valueOf(250000, 0);
        } else if (height > 1 && height > 101 && height <= 1001) {
            // PoW Phase does not produce any coins
            nSubsidy =Coin.valueOf(0, 0);
        } else if (height > 1001 && height <= 10000) {
            // PoS - Phase 1 lasts until block 1110)
            nSubsidy =Coin.valueOf(0, 0);
        } else if (height > 1001 && height > 10000) {
            // PoS - Phase 2 lasts until - undefined)
            nSubsidy = Coin.valueOf(3, 80);
        } else {
            nSubsidy = Coin.valueOf(0, 0);
        }
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 4730400;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //chainparams.cpp bnProofOfWorkLimit Wagerr starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
            "test.testseederv1.wgr.host",
            "test.testseederv2.wgr.host",
            "test.devseeder1.wgr.host",
            "test.devseeder2.wgr.host"
    };
    //from chainparams.cpp: CAlert::vAlertPubKey
    public static final String SATOSHI_KEY = "043cb569d89fb78fc61df67617012e6c33c1ba306f4620bbb89424279a4931adf4a9e238db60aa7f78cd10ef780f21f1fd3b881f014fd0f656db4b6a6a98f0cff2";
    public static final String TESTNET_SATOSHI_KEY = "0466223434350e5754c7379008e82954820a4bcc17335c42b915a0223c486e8bbbf87ba6281777d19ec73dc0b43416b33df432e3f4685770e56f9688afec7c2e3c";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "com.wagerr.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "com.wagerr.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.wagerr.unittest";
    public static final String ID_REGTEST = "com.wagerr.regtest";

    //chainparams.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put(     1, Sha256Hash.wrap("000001364c4ed20f1b240810b5aa91fee23ae9b64b6e746b594b611cf6d8c87b"));
        checkpoints.put(   101, Sha256Hash.wrap("0000005e89a1fab52bf996e7eb7d653962a0eb064c16c09887504797deb7feaf"));
        checkpoints.put(  1001, Sha256Hash.wrap("0000002a314058a8f61293e18ddbef5664a2097ac0178005f593444549dd5b8c"));
        checkpoints.put(  4559, Sha256Hash.wrap("f26ac8c87c21ab26e34340c54b2a16c0f1b1c1cce2202e0eb6e79ca9579e2022"));
        checkpoints.put(  5530, Sha256Hash.wrap("b3a8e6eb90085394c1af916d5690fd5b83d53c43cf60c7b6dd1e904e0ede8e88"));
        checkpoints.put(  6160, Sha256Hash.wrap("7e7e688ae130d6b0bdfd3f059c6be93ba5e59c0bc28eb13f09e8158092151ad4"));
        checkpoints.put( 12588, Sha256Hash.wrap("d9d1da49888f0d6febbdb02f79dde7fdc20d72a20e6f0d672e9543085cb70ca7"));
        checkpoints.put( 14374, Sha256Hash.wrap("61dc2dbb225de3146bc59ab96dedf48047ece84d004acaf8f386ae7a7d074983"));
        checkpoints.put( 70450, Sha256Hash.wrap("ea83266a9dfd7cf92a96aa07f86bdf60d45850bd47c175745e71a1aaf60b4091"));
  }

    public static void initTestnetCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put(    1, Sha256Hash.wrap("0x00000385558ec1b9af7f939e1626a3116b9fb988c86c2f915e6451e8efcd0521"));
        checkpoints.put(  200, Sha256Hash.wrap("0x0000015bef4f6677a5055b7b35fa0791d4df50c2a887c39aaf6ec42e2aab13fa"));
        checkpoints.put(  300, Sha256Hash.wrap("0x00000020e9647debfcf5a2c2b0b43d24eef32646a7b1b3e78d82879080a07366"));
        checkpoints.put(  941, Sha256Hash.wrap("0xb40633bf2a3b4c4ecc5a784233287ba7a74a17b58dc40c29d3886ef34c10181a"));
        checkpoints.put( 1500, Sha256Hash.wrap("0x63bc2ca1c9d0fab31fc05868a231c165823c61d5621a81630aadbf5fd79a1e15"));
        checkpoints.put( 2075, Sha256Hash.wrap("0x8ddcd1c685dd70b84fb4de5a5235f2f50382dec2fcddf1c507661944c48658a8"));
   }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "XgxQxd6B8iYgEEryemnJrpvoWZ3149MCkK";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "XDtvHyDHk4S3WJvwjxSANCpZiLLkKzoDnjrcRhca2iLQRtGEz1JZ";

}
