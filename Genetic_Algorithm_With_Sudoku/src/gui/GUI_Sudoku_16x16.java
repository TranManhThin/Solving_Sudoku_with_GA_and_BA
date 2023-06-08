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
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.json.simple.parser.ParseException;


import connection.Connect_Database;
import gaWithBa.ConstantGA;



public class GUI_Sudoku_16x16 extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(0,6,0,0,4,2,0,16,1,9,13,0,0,0,0,7));
	ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(0,10,0,8,0,12,0,7,15,0,0,0,9,0,11,0));
	ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,10,7,0,0,6,0,3,0,4));
	ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(16,0,7,0,0,0,9,13,0,8,2,5,0,14,12,15));
	ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(13,0,1,0,12,0,14,0,0,2,0,0,15,11,7,5));
	ArrayList<Integer> a6 = new ArrayList<>(Arrays.asList(7,4,0,2,8,0,11,0,0,13,5,0,0,0,16,0));
	ArrayList<Integer> a7 = new ArrayList<>(Arrays.asList(14,8,0,0,0,0,0,0,0,0,3,0,1,10,0,0));
	ArrayList<Integer> a8 = new ArrayList<>(Arrays.asList(0,0,0,5,0,0,0,0,14,0,0,0,2,0,13,8));
	ArrayList<Integer> a9 = new ArrayList<>(Arrays.asList(0,2,0,0,0,3,0,0,0,12,6,0,14,15,0,13));
	ArrayList<Integer> a10 = new ArrayList<>(Arrays.asList(1,11,0,0,0,0,0,0,0,0,0,2,7,0,0,12));
	ArrayList<Integer> a11 = new ArrayList<>(Arrays.asList(0,15,4,3,0,0,0,0,13,1,8,16,0,9,0,2));
	ArrayList<Integer> a12 = new ArrayList<>(Arrays.asList(12,0,0,0,10,0,0,9,11,0,0,7,8,0,3,16));
	ArrayList<Integer> a13 = new ArrayList<>(Arrays.asList(0,9,3,0,1,8,13,4,0,6,7,0,0,0,15,0));
	ArrayList<Integer> a14 = new ArrayList<>(Arrays.asList(0,0,8,1,0,0,0,15,0,16,0,0,0,0,0,0));
	ArrayList<Integer> a15 = new ArrayList<>(Arrays.asList(0,0,0,0,3,11,0,0,0,10,0,8,0,0,0,0));
	ArrayList<Integer> a16 = new ArrayList<>(Arrays.asList(6,0,15,0,0,0,16,0,0,0,1,3,0,0,9,0));
	ArrayList<ArrayList<Integer>> sudoku = new ArrayList<>(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12
			,a13,a14,a15,a16));
	private static final long serialVersionUID = 1L;
	private String randomSudoku;
	private Font font;
	private int I, J;
	private int LV = 0;
	private JPanel jPanel1, jPanel2, jPanel3, jPanelMain, jPanel4;
	private JLabel title, lblSoLuongQT, lblLaiGhep, lblDotBien, lblTienHoa, lblSoDao, lblTienHoaDao;
	private JTextField txtSoLuongQT, txtLaiGhep, txtTienHoa, txtDotBien, txtSoDao, txtTienHoaDao;
	private JComboBox<String> level;
	private JButton jButtonNewGame, jSolver, jButtonThoat, jSolverBA;
	private JTextField bt[][] = new JTextField[16][17];
	private JButton btSolver[][] = new JButton[16][17];
	private int[][] a = new int[16][17];
	private int[][] A = new int[16][17];
	private ArrayList<ArrayList<Integer>> dataLine = new ArrayList<>();
	public GUI_Sudoku_16x16(int lv) {
		// TODO Auto-generated constructor stub
		super("18019701 - Tran Manh Thin - DHCNTT14");
		this.setSize(1500, 800);
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
		jPanel2.setLayout(new GridLayout(16, 16));
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				bt[i][j] = new JTextField();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setFont(new Font("UTM Micra", 1, 10));
				bt[i][j].setForeground(Color.black);
				jPanel2.add(bt[i][j]);
				bt[i][j].setHorizontalAlignment(SwingConstants.CENTER);
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
		jPanel4.setLayout(new GridLayout(16, 16));
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				btSolver[i][j] = new JButton();
				btSolver[i][j].addActionListener(this);
				btSolver[i][j].addKeyListener(this);
				btSolver[i][j].setActionCommand(i + " " + j);
				btSolver[i][j].setBackground(Color.white);
				btSolver[i][j].setFont(new Font("UTM Micra", 1, 10));
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
					image = ImageIO.read(new File("src/images/foooter.png"));
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
		jButtonNewGame.setBounds(800,20,110,50);
		jSolver = new RoundedButton("Giải");
		jSolverBA = new RoundedButton("Biểu đồ");
		jSolverBA.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jSolverBA.setBounds(1215, 20, 110, 50);
		jSolver.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jSolver.setBounds(1090,20,110,50);
		jButtonThoat = new RoundedButton("Thoát");
		jButtonThoat.setBackground(Color.WHITE);
		jButtonThoat.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jButtonThoat.setBounds(1340, 20, 110, 50);
		
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
		level.setBounds(925, 20, 150,50);
		level.addItem("Dễ");
		level.addItem("Tự nhập");
		level.setSelectedIndex(LV);
		jPanel3.add(jButtonNewGame);
		jPanel3.add(level);
		jPanel3.add(jSolver);
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
		jPanel3.add(jSolverBA);
		
		jSolverBA.setEnabled(false);
		this.add(jPanel1, BorderLayout.NORTH);
		this.add(jPanelMain, BorderLayout.CENTER);
		this.add(jPanel3, BorderLayout.SOUTH);

		jButtonNewGame.addActionListener(this);
		jSolver.addActionListener(this);
		jSolverBA.addActionListener(this);
		jButtonThoat.addActionListener(this);
		txtSoLuongQT.addActionListener(this);
		txtTienHoa.addActionListener(this);
		txtLaiGhep.addActionListener(this);
		txtDotBien.addActionListener(this);
		txtSoDao.addActionListener(this);
		txtTienHoaDao.addActionListener(this);
		loadRate();
		if (LV == 1) {
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
		} else if (LV == 0) {
			createMatrix16();
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
	
	public void createMatrix16() {
		for(int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {
				if (sudoku.get(i).get(j)>0) {
					bt[i][j].setText(sudoku.get(i).get(j)+"");
					bt[i][j].setEditable(false);
				}
			}
		}
	}

//	public static void main(String[] args) {
//		new GUI_Sudoku_16x16(0).setVisible(true);
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
		char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Không cho phép nhập ký tự khác số
        }

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 16; j++) {
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
			new GUI_Sudoku_16x16(level.getSelectedIndex()).setVisible(true);
			this.dispose();
		}
		else if(o.equals(jButtonThoat)) {
			setVisible(false);
			GUI_Main main = new GUI_Main();
			main.setVisible(true);

		}else if(o.equals(jSolverBA)) {
			GUI_SplineChart chart = new GUI_SplineChart(dataLine);
			chart.setVisible(true);
		}
		else if (o.equals(jSolver)) {

			Object[] options = { "GA", "GA_With_BA"};
			int selection = JOptionPane.showOptionDialog(null, "Chọn 1 lựa chọn", "OPTION", JOptionPane.DEFAULT_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null,  options,  options[0]);
			
			jSolver.setForeground(Color.RED);
			if(selection==0) {
				dataLine = new ArrayList<>();
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>(){
					@Override
					protected String doInBackground() throws Exception {
						// TODO Auto-generated method stub
						
						ArrayList<Integer> dong1 = new ArrayList<>();
						ArrayList<Integer> dong2 = new ArrayList<>();
						ArrayList<Integer> dong3 = new ArrayList<>();
						ArrayList<Integer> dong4 = new ArrayList<>();
						ArrayList<Integer> dong5 = new ArrayList<>();
						ArrayList<Integer> dong6 = new ArrayList<>();
						ArrayList<Integer> dong7 = new ArrayList<>();
						ArrayList<Integer> dong8 = new ArrayList<>();
						ArrayList<Integer> dong9 = new ArrayList<>();
						ArrayList<Integer> dong10 = new ArrayList<>();
						ArrayList<Integer> dong11 = new ArrayList<>();
						ArrayList<Integer> dong12 = new ArrayList<>();
						ArrayList<Integer> dong13 = new ArrayList<>();
						ArrayList<Integer> dong14 = new ArrayList<>();
						ArrayList<Integer> dong15 = new ArrayList<>();
						ArrayList<Integer> dong16 = new ArrayList<>();
						for (int i = 0; i < 16; i++) {
							for (int j = 0; j < 16; j++) {
								if (i == 0) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong1.add(0);
									} else {
										dong1.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 1) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong2.add(0);
									} else {
										dong2.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 2) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong3.add(0);
									} else {
										dong3.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 3) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong4.add(0);
									} else {
										dong4.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 4) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong5.add(0);
									} else {
										dong5.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 5) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong6.add(0);
									} else {
										dong6.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 6) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong7.add(0);
									} else {
										dong7.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 7) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong8.add(0);
									} else {
										dong8.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 8) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong9.add(0);
									} else {
										dong9.add(Integer.parseInt(bt[i][j].getText()));
									}
								}else if (i == 9) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong10.add(0);
									} else {
										dong10.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 10) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong11.add(0);
									} else {
										dong11.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 11) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong12.add(0);
									} else {
										dong12.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 12) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong13.add(0);
									} else {
										dong13.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 13) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong14.add(0);
									} else {
										dong14.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 14) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong15.add(0);
									} else {
										dong15.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 15) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong16.add(0);
									} else {
										dong16.add(Integer.parseInt(bt[i][j].getText()));
									}
								}		
			
							}
						}
						ArrayList<ArrayList<Integer>> sudokuPuzzle = new ArrayList<>(Arrays.asList(dong1, dong2, dong3,dong4,dong5,dong6,dong7,dong8,dong9,dong10,dong11,
								dong12,dong13,dong14,dong15,dong16));
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
					
						
						ga.SudokuGeneticAlgorithm imp = new ga.SudokuGeneticAlgorithm(sudokuPuzzle,(int)soLuongQT*soDao, vongDoiTienHoa, tiLeLai, tiLeDotBien, soDao, vongDoiDao,16);
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
							jSolverBA.setEnabled(true);
							if (!part1.equals("[]")) {
								String part2 = part[1];
								
								part1 = part1.replace("[", "").replace("]", "");
								String[] strArr = part1.split(",");
								result = new ArrayList<>();
								for(String s: strArr) {
									result.add(Integer.parseInt(s.trim()));
								}
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {
										A[i][j] = result.get(i * 16 + j);
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
								jSolver.setForeground(Color.black);
							
							}
							else{
								JOptionPane.showMessageDialog(rootPane, "Vui lòng thử lại sau!","Message",JOptionPane.INFORMATION_MESSAGE);
								jSolver.setForeground(Color.black);
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
			}else {

				
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>(){
					@Override
					protected String doInBackground() throws Exception {
						// TODO Auto-generated method stub
						dataLine = new ArrayList<>();
						ArrayList<Integer> dong1 = new ArrayList<>();
						ArrayList<Integer> dong2 = new ArrayList<>();
						ArrayList<Integer> dong3 = new ArrayList<>();
						ArrayList<Integer> dong4 = new ArrayList<>();
						ArrayList<Integer> dong5 = new ArrayList<>();
						ArrayList<Integer> dong6 = new ArrayList<>();
						ArrayList<Integer> dong7 = new ArrayList<>();
						ArrayList<Integer> dong8 = new ArrayList<>();
						ArrayList<Integer> dong9 = new ArrayList<>();
						ArrayList<Integer> dong10 = new ArrayList<>();
						ArrayList<Integer> dong11 = new ArrayList<>();
						ArrayList<Integer> dong12 = new ArrayList<>();
						ArrayList<Integer> dong13 = new ArrayList<>();
						ArrayList<Integer> dong14 = new ArrayList<>();
						ArrayList<Integer> dong15 = new ArrayList<>();
						ArrayList<Integer> dong16 = new ArrayList<>();
						for (int i = 0; i < 16; i++) {
							for (int j = 0; j < 16; j++) {
								if (i == 0) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong1.add(0);
									} else {
										dong1.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 1) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong2.add(0);
									} else {
										dong2.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 2) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong3.add(0);
									} else {
										dong3.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 3) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong4.add(0);
									} else {
										dong4.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 4) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong5.add(0);
									} else {
										dong5.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 5) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong6.add(0);
									} else {
										dong6.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 6) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong7.add(0);
									} else {
										dong7.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 7) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong8.add(0);
									} else {
										dong8.add(Integer.parseInt(bt[i][j].getText()));
									}
								} else if (i == 8) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong9.add(0);
									} else {
										dong9.add(Integer.parseInt(bt[i][j].getText()));
									}
								}else if (i == 9) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong10.add(0);
									} else {
										dong10.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 10) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong11.add(0);
									} else {
										dong11.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 11) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong12.add(0);
									} else {
										dong12.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 12) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong13.add(0);
									} else {
										dong13.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 13) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong14.add(0);
									} else {
										dong14.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 14) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong15.add(0);
									} else {
										dong15.add(Integer.parseInt(bt[i][j].getText()));
									}
								}
								else if (i == 15) {
									if (bt[i][j].getText().equals("") || bt[i][j].getText() == null) {
										dong16.add(0);
									} else {
										dong16.add(Integer.parseInt(bt[i][j].getText()));
									}
								}		
			
							}
						}
						ArrayList<ArrayList<Integer>> sudokuPuzzle = new ArrayList<>(Arrays.asList(dong1, dong2, dong3,dong4,dong5,dong6,dong7,dong8,dong9,dong10,dong11,
								dong12,dong13,dong14,dong15,dong16));
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
					
						
						gaWithBa.SudokuGeneticAlgorithm imp = new gaWithBa.SudokuGeneticAlgorithm(sudokuPuzzle,(int)soLuongQT*soDao, vongDoiTienHoa, tiLeLai, tiLeDotBien, soDao, vongDoiDao,16);
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
							jSolverBA.setEnabled(true);
							if (!part1.equals("[]")) {
								String part2 = part[1];
								
								part1 = part1.replace("[", "").replace("]", "");
								String[] strArr = part1.split(",");
								result = new ArrayList<>();
								for(String s: strArr) {
									result.add(Integer.parseInt(s.trim()));
								}
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {
										A[i][j] = result.get(i * 16 + j);
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
								jSolver.setForeground(Color.black);
						
							}
							else{
								JOptionPane.showMessageDialog(rootPane, "Vui lòng thử lại sau!","Message",JOptionPane.INFORMATION_MESSAGE);
								jSolver.setForeground(Color.black);
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
			
			
			

		} else {
			String s = e.getActionCommand();
			int k = s.indexOf(32);
			int ii = Integer.parseInt(s.substring(0, k));
			int jj = Integer.parseInt(s.substring(k + 1, s.length()));
			I = ii;
			J = jj;
			
			for(ii=0;ii<16;ii++) {
				for(jj=0;jj<16;jj++) {
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
