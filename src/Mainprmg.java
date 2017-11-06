import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mainprmg {
	public static void main(String[] args){
		FileSystem rootdir = FileSystem.getFileSystem();
		Directory root = rootdir.getRootDIR();
		Directory system = new Directory("system","Arshita",0,rootdir.getRootDIR());
		Directory home = new Directory("home","Arshita",0,rootdir.getRootDIR());
		Directory pictures= new Directory("pictures","Arshita",0,home);
		
		ExecutorService executor = Executors.newWorkStealingPool();
		
		File a = new File("a","Arshita",1,system);
		File b = new File("b","Arshita",2,system);
		File c = new File("c","Arshita",3,system);
		File d = new File("d","Arshita",4,home);
		File e = new File("e","Arshita",5,pictures);
		File f = new File("f","Arshita",6,pictures);
		
		Link x = new Link("x", "Arshita",0,home, system);
		Link y = new Link("y", "Arshita",0,pictures, e);
		
		root.appendChild(system);
		root.appendChild(home);
		home.appendChild(x);
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		home.appendChild(d);
		home.appendChild(pictures);
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);
		
		FileCrawler fc1 = new FileCrawler(root); 
		FileCrawler fc2 = new FileCrawler(root); 
		FileCrawler fc3 = new FileCrawler(system); 
		FileCrawler fc4 = new FileCrawler(root); 
		FileCrawler fc5 = new FileCrawler(home); 
		FileCrawler fc6 = new FileCrawler(root); 
		FileCrawler fc7 = new FileCrawler(pictures); 
		FileIndexer fi1 = new FileIndexer(); 
		FileIndexer fi2 = new FileIndexer(); 
		FileIndexer fi3 = new FileIndexer(); 
		//WebCrawler wc1 = new WebCrawler("http://www.umb.edu");
		WebCrawler wc2 = new WebCrawler("http://www.facebook.com/");
		//executor.execute(wc1);
		executor.execute(wc2);
		executor.execute(fc1);
		executor.execute(fi1);
		executor.execute(fc2);
		executor.execute(fi2);
		executor.execute(fc3);
		executor.execute(fc4);
		executor.execute(fc5);
		executor.execute(fi3);
		executor.execute(fc6);
		executor.execute(fc7);
		
		
		try {
			Thread.sleep(10000);
			executor.shutdown();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
	
		
	}

}
