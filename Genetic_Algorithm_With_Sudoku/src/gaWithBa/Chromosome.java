package gaWithBa;

import java.util.ArrayList;
import java.util.Random;


public class Chromosome {
	private ArrayList<ArrayList<Integer>> giaiPhapSudoku; // 1 ca the la 1 giai phap sudoku 9x9
	private int doThichNghi; // do thich nghi

	/**
	 * Constructor bat dau tao gia tri ngau nhien khong trung o hang.
	 * 
	 * @param sudoku: Truyen du lieu cau hoi Sudoku vao va luu tru vi tri co dinh.
	 *                Nhung o trong duoc thay the bang so 0.
	 */

	public Chromosome(int kichThuocSudoku, ArrayList<ArrayList<Integer>> sudoku, ArrayList<ArrayList<Integer>> destinationSoltion) {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(kichThuocSudoku);

		// Lap lai tat ca cac hang cua Ma tran sudoku de tao ra 1 giai phap ngau nhien
		for (ArrayList<Integer> hangCauDo : sudoku) {
			ArrayList<Integer> hangGiaiPhap = new ArrayList<Integer>(); 

			// Lap lai tat ca phan tu tren hang
			for (Integer phanTu : hangCauDo) {
				if (phanTu != 0) {
					hangGiaiPhap.add(phanTu);
				} else {
					while (true) {
						int phatSinhSo = new Random().nextInt(kichThuocSudoku) + 1; // Ngau nhien phat sinh
																								// so tu 1 - 9.
						// Neu phan tu moi Phat sinh ngau nhien khong co trong giai phap va cau hoi thi
						// them vao giai phap.
						if (!hangCauDo.contains(phatSinhSo) && !hangGiaiPhap.contains(phatSinhSo)) {
							hangGiaiPhap.add(phatSinhSo);
							break;// Ngat vong lap, den phan tu tiep theo.
						}
					}
				}
			}
			giaiPhapSudoku.add(hangGiaiPhap);// Them tat ca hang vao giai phap.
		}

		setDoThichNghi(tinhDoThichNghi(kichThuocSudoku,destinationSoltion));
	}

	/**
	 * Constructor duoc su dung cho viec khoi tao giai phap cho 1 o trong. Ham tao 1
	 * ca the moi tu dau khong can ngau nhien hoa
	 */
	public Chromosome() {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(); 
		setDoThichNghi(0);
	}

	/**
	 * Ham dung de tinh toan do thich nghi cua ca the
	 * 
	 * @return tra ve do thich nghi cua quan the.
	 */

	public int tinhDoThichNghi(int kichThuocSudoku, ArrayList<ArrayList<Integer>> destinationSolution) {
		int doThichNghi = 0; // khoi tao do thich nghi ban dau = 0;

		// Lap lai qua tat ca cac cot cua giai phap sudoku
		for (int hang = 0; hang < kichThuocSudoku; hang++) {
			for (int cot = 0; cot < kichThuocSudoku; cot++) {
				if (this.giaiPhapSudoku.get(hang).get(cot) != destinationSolution.get(hang).get(cot)) {
					doThichNghi++;
				}
			}
		}

		return doThichNghi;
	}

	/**
	 * Phuong thuc tra 1 mang voi so lan xuat hien cua 1 phan tu trong cot
	 * 
	 * @param cotIndex la cot can tim
	 * @return tra ve 1 mang voi dac ta so phan tu
	 */
	

	/**
	 * Phuong thuc tra ve 1 mang voi so lan xuat hien 1 phan tu trong hop 3x3
	 * 
	 * @param hang la hang bat dau cua hop
	 * @param cot  la cot bat dau cua hop
	 * @return Tra ve mang voi dac ta so phan tu
	 */

	// set
	public ArrayList<ArrayList<Integer>> getGiaiPhapSudoku() {
		return giaiPhapSudoku;
	}

	// Set tat ca giai phap
	public void setGiaiPhapSudoku(ArrayList<ArrayList<Integer>> giaiPhapSudoku) {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(giaiPhapSudoku);
	}

	// set 1 dong cu the.
	public void setGiaiPhapSudoku(int hangIndex, ArrayList<Integer> sudokuhang) {
		this.giaiPhapSudoku.set(hangIndex, sudokuhang);
	}

	// Set 1 phan tu cu the
	public void setGiaiPhapSudoku(int hangIndex, int cotIndex, int phanTu) {
		this.giaiPhapSudoku.get(hangIndex).set(cotIndex, phanTu);
	}

	// lay ra so thich nghi
	public int getDoThichNghi() {
		return doThichNghi;
	}

	// set do thich nghi
	public void setDoThichNghi(int doThichNghi) {
		this.doThichNghi = doThichNghi;
	}

	/**
	 * Tra ve toString cho class chay trong console log
	 * 
	 * @return Gia tri cua 1 ca the
	 */
	@Override
	public String toString() {
		String result = "";
		ArrayList<Integer> all = new ArrayList<>();

		for (ArrayList<Integer> sudokuhang : this.giaiPhapSudoku) {
			all.addAll(sudokuhang);
		}
		result += all.toString();
		return result;
	}

	/**
	 * Phuong thuc nay tra ve 1 mang bao gom tap hop tat ca cac mang con cua ca the
	 * 
	 * @return tra ve 1 mang gom 81 so
	 */
	public ArrayList<Integer> ketQua() {
		ArrayList<Integer> all = new ArrayList<>();

		for (ArrayList<Integer> sudokuhang : this.giaiPhapSudoku) {
			all.addAll(sudokuhang);
		}
		return all;
	}
}
