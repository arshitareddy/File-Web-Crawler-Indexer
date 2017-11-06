
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class FIFOFileCache extends FileCache {
	
	private static FIFOFileCache instance = null;
	private FIFOFileCache(){}
	private ReentrantLock fifolock = new ReentrantLock();
	private static ReentrantLock fifolock1 = new ReentrantLock();
	
	public static FIFOFileCache getInstance(){
		fifolock1.lock();
		try{
		  if(instance == null)
			  instance = new FIFOFileCache();
			 return instance;
		}finally{
			fifolock1.unlock();
		}
	  }
	
		public void replaceFIFO(LinkedHashMap<Path, String> h, Path f, String c) {
			fifolock.lock();
			try{
			Path key = h.keySet().iterator().next();
			System.out.println("File System Element is new & cache is full, so replacing -< " + key + " >- with -< "+f+" >- using FIFO logic");
			h.remove(key);
			h.put(f, c);
			}finally{
				fifolock.unlock();
			}
	}

		@Override
		public void replace(Path targetfile_path) {
			replaceFIFO(cache, targetfile_path, content);
			
		}

}
