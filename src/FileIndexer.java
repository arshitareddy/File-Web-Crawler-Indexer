
public class FileIndexer implements Runnable {
	
	private FileQueue file_queue = FileQueue.getFileQueue();
	private FileCache fc = FIFOFileCache.getInstance();
	
	private volatile boolean done = false;
	
	public void indexFile(FSElement file){
		if(file != null){
		if(file instanceof Directory)
		System.out.println("In Indexer: FSElement -< "+file.getName()+" >- is a Directory, and the Parent Dir is: "+file.getParent().getName());
		if(file instanceof File)
			System.out.println("In Indexer: FSElement -< "+file.getName()+" >- is a File, and the Parent Dir is: "+file.getParent().getName());
		if(file instanceof Link)
			System.out.println("In Indexer: FSElement -< "+file.getName()+" >- is a Link, and it is Linked to: "+file.getLinkedto().getName());
		fc.fetch(file.getName());
	}}
	
	public void indexUrl(String url){
		if (url != null){
			System.out.println("Indexed URL is : "+ url);
		}
	}
	
	public void setDone(){
		this.done = true;
	}

	@Override
	public void run() {
		while(done == false){
			indexFile(file_queue.get());
			//indexUrl(file_queue.get_url());
			}
			}
		}
		
	


