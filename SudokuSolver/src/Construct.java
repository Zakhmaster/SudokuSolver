import java.util.LinkedList;

public class Construct{
	public Sector[] sectors;
	public Length[] col;
	public Construct(LinkedList<Cell> cells){
		Length[] rows = new Length[9];
		col = new Length[9];
		for(int i = 0; i<9; i++){
			rows[i] = new Length(i, "row");
			col[i] = new Length(i, "column");
		}
		sectors = new Sector[9];
		for(int i = 0; i<9; i++){
			int indexrow = 0;
			int indexcolumn = 0;
			switch(i){
			case 1:
				indexcolumn =3;
				break;
			case 2: 
				indexcolumn = 6;
				break;
			case 3: 
				indexrow =3;
				break;
			case 4:
				indexrow = 3;
				indexcolumn = 3;
				break;
			case 5: 
				indexrow = 3;
				indexcolumn = 6;
				break;
			case 6:
				indexrow = 6;
				break;
			case 7: 
				indexrow = 6;
				indexcolumn = 3;
				break;
			case 8: 
				indexrow = 6;
				indexcolumn = 6;
				break;
			}
			Sector temp = new Sector(i);
			temp.addRow(rows[indexrow], 0);
			indexrow++;
			temp.addRow(rows[indexrow], 1);
			indexrow++;
			temp.addRow(rows[indexrow], 2);
			indexrow++;
			temp.addColumn(col[indexcolumn], 0);
			indexcolumn++;
			temp.addColumn(col[indexcolumn], 1);
			indexcolumn++;
			temp.addColumn(col[indexcolumn], 2);
			indexcolumn++;
			sectors[i] = temp;
			
		}
		
//		int[][][] board = {{{4,3,0},{2,0,9},{0,5,0}},{{7,0,2},{0,0,0},{9,0,4}},{{0,5,6},{7,0,3},{0,2,0}},{{9,0,1},{0,0,0},{3,0,4}},{{0,2,0},{3,4,1},{0,6,0}},{{8,0,4},{0,0,0},{2,0,5}},{{0,8,0},{6,0,7},{5,9,0}},{{4,0,5},{0,0,0},{2,0,6}},{{0,6,0},{5,0,2},{0,7,1}}};
//		for(int sect = 0; sect<9; sect++){
//			for(int i = 0; i<3;i++){
//				for(int j=0; j<3; j++){
//					if(board[sect][i][j]!=0){
//						sectors[sect].setCell(j, i, board[sect][i][j]);
//					}
//				}
//			}
//		}
//		for(int sect = 0; sect<9; sect++){
//			System.out.print("[");
//			for(int i = 0; i<3;i++){
//				System.out.print("	[");
//				for(int j=0; j<3; j++){
//					System.out.print("  "+ board[sect][i][j]+"  ");
//				}
//				System.out.print("]	");
//			}
//			System.out.println("]");
//		}
		while(!cells.isEmpty()){
			Cell temp = cells.removeLast();
			sectors[temp.getSector()].setCell(temp);
		}
		for(int i = 0; i<9; i++){
			sectors[i].start();
		}
		for(Sector sect: sectors){
			try {
				sect.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		for(Sector sect : sectors){
//			System.out.println(sect.toString());
//		}
	}
}
