package application;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteBase extends AbstractBase {
    
    // Contém o lock específico
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    @Override
    public Lock getReadLock() {
        return rwLock.readLock();
    }

    @Override
    public Lock getWriteLock() {
        return rwLock.writeLock();
    }
}