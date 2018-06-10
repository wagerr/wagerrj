package org.wagerrj.kits;

import org.wagerrj.core.NetworkParameters;
import org.wagerrj.store.BlockStore;
import org.wagerrj.store.BlockStoreException;
import org.wagerrj.store.LevelDBBlockStore;
import org.wagerrj.store.SPVBlockStore;

import java.io.File;

/**
 * Created by Eric on 2/23/2016.
 */
public class LevelDBWalletAppKit extends WalletAppKit {
    public LevelDBWalletAppKit(NetworkParameters params, File directory, String filePrefix) {
        super(params, directory, filePrefix);
    }

    /**
     * Override this to use a {@link BlockStore} that isn't the default of {@link SPVBlockStore}.
     */
    protected BlockStore provideBlockStore(File file) throws BlockStoreException {
        return new LevelDBBlockStore(context, file);
    }
}
