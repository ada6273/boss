
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实现缓存目标数据的缓存类（CachedData），并实现一个方法（processCachedData），
 * 该方法在缓存有效时处理数据，如果无效，则首先根据其数据源更新缓存对象，然后对其进行处理。
 */
public class CachedData {

	// 缓存的目标数据
	private Object data;

	private volatile boolean cacheValid = false;

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	// 数据源获取最新数据
	private Object findDb() {
		return "newData";
	}

	public Object processCachedData() {
		lock.readLock().lock();
		if (!cacheValid) {
			// 在获取写锁前必须释放读锁
			lock.readLock().unlock();
			lock.writeLock().lock();
			// 再次检查其他线程是否已经抢到

			if (!cacheValid) {
				cacheValid = true;
				// 获取数据
				data = findDb();
			}
			// 在释放写锁之前通过获取读锁来降级
			lock.readLock().lock();
			// 释放写锁，保持读锁
			lock.writeLock().unlock();
		}
		// process(data);使用缓存数据
		lock.readLock().unlock();
		return data;
	}

}