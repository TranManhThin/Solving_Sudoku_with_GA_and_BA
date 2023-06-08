package ga;

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
	public Chromosome(int kichThuocSudoku, ArrayList<ArrayList<Integer>> sudoku) {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(kichThuocSudoku); // Add 9 different
																								// ArrayList in the
																								// Solution.

		// Lap lai tat ca cac hang cua Ma tran sudoku de tao ra 1 giai phap ngau nhien
		for (ArrayList<Integer> hangCauDo : sudoku) {
			ArrayList<Integer> hangGiaiPhap = new ArrayList<Integer>(kichThuocSudoku); // Create a new hang
																									// for the solution.

			// Lap lai tat ca phan tu tren hang
			for (Integer phanTu : hangCauDo) {
				if (phanTu != 0) {
					hangGiaiPhap.add(phanTu);
				} else {
					while (true) {
						int phatSinhSo = new Random().nextInt(kichThuocSudoku) + 1; // Ngau nhien phat sinh
																								// so tu 1 - 9.
						if (!hangCauDo.contains(phatSinhSo) && !hangGiaiPhap.contains(phatSinhSo)) {
							hangGiaiPhap.add(phatSinhSo);
							break;// Ngat vong lap, den phan tu tiep theo.
						}
					}
					
				}
			}
			giaiPhapSudoku.add(hangGiaiPhap);// Them tat ca hang vao giai phap.
		}

		setDoThichNghi(tinhDoThichNghi(kichThuocSudoku));
	}

	/**
	 * Constructor duoc su dung cho viec khoi tao giai phap cho 1 o trong. Ham tao 1
	 * ca the moi tu dau khong can ngau nhien hoa
	 */
	public Chromosome() {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(); // Them 9 Arraylist khac
																								// nhau vao.
		setDoThichNghi(0);
	}

	/**
	 * Ham dung de tinh toan do thich nghi cua ca the Do thich nghi dua tren Diem
	 * phat cua giai phap Xet cac cot, neu xuat hien nhieu hon 1 so trong tung cot
	 * se tang 1 diem phat Cac hop con (0,0 - 2,2 ; 3,0 - 5,2 ; 6,0 - 8,2; 0,3-2,5;
	 * 3,3- 5,5 ; 6,3-8-5; 0,6 - 2,8 ; 3,6 - 5,8 ; 6,6 - 8,8) neu chua bat ky so
	 * trung lap nao thi diem trung lap se duoc them vao diem phat Parameters : Cac
	 * ca the la 1 mang 2 chieu ArrayList 9 phan tu.
	 * 
	 * @return tra ve do thich nghi cua quan the.
	 */
	public int tinhDoThichNghi(int kichThuocSudoku) {
		int doThichNghi = 0; // khoi tao do thich nghi ban dau = 0;

		// Lap lai qua tat ca cac cot cua giai phap sudoku
		for (int cot = 0; cot < kichThuocSudoku; cot++) {
			// ArrayList nay se dem so lan xuat hien phan tu tren 1 cot.
			// VD: panaltyCounter(0) = 3. co nghia la 3 lan xuat hien so 1 trong cot
			ArrayList<Integer> danhGiaViPham = new ArrayList<Integer>();
			for(int i=0;i<kichThuocSudoku;i++) {
				danhGiaViPham.add(0);
			}
			for (int hang = 0; hang < kichThuocSudoku; hang++) {
				int giaTri = this.giaiPhapSudoku.get(hang).get(cot); // Gia tri tren 1 o cu the
				danhGiaViPham.set(giaTri - 1, danhGiaViPham.get(giaTri - 1) + 1);// Tang bo dem.
			}
			// Cong tat ca diem phat
			for (Integer viPhamCot : danhGiaViPham) {
				// Neu 1 so xuat hien nhieu lan trong 1 cot
				// Them vao diem phat neu gia tri viPhamCot khong bang 1
				if (viPhamCot > 1) {
					doThichNghi += viPhamCot;
				} else if (viPhamCot == 0) {
					doThichNghi += 1;
				}
			}
		}
		
		// Dem so diem phat tren mo hop 3x3
		for (int hang = 0; hang < kichThuocSudoku; hang += Math.sqrt(kichThuocSudoku)) {
			for (int cot = 0; cot < kichThuocSudoku; cot += Math.sqrt(kichThuocSudoku)) {
				// Arraylist dem so lan xuat hien cua 1 phan tu trong 1 hop 3x3 trong cau do 9x9

				ArrayList<Integer> danhGiaViPham = new ArrayList<Integer>();
				for(int i=0;i<kichThuocSudoku;i++) {
					danhGiaViPham.add(0);
				}
				// Duyet qua tat ca box 3x3
				for (int boxHang = hang; boxHang < hang + Math.sqrt(kichThuocSudoku); boxHang++) {
					for (int boxCot = cot; boxCot < cot + Math.sqrt(kichThuocSudoku); boxCot++) {
						int giaTri = this.giaiPhapSudoku.get(boxHang).get(boxCot); // Gia tri cu the
						danhGiaViPham.set(giaTri - 1, danhGiaViPham.get(giaTri - 1) + 1);// Tang bo dem phat trong mang.
					}
				}

				// Tong tat ca diem phat trong box
				for (Integer viPhamBox : danhGiaViPham) {
					// Neu 1 so xuat hien nhieu hon 1 lan, tang diem phat.
					if (viPhamBox > 1) {
						doThichNghi += viPhamBox;
					}
					else if(viPhamBox == 0) {
						doThichNghi +=1;
					}
				}
			}
		}
		return doThichNghi;
	}

	

	// set
	public ArrayList<ArrayList<Integer>> getGiaiPhapSudoku() {
		return giaiPhapSudoku;
	}

	// Set tat ca giai phap
	public void setGiaiPhapSudoku(ArrayList<ArrayList<Integer>> giaiPhapSudoku) {
		this.giaiPhapSudoku = new ArrayList<ArrayList<Integer>>(giaiPhapSudoku);
	}

	// set 1 dong cu the.
//	public void setGiaiPhapSudoku(int hangIndex, ArrayList<Integer> sudokuhang) {
//		this.giaiPhapSudoku.set(hangIndex, sudokuhang);
//	}

	// Set 1 phan tu cu the
//	public void setGiaiPhapSudoku(int hangIndex, int cotIndex, int phanTu) {
//		this.giaiPhapSudoku.get(hangIndex).set(cotIndex, phanTu);
//	}

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
