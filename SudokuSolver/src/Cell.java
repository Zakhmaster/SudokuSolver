
public class Cell {
	private int value;
	private int column;
	private int row;
	private int sector;
	public Cell(int value, int column, int row, int sector){
		this.value = value;
		this.column = column;
		this.row = row;
		this.sector = sector;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	@Override
	public String toString(){
		String text  = "";
		text += value + " val " + row + " row " + column + " col " + sector + " sector ";
		return text;
	}
}
