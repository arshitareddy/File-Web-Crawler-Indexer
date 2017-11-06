import java.util.ArrayList;


public class Directory extends FSElement {

	private ArrayList<FSElement> children = new ArrayList<FSElement>();
	private int size;
	public Directory(String name, String owner, int size, Directory parent) {
		super(name, owner, size, parent);
		}
	
	public ArrayList<FSElement> getChildren(){
		return children;
	}
	
	public void appendChild(FSElement child){
		children.add(child);
	}
	
	public void showDirElements(Directory D1){
		for(FSElement d : D1.getChildren()){
			if(d instanceof Directory){
				System.out.println("Directory:"+d.getName());
				((Directory) d).showDirElements((Directory) d);
			}
			if(d instanceof File){
				System.out.println("File Name:"+d.getName() +" Size: "+d.getSize());
				
			}
		}
	}
 
	@Override
	public boolean isFile(){
		  return false;
	 }
		
	@Override
	public int getSize(){
		for(FSElement child: children){
			if(child instanceof Directory)
			{
			 for(FSElement c1 :((Directory) child).getChildren() ){
			 size = size + c1.getSize();
			 }
			 
			}
			if(child instanceof File){ 
			size = size + child.getSize();
			}
		}
		return size;
	
	}
	}
