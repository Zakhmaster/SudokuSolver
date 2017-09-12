import java.util.LinkedList;

public class Length{
	public LinkedList<Cell> cells;
	private boolean[] has;
	public int id;
	public String type;
	private boolean inUse;
	
	public Length(int id, String type){
		this.type = type;
		inUse = false;
		this.id = id;
		has = new boolean[9];
		for(Boolean bool: has){
			bool = false;
		}
		cells = new LinkedList<Cell>();
	} 
	public void addCell(Cell cell){
		cells.addLast(cell);
		has[cell.getValue()-1]=true;
//		System.out.println("The Length Type "+ type +" and of id "+ id +" set has for " +cell.getValue());
	}
	public boolean has(int value){
//		System.out.println(value +" "+ has[value-1]);
		return has[value-1];
	}
	public synchronized void TakeControl() throws InterruptedException{
		if(inUse){
			wait();
			inUse=true;
		}else{
			inUse=true;
		}
	}
	public synchronized void giveItAway(){
		inUse=false;
		notifyAll();
	}
}
