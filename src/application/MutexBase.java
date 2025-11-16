package application;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexBase extends AbstractBase {

    // Contém o lock específico
    private final ReentrantLock mutex = new ReentrantLock();
    
    @Override
    public Lock getReadLock() {
        return mutex;
    }

    @Override
    public Lock getWriteLock() {
        return mutex;
    }
}