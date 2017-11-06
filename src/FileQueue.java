
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FileQueue {

	private CopyOnWriteArrayList<FSElement> queue = new CopyOnWriteArrayList<FSElement>();
	//private CopyOnWriteArrayList<String> url_queue = new CopyOnWriteArrayList<String>();

	private static FileQueue instance;
	private ReentrantLock q_lock = new ReentrantLock();
//	private ReentrantLock url_lock = new ReentrantLock();
	private Condition cond1 = q_lock.newCondition();
	private Condition cond2 = q_lock.newCondition();
//	private Condition cond3 = url_lock.newCondition();
//	private Condition cond4 = url_lock.newCondition();
	private FSElement f;
//	private String url;

	private FileQueue() {
	}

	public static FileQueue getFileQueue() {
		if (instance == null)
			instance = new FileQueue();
		return instance;
	}

	public void put(FSElement file) {
		q_lock.lock();
		try{
				if(this.queue.size() < 10){
				System.out.println("Added file to queue ");
				this.queue.add(file);
				cond1.signalAll();
			}
			else{
				cond2.await();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			q_lock.unlock();
		}
	}

	
	public FSElement get() {
		q_lock.lock();
		try{
			
	    	while(this.queue.isEmpty()){
			    System.out.println("Queue is empty, waiting");
			    cond1.await();
	    		}
	    		
				f = this.queue.get(0);
			    this.queue.remove(0);
			    cond2.signalAll();
			    return f;
			
		} catch (InterruptedException e) {
			
		}finally{
		   q_lock.unlock();	
		}
		
		return null;
	}
	
}
