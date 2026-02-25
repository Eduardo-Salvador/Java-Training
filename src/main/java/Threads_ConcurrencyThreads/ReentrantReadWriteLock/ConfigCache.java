package Threads_ConcurrencyThreads.ReentrantReadWriteLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConfigCache {
    private Map<String, String> config = new HashMap<>();
    ReentrantReadWriteLock readWriteLock;

    public ConfigCache(ReentrantReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }

    public String get(String key){
        readWriteLock.readLock().lock();
        try {
            return config.get(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(String key, String value){
        readWriteLock.writeLock().lock();
        try {
            config.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size(){
        readWriteLock.readLock().lock();
        try {
            return config.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void clear(){
        readWriteLock.writeLock().lock();
        try {
            config.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}