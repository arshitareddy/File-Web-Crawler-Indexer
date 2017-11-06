

public class FileCrawler implements Runnable{

	private Directory dir;
	private FileQueue queue = FileQueue.getFileQueue();
	
	
	
	
	public FileCrawler(Directory root){
		this.dir = root;
	}
	
	
	@Override
	public void run() {
		
		crawl(this.dir);
		
	}
	
	public void crawl(Directory root){
		for(FSElement child: root.getChildren()){
			if(child instanceof Directory){
				System.out.print("FSElement -< "+child.getName()+" >- is a Directory ");
				this.queue.put(child);
				crawl((Directory) child);
			}
			if(child instanceof Link){
				System.out.print("FSElement -< "+child.getName()+" >-is a Link, linked to -< "+child.getLinkedto().getName()+" >-");
				this.queue.put(child);
			}
			if(child instanceof File){
				System.out.print("FSElement -< "+child.getName()+" >- is a File ");
				this.queue.put(child);
			}
		}
	}

}
