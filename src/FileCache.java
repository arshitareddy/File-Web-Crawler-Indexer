
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileCache {

	protected LinkedHashMap<Path, String> cache = new LinkedHashMap<Path, String>(5);
	protected String content;
	private Path path;

	public FileCache() {
	}

	private ReentrantLock fclock = new ReentrantLock();

	public abstract void replace(Path targetfile);

	public void showingMapvalues() {
		Collection c = cache.values();
		Iterator itr = c.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public final String fetch(String targetfile) {
		this.path = Paths.get(targetfile);
		fclock.lock();
		try {

			if (cache.containsKey(this.path)) {
			 System.out.println("File System Element is in Cache -< " +this.path+ " >-");
				return cache.get(targetfile);
			} else {

				if (cache.size() < 5) {
					cache.put(this.path, targetfile);
					 System.out.println("File System Element is new & caching file -< " +this.path+" >-");
					return content;
				} else {

					replace(this.path);
					return content;
				}
			}
		} finally {
			fclock.unlock();
		}
	}

}
