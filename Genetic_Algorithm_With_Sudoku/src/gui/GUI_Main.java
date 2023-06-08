package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI_Main extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel1, jPanel2;
	private JButton jButton1, jButton2, jButton3;
	public GUI_Main() {
		// TODO Auto-generated constructor stub
		super("18019701 - Tran Manh Thin - DHCNTT14");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		jPanel1 = new JPanel(){
//			 /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			protected void paintComponent(Graphics g) {
//			        super.paintComponent(g);
//			        BufferedImage image;
//					try {
//						image = ImageIO.read(new File("src/images/header5.png"));
//						 g.drawImage(image, 0, 0, null);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			       
//			      }
//		};
		jPanel1 = new JPanel();
		jPanel1.setBackground(Color.WHITE);
		
		jPanel1.setLayout(null);
		jButton1 = new RoundedButton("");
		jButton1.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		ImageIcon icon1 = new ImageIcon(new ImageIcon("src/images/9x9.2.png").getImage().getScaledInstance(100,109, Image.SCALE_SMOOTH));
		jPanel1.add(jButton1);
		jButton1.setIcon(icon1);
		
		jButton2 = new RoundedButton("");
		jButton2.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		ImageIcon icon2 = new ImageIcon(new ImageIcon("src/images/16x16.png").getImage().getScaledInstance(100,109, Image.SCALE_SMOOTH));
		jPanel1.add(jButton2);
		jButton2.setIcon(icon2);
		
		jButton3 = new RoundedButton("");
		ImageIcon icon3 = new ImageIcon(new ImageIcon("src/images/exit.jpg").getImage().getScaledInstance(100,109, Image.SCALE_SMOOTH));
		jButton3.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		jButton3.setIcon(icon3);
		jButton3.setBounds(560, 10, 115, 123);
		JLabel lblThoat = new JLabel("Thoát");
		Font font2 = new Font("Consolas", Font.BOLD | Font.PLAIN, 20);
		lblThoat.setFont(font2);
		lblThoat.setBounds(590, 95, 100, 100);
		jPanel1.add(lblThoat);
		JLabel jLabel1 = new JLabel("9x9");
		JLabel jLabel2 = new JLabel("16x16");
		Font font1 = new Font("Tempus Sans ITC", Font.BOLD | Font.PLAIN, 20);
		jLabel1.setForeground(Color.RED);
		jLabel1.setFont(font1);
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jButton3);
		jLabel1.setBounds(50,95,100,100);
		jLabel2.setBounds(165,95,100,100);
		jLabel2.setForeground(Color.RED);
		jLabel2.setFont(font1);
		jButton1.setBounds(10,10,115,123);
		jButton2.setBounds(135,10,115,123);
//		jPanel2 = new JPanel(){
//			 /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			protected void paintComponent(Graphics g) {
//			        super.paintComponent(g);
//			        BufferedImage image;
//					try {
//						image = ImageIO.read(new File("src/images/header5.png"));
//						 g.drawImage(image, 0, 0, null);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			       
//			      }
//		};
		jPanel2 = new JPanel();
		jPanel2.setBackground(Color.WHITE);
		Font font = new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 80);
		JLabel title = new JLabel("Sudoku");
		title.setFont(font);
		title.setForeground(Color.red);
		jPanel2.add(title);
		
//		jPanel2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		add(jPanel1, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);
		
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
		
	}
//	public static void main(String[] args) {
//		new GUI_Sudoku().setVisible(true);
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(jButton1)) {
			setVisible(false);
			new ui_splashscreen.SplashScreen(null, true).setVisible(true);
			
            new GUI_Sudoku_9x9(0).setVisible(true);
		}
		else if(o.equals(jButton2)) {
			setVisible(false);
			new ui_splashscreen.SplashScreen(null, true).setVisible(true);
			
            new GUI_Sudoku_16x16(0).setVisible(true);
		}
		else if(o.equals(jButton3)) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát ?","Lưu ý",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				System.exit(0);
				
			}
		}
		
	}
}
