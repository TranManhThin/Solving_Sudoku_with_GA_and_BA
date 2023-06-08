package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.SwingWorker;

import org.json.simple.parser.ParseException;


import connection.Connect_Database;
import ga.ConstantGA;



public class GUI_Sudoku_9x9 extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String randomSudoku;
	private Font font;
	private int I, J;
	private int LV = 0;
	private JPanel jPanel1, jPanel2, jPanel3, jPanelMain, jPanel4;
	private JLabel title, lblSoLuongQT, lblLaiGhep, lblDotBien, lblTienHoa, lblSoDao, lblTienHoaDao;
	private JTextField txtSoLuongQT, txtLaiGhep, txtTienHoa, txtDotBien, txtSoDao, txtTienHoaDao;
	private JComboBox<String> level;
	private JButton jButtonNewGame, jSolver, jButtonThoat, jSolverWBa;
	private JButton bt[][] = new JButton[9][10];
	private JButton btSolver[][] = new JButton[9][10];
	private int[][] a = new int[9][10];
	private int[][] A = new int[9][10];
	private ArrayList<ArrayList<Integer>> dataLine = new ArrayList<>();
	

	public GUI_Sudoku_9x9(int lv) {
		// TODO Auto-generated constructor stub
		super("18019701 - Tran Manh Thin - DHCNTT14");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.LV = lv;
		jPanel1 = new JPanel() {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        BufferedImage image;
					try {
						image = ImageIO.read(new File("src/images/header5.png"));
						 g.drawImage(image, 0, 0, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
			      }
		};
	
		font = new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 80);
		title = new JLabel("Sudoku");
		title.setFont(font);
		title.setForeground(Color.red);
		jPanel1.add(title);

		jPanel2 = new JPanel() {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        BufferedImage image;
					try {
						image = ImageIO.read(new File("src/images/mid1.png"));
						 g.drawImage(image, 0, 0, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
			      }
		};
		jPanel2.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setFont(new Font("UTM Micra", 1, 30));
				bt[i][j].setForeground(Color.black);
				jPanel2.add(bt[i][j]);
			}
		}

		jPanel4 = new JPanel() {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        BufferedImage image;
					try {
						image = ImageIO.read(new File("src/images/mid2.png"));
						 g.drawImage(image, 0, 0, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
			      }
		};
		jPanel4.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				btSolver[i][j] = new JButton();
				btSolver[i][j].addActionListener(this);
				btSolver[i][j].addKeyListener(this);
				btSolver[i][j].setActionCommand(i + " " + j);
				btSolver[i][j].setBackground(Color.white);
				btSolver[i][j].setFont(new Font("UTM Micra", 1, 30));
				btSolver[i][j].setForeground(Color.black);
				jPanel4.add(btSolver[i][j]);
			}
		}


		jPanelMain = new JPanel();
		jPanelMain.setLayout(new GridLayout(1, 2));
		jPanelMain.add(jPanel2);
		jPanelMain.add(jPanel4);
		
		
	
		jPanel3 = new JPanel() {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		      protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        BufferedImage image;
				try {
					image = ImageIO.read(new File("src/images/footer.png"));
					 g.drawImage(image, 0, 0, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		      }
		};
		jPanel3.setLayout(null);
		jPanel3.setPreferredSize(new Dimension(0,100));
		
		
		
		jButtonNewGame = new RoundedButton("Tạo game mới");
		jButtonNewGame.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jButtonNewGame.setBounds(600,20,110,50);
		jSolver = new RoundedButton("Giải");
		jSolverWBa = new RoundedButton("Biểu đồ");
		jSolverWBa.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jSolver.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jSolver.setBounds(840,20,110,50);
		jSolverWBa.setBounds(965, 20, 100, 50);
		jButtonThoat = new RoundedButton("Thoát");
		jButtonThoat.setBackground(Color.WHITE);
		jButtonThoat.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jButtonThoat.setBounds(1080, 20, 100, 50);
		
		lblSoLuongQT = new JLabel("Số lượng quần thể: ");
		lblSoLuongQT.setBounds(10,10,120,30);
		txtSoLuongQT = new JTextField();
		txtSoLuongQT.setBounds(130, 10, 70, 30);
		
		lblTienHoa = new JLabel("Vòng đời tiến hóa: ");
		lblTienHoa.setBounds(10, 55,120,30);
		txtTienHoa = new JTextField();
		txtTienHoa.setBounds(130, 55, 70, 30);
		
		lblLaiGhep = new JLabel("Tỉ lệ lai ghép:");
		lblLaiGhep.setBounds(220, 10, 120, 30);
		txtLaiGhep = new JTextField();
		txtLaiGhep.setBounds(320, 10, 70, 30);
		
		lblDotBien = new JLabel("Tỉ lệ đột biến:");
		lblDotBien
		.setBounds(220, 55, 120, 30);
		txtDotBien = new JTextField();
		txtDotBien.setBounds(320, 55, 70, 30);
		
		lblSoDao = new JLabel("Số đảo:");
		lblSoDao.setBounds(410, 10, 110, 30);
		txtSoDao = new JTextField();
		txtSoDao.setBounds(520, 10, 70, 30);
		
		lblTienHoaDao = new JLabel("Vòng đời đảo:");
		lblTienHoaDao.setBounds(410, 55, 110, 30);
		txtTienHoaDao = new JTextField();
		txtTienHoaDao.setBounds(520, 55, 70, 30);
        level = new JComboBox<>();
		level.setBackground(Color.WHITE);
		
//        level.setPreferredSize(new Dimension(150,40));
		level.setBounds(725, 20, 100,50);
		level.addItem("Dễ");
		level.addItem("Trung bình");
		level.addItem("Khó");
		level.addItem("Siêu khó");
		level.addItem("Tự nhập");
		level.setSelectedIndex(LV);
		jPanel3.add(jButtonNewGame);
		jPanel3.add(level);
		jPanel3.add(jSolver);
		jPanel3.add(jSolverWBa);
		jPanel3.add(jButtonThoat);
		jPanel3.add(lblSoLuongQT);
		jPanel3.add(txtSoLuongQT);
		jPanel3.add(lblTienHoa);
		jPanel3.add(txtTienHoa);
		jPanel3.add(lblLaiGhep);
		jPanel3.add(txtLaiGhep);
		jPanel3.add(lblDotBien);
		jPanel3.add(txtDotBien);
		jPanel3.add(lblSoDao);
		jPanel3.add(txtSoDao);
		jPanel3.add(lblTienHoaDao);
		jPanel3.add(txtTienHoaDao);
		

		this.add(jPanel1, BorderLayout.NORTH);
		this.add(jPanelMain, BorderLayout.CENTER);
		this.add(jPanel3, BorderLayout.SOUTH);
		jSolverWBa.setEnabled(false);
		jButtonNewGame.addActionListener(this);
		jSolver.addActionListener(this);
		jSolverWBa.addActionListener(this);
		jButtonThoat.addActionListener(this);
		txtSoLuongQT.addActionListener(this);
		txtTienHoa.addActionListener(this);
		txtLaiGhep.addActionListener(this);
		txtDotBien.addActionListener(this);
		txtSoDao.addActionListener(this);
		txtTienHoaDao.addActionListener(this);
		
		loadRate();

		if (LV == 0) {
			try {
				String[] S = Connect_Database.parseSudoku("Easy");
				creatMatrix(S);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (LV == 1) {
			try {
				String[] S = Connect_Database.parseSudoku("Medium");
				creatMatrix(S);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (LV == 2) {
			try {
				String[] S = Connect_Database.parseSudoku("Hard");
				creatMatrix(S);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}else if (LV == 3) {
			try {
				String[] S = Connect_Database.parseSudoku("Expert");
				creatMatrix(S);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
		else {
			try {
				String[] S = Connect_Database.parseSudoku("Generate");
				creatMatrix(S);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void creatMatrix(String[] S) throws FileNotFoundException {
		randomSudoku = S[new Random().nextInt(S.length)];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				a[i][j] = randomSudoku.charAt(i * 9 + j) - 48;

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (a[i][j] > 0)
					bt[i][j].setText(a[i][j] + "");
	}

//	public static void main(String[] args) {
//		new GUI_Sudoku_9x9(0).setVisible(true);
//	}
	
	public void loadRate() {
		txtSoLuongQT.setText((int)ConstantGA.SO_LUONG_QUAN_THE.getGiaTri()+"");
		txtSoDao.setText((int)ConstantGA.SO_DAO.getGiaTri()+"");
		txtTienHoa.setText((int)ConstantGA.VONG_DOI_TIEN_HOA.getGiaTri()+"");
		txtTienHoaDao.setText((int)ConstantGA.VONG_DOI_QUAN_THE_DAO.getGiaTri()+"");
		txtDotBien.setText(ConstantGA.TI_LE_DOT_BIEN.getGiaTri()+"");
		txtLaiGhep.setText(ConstantGA.TI_LE_LAI_GHEP.getGiaTri()+"");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int v = e.getKeyCode();
		if ((v >= 49 && v <= 57) || (v >= 97 && v <= 105)) {
			if (v >= 49 && v <= 57)
				v -= 48;
			if (v >= 97 && v <= 105)
				v -= 96;
			bt[I][J].setText(v + "");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				if(a[i][j] > 0 || !bt[i][j].getText().equals("")) {
					if (Integer.parseInt(bt[i][j].getText()) == A[i][j] ) {
						btSolver[i][j].setBackground(Color.red);
						btSolver[i][j].setForeground(Color.WHITE);
					}
				}
				else {
					btSolver[i][j].setBackground(Color.white);
					btSolver[i][j].setForeground(Color.BLACK);
				}
				
			}
					
		Object o = e.getSource();
		if (o.equals(jButtonNewGame)) {
			new GUI_Sudoku_9x9(level.getSelectedIndex()).setVisible(true);
			this.dispose();
		}
		else if(o.equals(jButtonThoat)) {

			setVisible(false);
			GUI_Main main = new GUI_Main();
			main.setVisible(true);
//			SwingUtilities.invokeLater(new Runnable() {
//				
//				@Override
//				public void run() {
//					new SplineTest().display(dataLine);;
//				}
//			});
			


		}
		else if(o.equals(jSolver)) {
			
			Object[] options = { "GA", "GA_With_BA"};
			int selection = JOptionPane.showOptionDialog(null, "Chọn 1 lựa chọn", "OPTION", JOptionPane.DEFAULT_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null,  options,  options[0]);
			if(selection == 0) {
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>(){
					@Override
					protected String doInBackground() throws Exception {
						// TODO Auto-generated method stub
						dataLine = new ArrayList<>();
						ArrayList<Integer> firstRow = new ArrayList<Integer>();
						ArrayList<Integer> secondRow = new ArrayList<Integer>();
						ArrayList<Integer> thirdRow = new ArrayList<Integer>();
						ArrayList<Integer> fourthRow = new ArrayList<Integer>();
						ArrayList<Integer> fifthRow = new ArrayList<Integer>();
						ArrayList<Integer> sixthRow = new ArrayList<Integer>();
						ArrayList<Integer> seventhRow = new ArrayList<Integer>();
						ArrayList<Integer> eighthRow = new ArrayList<Integer>();
						ArrayList<Integer> ninthRow = new ArrayList<Integer>();
			
						for (int i = 0; i < 9; i++) {
							for (int j = 0; j < 9; j++) {
								if (i == 0) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										firstRow.add(0);
									} else {
										firstRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 1) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										secondRow.add(0);
									} else {
										secondRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 2) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										thirdRow.add(0);
									} else {
										thirdRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 3) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										fourthRow.add(0);
									} else {
										fourthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 4) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										fifthRow.add(0);
									} else {
										fifthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 5) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										sixthRow.add(0);
									} else {
										sixthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 6) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										seventhRow.add(0);
									} else {
										seventhRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 7) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										eighthRow.add(0);
									} else {
										eighthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 8) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										ninthRow.add(0);
									} else {
										ninthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
			
							}
						}
						ArrayList<ArrayList<Integer>> sudokuPuzzle = new ArrayList<ArrayList<Integer>>(Arrays.asList(firstRow,
								secondRow, thirdRow, fourthRow, fifthRow, sixthRow, seventhRow, eighthRow, ninthRow));
						int soLuongQT = 0;
						int vongDoiTienHoa = 0;
						double tiLeLai = 0.0;
						double tiLeDotBien = 0.0;
						int soDao = 0;
						int vongDoiDao = 0;
						if (txtSoLuongQT.getText().equals("")) {
							soLuongQT = (int)ConstantGA.SO_LUONG_QUAN_THE.getGiaTri();
						}
						else {
							soLuongQT = Integer.parseInt(txtSoLuongQT.getText());
						}
						
						if (txtTienHoa.getText().equals("")) {
							vongDoiTienHoa = (int)ConstantGA.VONG_DOI_TIEN_HOA.getGiaTri();
						}
						else {
							vongDoiTienHoa = Integer.parseInt(txtTienHoa.getText());
						}
						
						if (txtLaiGhep.getText().equals("")) {
							tiLeLai = ConstantGA.TI_LE_LAI_GHEP.getGiaTri();
						}
						else {
							tiLeLai = Double.parseDouble(txtLaiGhep.getText());
						}
						
						if (txtDotBien.getText().equals("")) {
							tiLeDotBien = ConstantGA.TI_LE_DOT_BIEN.getGiaTri();
						}
						else {
							tiLeDotBien = Double.parseDouble(txtDotBien.getText());
						}
						
						if (txtSoDao.getText().equals("")) {
							soDao = (int)ConstantGA.SO_DAO.getGiaTri();
						}
						else {
							soDao = Integer.parseInt(txtSoDao.getText());
						}
						
						if (txtTienHoaDao.getText().equals("")) {
							vongDoiDao = (int)ConstantGA.VONG_DOI_QUAN_THE_DAO.getGiaTri();
						}
						else {
							vongDoiDao = Integer.parseInt(txtTienHoaDao.getText());
						}
						ga.SudokuGeneticAlgorithm imp = new ga.SudokuGeneticAlgorithm(sudokuPuzzle, (int)soLuongQT*soDao, vongDoiTienHoa, tiLeLai, tiLeDotBien, soDao, vongDoiDao,9);
						imp.trienKhaiGiaiThuatDiTruyen();
		
						String s = imp.toString();
						return s;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						ArrayList<Integer> result;
						String informed="";
						try {
							informed = get();
							String[] part = informed.split(";");
							String part1 = part[0];
							String part2 = part[1];
							String line1 = part[2];
							String line2 = part[3];
							String line3 = part[4];
							String line4 = part[5];
							String line5 = part[6];
							String line6 = part[7];
							
							line1 = line1.replace("[", "").replace("]", "");
							String[] strArr1 = line1.split(",");
							ArrayList<Integer> line01 = new ArrayList<>();
							for(String s: strArr1) {
								line01.add(Integer.parseInt(s.trim()));
							}
							
							line2 = line2.replace("[", "").replace("]", "");
							String[] strArr2 = line2.split(",");
							ArrayList<Integer> line02 = new ArrayList<>();
							for(String s: strArr2) {
								line02.add(Integer.parseInt(s.trim()));
							}
							
							line3 = line3.replace("[", "").replace("]", "");
							String[] strArr3 = line3.split(",");
							ArrayList<Integer> line03 = new ArrayList<>();
							for(String s: strArr3) {
								line03.add(Integer.parseInt(s.trim()));
							}
							
							line4 = line4.replace("[", "").replace("]", "");
							String[] strArr4 = line4.split(",");
							ArrayList<Integer> line04 = new ArrayList<>();
							for(String s: strArr4) {
								line04.add(Integer.parseInt(s.trim()));
							}
							
							line5 = line5.replace("[", "").replace("]", "");
							String[] strArr5 = line5.split(",");
							ArrayList<Integer> line05 = new ArrayList<>();
							for(String s: strArr5) {
								line05.add(Integer.parseInt(s.trim()));
							}
							
							line6 = line6.replace("[", "").replace("]", "");
							String[] strArr6 = line6.split(",");
							ArrayList<Integer> line06 = new ArrayList<>();
							for(String s: strArr6) {
								line06.add(Integer.parseInt(s.trim()));
							}
							
							dataLine.add(line01);
							dataLine.add(line02);
							dataLine.add(line03);
							dataLine.add(line04);
							dataLine.add(line05);
							dataLine.add(line06);
							jSolverWBa.setEnabled(true);
							if (!part1.equals("[]")) {
							
								part1 = part1.replace("[", "").replace("]", "");
								String[] strArr = part1.split(",");
								result = new ArrayList<>();
								for(String s: strArr) {
									result.add(Integer.parseInt(s.trim()));
								}
								for (int i = 0; i < 9; i++) {
									for (int j = 0; j < 9; j++) {
										A[i][j] = result.get(i * 9 + j);
										btSolver[i][j].setText(A[i][j] + "");
										if(!bt[i][j].getText().equals("")) {
											if (Integer.parseInt(bt[i][j].getText()) == A[i][j]) {
												btSolver[i][j].setBackground(Color.red);
												btSolver[i][j].setForeground(Color.WHITE);
											}
										}
									}
								}
								
								JOptionPane.showMessageDialog(rootPane, part2,"Message",JOptionPane.INFORMATION_MESSAGE);
								
							}
							else{
								JOptionPane.showMessageDialog(rootPane, "Vui lòng thử lại sau!","Message",JOptionPane.INFORMATION_MESSAGE);
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
				worker.execute();
			}
			else {
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>(){
					@Override
					protected String doInBackground() throws Exception {
						// TODO Auto-generated method stub
						dataLine = new ArrayList<>();
						ArrayList<Integer> firstRow = new ArrayList<Integer>();
						ArrayList<Integer> secondRow = new ArrayList<Integer>();
						ArrayList<Integer> thirdRow = new ArrayList<Integer>();
						ArrayList<Integer> fourthRow = new ArrayList<Integer>();
						ArrayList<Integer> fifthRow = new ArrayList<Integer>();
						ArrayList<Integer> sixthRow = new ArrayList<Integer>();
						ArrayList<Integer> seventhRow = new ArrayList<Integer>();
						ArrayList<Integer> eighthRow = new ArrayList<Integer>();
						ArrayList<Integer> ninthRow = new ArrayList<Integer>();
			
						for (int i = 0; i < 9; i++) {
							for (int j = 0; j < 9; j++) {
								if (i == 0) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										firstRow.add(0);
									} else {
										firstRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 1) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										secondRow.add(0);
									} else {
										secondRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 2) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										thirdRow.add(0);
									} else {
										thirdRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 3) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										fourthRow.add(0);
									} else {
										fourthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 4) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										fifthRow.add(0);
									} else {
										fifthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 5) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										sixthRow.add(0);
									} else {
										sixthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 6) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										seventhRow.add(0);
									} else {
										seventhRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 7) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										eighthRow.add(0);
									} else {
										eighthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 8) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										ninthRow.add(0);
									} else {
										ninthRow.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
			
							}
						}
						ArrayList<ArrayList<Integer>> sudokuPuzzle = new ArrayList<ArrayList<Integer>>(Arrays.asList(firstRow,
								secondRow, thirdRow, fourthRow, fifthRow, sixthRow, seventhRow, eighthRow, ninthRow));
						int soLuongQT = 0;
						int vongDoiTienHoa = 0;
						double tiLeLai = 0.0;
						double tiLeDotBien = 0.0;
						int soDao = 0;
						int vongDoiDao = 0;
						if (txtSoLuongQT.getText().equals("")) {
							soLuongQT = (int)ConstantGA.SO_LUONG_QUAN_THE.getGiaTri();
						}
						else {
							soLuongQT = Integer.parseInt(txtSoLuongQT.getText());
						}
						
						if (txtTienHoa.getText().equals("")) {
							vongDoiTienHoa = (int)ConstantGA.VONG_DOI_TIEN_HOA.getGiaTri();
						}
						else {
							vongDoiTienHoa = Integer.parseInt(txtTienHoa.getText());
						}
						
						if (txtLaiGhep.getText().equals("")) {
							tiLeLai = ConstantGA.TI_LE_LAI_GHEP.getGiaTri();
						}
						else {
							tiLeLai = Double.parseDouble(txtLaiGhep.getText());
						}
						
						if (txtDotBien.getText().equals("")) {
							tiLeDotBien = ConstantGA.TI_LE_DOT_BIEN.getGiaTri();
						}
						else {
							tiLeDotBien = Double.parseDouble(txtDotBien.getText());
						}
						
						if (txtSoDao.getText().equals("")) {
							soDao = (int)ConstantGA.SO_DAO.getGiaTri();
						}
						else {
							soDao = Integer.parseInt(txtSoDao.getText());
						}
						
						if (txtTienHoaDao.getText().equals("")) {
							vongDoiDao = (int)ConstantGA.VONG_DOI_QUAN_THE_DAO.getGiaTri();
						}
						else {
							vongDoiDao = Integer.parseInt(txtTienHoaDao.getText());
						}
						gaWithBa.SudokuGeneticAlgorithm imp = new gaWithBa.SudokuGeneticAlgorithm(sudokuPuzzle, (int)soLuongQT*soDao, vongDoiTienHoa, tiLeLai, tiLeDotBien, soDao, vongDoiDao,9);
						imp.trienKhaiGiaiThuatDiTruyen();
		
						String s = imp.toString();
						return s;
					}
					
					@Override
					protected void done() {
						// TODO Auto-generated method stub
						ArrayList<Integer> result;
						String informed="";
						try {
							informed = get();
							String[] part = informed.split(";");
							String part1 = part[0];
							String line1 = part[2];
							String line2 = part[3];
							String line3 = part[4];
							String line4 = part[5];
							String line5 = part[6];
							String line6 = part[7];
							line1 = line1.replace("[", "").replace("]", "");
							String[] strArr1 = line1.split(",");
							ArrayList<Integer> line01 = new ArrayList<>();
							for(String s: strArr1) {
								line01.add(Integer.parseInt(s.trim()));
							}
							
							line2 = line2.replace("[", "").replace("]", "");
							String[] strArr2 = line2.split(",");
							ArrayList<Integer> line02 = new ArrayList<>();
							for(String s: strArr2) {
								line02.add(Integer.parseInt(s.trim()));
							}
							
							line3 = line3.replace("[", "").replace("]", "");
							String[] strArr3 = line3.split(",");
							ArrayList<Integer> line03 = new ArrayList<>();
							for(String s: strArr3) {
								line03.add(Integer.parseInt(s.trim()));
							}
							
							line4 = line4.replace("[", "").replace("]", "");
							String[] strArr4 = line4.split(",");
							ArrayList<Integer> line04 = new ArrayList<>();
							for(String s: strArr4) {
								line04.add(Integer.parseInt(s.trim()));
							}
							
							line5 = line5.replace("[", "").replace("]", "");
							String[] strArr5 = line5.split(",");
							ArrayList<Integer> line05 = new ArrayList<>();
							for(String s: strArr5) {
								line05.add(Integer.parseInt(s.trim()));
							}
							
							line6 = line6.replace("[", "").replace("]", "");
							String[] strArr6 = line6.split(",");
							ArrayList<Integer> line06 = new ArrayList<>();
							for(String s: strArr6) {
								line06.add(Integer.parseInt(s.trim()));
							}
							
							dataLine.add(line01);
							dataLine.add(line02);
							dataLine.add(line03);
							dataLine.add(line04);
							dataLine.add(line05);
							dataLine.add(line06);
							jSolverWBa.setEnabled(true);
							if (!part1.equals("[]")) {
								String part2 = part[1];
								
								part1 = part1.replace("[", "").replace("]", "");
								String[] strArr = part1.split(",");
								result = new ArrayList<>();
								for(String s: strArr) {
									result.add(Integer.parseInt(s.trim()));
								}
								for (int i = 0; i < 9; i++) {
									for (int j = 0; j < 9; j++) {
										A[i][j] = result.get(i * 9 + j);
										btSolver[i][j].setText(A[i][j] + "");
										if(!bt[i][j].getText().equals("")) {
											if (Integer.parseInt(bt[i][j].getText()) == A[i][j]) {
												btSolver[i][j].setBackground(Color.red);
												btSolver[i][j].setForeground(Color.WHITE);
											}
										}
									}
								}
								
								JOptionPane.showMessageDialog(rootPane, part2,"Message",JOptionPane.INFORMATION_MESSAGE);
								
								
							}
							else{
								JOptionPane.showMessageDialog(rootPane, "Vui lòng thử lại sau!","Message",JOptionPane.INFORMATION_MESSAGE);
							}
							
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
				worker.execute();
			}
			
		}
		else if (o.equals(jSolverWBa)) {
			
			GUI_SplineChart chart = new GUI_SplineChart(dataLine);
			chart.setVisible(true);


		} else {
			String s = e.getActionCommand();
			int k = s.indexOf(32);
			int ii = Integer.parseInt(s.substring(0, k));
			int jj = Integer.parseInt(s.substring(k + 1, s.length()));
			I = ii;
			J = jj;
			
			for(ii=0;ii<9;ii++) {
				for(jj=0;jj<9;jj++) {
					if (!(btSolver[ii][jj].getText()==null || btSolver[ii][jj].getText().equals(""))) {
						if (A[I][J] == A[ii][jj]) {
							btSolver[ii][jj].setBackground(Color.BLACK);
							btSolver[ii][jj].setForeground(Color.WHITE);
						}
					}
					
				}
			}


		}

	}


}
