public class FileSystem {
	private static FileSystem instance = null;
	private Directory rootDir= new Directory("root", "Arshita", 0, null);
	private FileSystem(){		
	}
	
	public static FileSystem getFileSystem(){ 
		if(instance==null) instance = new FileSystem(); 
		return instance; 
		}
	
	public Directory getRootDIR(){
		return this.rootDir;
	}
	
	public void showAllElement(){
		for(FSElement child: rootDir.getChildren())
		{
			System.out.println("Directory:"+child.getName());
			if(child instanceof Directory)
				{
				  ((Directory) child).showDirElements((Directory) child);				
				}
			if(child instanceof File){ 
					System.out.println("File Name:"+child.getName() +" Size: "+child.getSize());
				}
			}
		}
			
	}
		
		
	
	


