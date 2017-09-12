import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Sudoku extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LinkedList<Cell> cells = new LinkedList<Cell>();
	private JPanel contentPane;
	public int current;
	public Button[][][] buttons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sudoku() {
		class SudoButton extends Button{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public int col, row, sector;
			public SudoButton(int sector, int col, int row){
				super();
				this.col = col;
				this.row = row;
				this.sector = sector;
			}
		}
		buttons = new Button[9][9][9];
		current = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 63, 329, 296);
		panel_3.add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		for(int i = 0; i<9; i++){
			JPanel sector = new JPanel();
			sector.setLayout(new GridLayout(3, 3, 0, 0));
			sector.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
			for(int j = 0; j<3;j++){
				for(int k =0; k<3;k++){
					SudoButton sudoButt = new SudoButton(i, k, j);
					buttons[i][k][j] = (Button)sudoButt;
					sudoButt.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0){
							SudoButton tempo = ((SudoButton)arg0.getComponent());
							Cell tempoy = new Cell(current, tempo.col, tempo.row, tempo.sector );
							String dubby = current + "";
							tempo.setLabel(dubby);
							cells.add(tempoy);
							System.out.println(tempoy.toString());
						}
					});
					sector.add(sudoButt);
				}
			}
			panel.add(sector);
		}
		
		Button button = new Button("Solve");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Construct sudoke = new Construct(cells);
				Length[] col = sudoke.col;
				for(Length column: col){
					while(!column.cells.isEmpty()){
						Cell temp = column.cells.removeFirst();
						buttons[temp.getSector()][temp.getColumn()][temp.getRow()].setLabel(Integer.toString(temp.getValue()));
					}
				}
			}
		});
		button.setBounds(123, 407, 70, 22);
		panel_3.add(button);
		
		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				current = Integer.parseInt(arg0.getItem().toString());
				System.out.println(arg0.getItem().toString());
			}
		});
		choice.setBounds(419, 93, 64, 34);
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.add("5");
		choice.add("6");
		choice.add("7");
		choice.add("8");
		choice.add("9");
		
		panel_3.add(choice);
	
		
//		for(int i = 0; i<9; i++){
//			for(int j = 0; j<9; j++){
//				SudoButton sudoButt = new SudoButton(j,i);
//				buttons[j][i] = (Button)sudoButt;
//				sudoButt.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent arg0){
//						SudoButton tempo = ((SudoButton)arg0.getComponent());
//						Cell tempoy = new Cell(current, tempo.col, tempo.row, tempo.sector );
//						String dubby = current + "";
//						tempo.setLabel(dubby);
//						cells.add(tempoy);
//						System.out.println(tempoy.toString());
//					}
//				});
//				panel.add(sudoButt);
//			}
//		}
	}
}
