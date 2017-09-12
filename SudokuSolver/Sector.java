import java.util.LinkedList;


public class Sector extends Thread{
	private Cell[] values;
	private int id;
	private Length[] rows;
	private Length[] columns;
	private boolean[] has;
	private int filled;
	
	public Sector(int id){
		has = new boolean[9];
		this.id = id;
		values = new Cell[9];
		filled = 0;
		this.rows = new Length[3];
		this.columns = new Length[3];
	}
	
	public void setCell(int column, int row, int value){
		Cell cell = new Cell(value, column, row, id);
		values[(3*row)+column] = cell;
		rows[row].addCell(cell);
		columns[column].addCell(cell);
		has[value-1] = true;
		filled++;
		if(id == 4){
			System.out.println("just added the value " + value);
		}
		return;
	}
	public void setCell(Cell cell){
		int row, column, value;
		row = cell.getRow();
		column = cell.getColumn();
		value = cell.getValue();
		values[(row%3)*3+column%3] = cell;
		rows[row%3].addCell(cell);
		columns[column%3].addCell(cell);
		has[value-1] = true;
		filled++;
		if(id == 4){
			System.out.println("just added the value " + value);
		}
	}
	
	public synchronized void run(){
		System.out.println("Sector "+id+" was started");
		int token;
		while(filled < 9){
			for(token = 0; token<9; token++){
				if(!has[token]){
					standardSearch(token+1);
				}
			}
		}
		System.out.println("filled sector "+id);
		
	}

	private void midLevelSearch(int token){

    }

	private void standardSearch(int token){
		LinkedList<int[]> open = new LinkedList<int[]>();
		LinkedList<int[]> result = new LinkedList<int[]>();
		for(int colu = 0; colu<3 ; colu++){
			Length col = columns[colu];
			try {
				col.TakeControl();
//				System.out.println("took column "+ id+col.id+token);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!col.has(token)){
//				System.out.println("made it in col has "+ id);
				for(int j = 0; j<3; j++){
					if(values[(3*j)+colu]==null){
						open.add(new int[]{colu,j});
//						System.out.println("made it here "+id+" "+col.id+" "+j);
					}
				}
			}
			col.giveItAway();
//			System.out.println("gave back column "+ id);
		}

		while(!open.isEmpty()){
			int[] check = open.remove();
			try {
				rows[check[1]].TakeControl();
//				System.out.print("took row " + check[1]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!rows[check[1]].has(token)){
				result.add(check);
			}
		rows[check[1]].giveItAway();
//		System.out.println("gave back row "+ id);
		}
		if(!result.isEmpty()){
		int[] temp = result.remove();

		if(result.isEmpty()){
			setCell(temp[0],temp[1], token);
//			System.out.println("Added Cell");
			}
		}
	}
	public void addRow(Length row, int index){
		this.rows[index] = row;
	}	
	public void addColumn(Length column, int index){
		this.columns[index] = column;
	}
	public String toString(){
		String sectory = "";
		int lastRow = 0;
		for(Cell cell: values){
			if(!(cell == null)){
				if(cell.getRow()!=lastRow){
					lastRow = cell.getRow();
					sectory = sectory + " || " + cell.getValue();
				}else{
					sectory = sectory + " | " + cell.getValue(); 
				}
			}else{
				sectory = sectory +" | "+ 0;
			}
		}
		return sectory+" |";
	}
}
