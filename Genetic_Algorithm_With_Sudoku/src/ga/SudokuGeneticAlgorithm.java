package ga;

import java.util.ArrayList;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;





public class SudokuGeneticAlgorithm {
	private ArrayList<ArrayList<Integer>> cauHoiSudoku; // Van de sudoku, cau hoi duoc nhap
	private ArrayList<Chromosome> quanThe = new ArrayList<Chromosome>();// Quan the
	private ArrayList<Integer> doThichNghiQuanThe = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<Chromosome>> tapHopQuanTheDao = new ArrayList<ArrayList<Chromosome>>();
	private ArrayList<ArrayList<Integer>> tapHopDoThichNghiQuanTheDao = new ArrayList<ArrayList<Integer>>();
	
	private boolean daDangThap = false;// xet tinh da dang cua quan the la thap hay cao
	private ArrayList<Integer> ketQua = new ArrayList<>(); // khai bao 1 mang tra ve du lieu can lay.
	private String thongBao = ""; // Biến kiểu string chứa thông tin cần trả về
	private Timer timer = new Timer();
	private int soGiay = 0;
	private int vongDoiTienHoa = 0;
	private String tienHoa = "";
	private int soLuongQT;
	private int vongDoi;
	private double tiLeLaiGhep;
	private double tiLeDotBien;
	private int soDao;
	private int vongDoiMoiDao;
	private int kichThuocSudoku;
	private ArrayList<Integer> lineChart1 = new ArrayList<>();
	private ArrayList<Integer> lineChart2 = new ArrayList<>();
	private ArrayList<Integer> lineChart3 = new ArrayList<>();
	private ArrayList<Integer> lineChart4 = new ArrayList<>();
	private ArrayList<Integer> lineChart5 = new ArrayList<>();
	private ArrayList<Integer> lineChart6 = new ArrayList<>();
	@Override
	public String toString() {
		return ketQua+";"+thongBao+";"+lineChart1+";"+lineChart2+";"+lineChart3+";"+lineChart4+";"+lineChart5+";"+lineChart6+";";
	}

	/**
	 * Constructor
	 * @param cauHoiSudoku
	 */
	public SudokuGeneticAlgorithm(ArrayList<ArrayList<Integer>> cauHoiSudoku, int soLuongQT, int vongDoi, double tiLeLaiGhep, double tiLeDotBien, int soDao, int vongDoiMoiDao, int kichThuocSudoku) {
		this.cauHoiSudoku = cauHoiSudoku;
		this.soLuongQT = soLuongQT;
		this.vongDoi = vongDoi;
		this.tiLeLaiGhep = tiLeLaiGhep;
		this.tiLeDotBien = tiLeDotBien;
		this.soDao = soDao;
		this.vongDoiMoiDao = vongDoiMoiDao;
		this.kichThuocSudoku = kichThuocSudoku;
	}
	
	/**
	 * @param chaMe
	 * @param chuKy
	 * @param dao
	 * @return
	 */
	public Chromosome laiGhep(ArrayList<Chromosome> chaMe, int chuKy, int dao) {
		Chromosome con = new Chromosome();// Tao 1 ca the moi 

		// Cap nhat ti le lai ghep
//		double reCombRate = ConstantGA.TI_LE_LAI_GHEP.getGiaTri();
		double reCombRate = this.tiLeLaiGhep;
		double reCombOrNot = new Random().nextDouble();

		// Thuc hien lai ghep xac suat
		if (reCombOrNot <= reCombRate) {
			con = laiGhepPMX(chaMe);
		} else { 
			con = chaMe.get(0);
		}

		return con;// return
	}



	/**
	 * @param chaMe
	 * @return
	 */
	private Chromosome laiGhepPMX(ArrayList<Chromosome> chaMe) {
		// TODO Auto-generated method stub
		Chromosome con = new Chromosome();//

		// Ap dung moi dong thuc hien lai PMX
		for (int hang = 0; hang < this.kichThuocSudoku; hang++) {
			ArrayList<Integer> hangCon = new ArrayList<Integer>(this.cauHoiSudoku.get(hang)); // Copy 1 dong
			ArrayList<ArrayList<Integer>> pmxHangChaMe = new ArrayList<ArrayList<Integer>>(this.kichThuocSudoku);

			// Them 2 hang moi vao pmxHangChaMe
			for (int i = 0; i < (int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri(); i++) {
				pmxHangChaMe.add(new ArrayList<Integer>());
			}

			for (int viTriPhanTu = 0; viTriPhanTu < this.kichThuocSudoku; viTriPhanTu++) {
				for (int parentCount = 0; parentCount < (int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri(); parentCount++) {
					pmxHangChaMe.get(parentCount)
							.add(chaMe.get(parentCount).getGiaiPhapSudoku().get(hang).get(viTriPhanTu));
				}
			}

			int diemCat1 = (int) (Math.random() * chaMe.get(0).getGiaiPhapSudoku().get(0).size());
			int diemCat2 = (int) (Math.random() * chaMe.get(0).getGiaiPhapSudoku().get(0).size());
			if (diemCat1 > diemCat2) {
				int temp = diemCat1;
				diemCat1 = diemCat2;
				diemCat2 = temp;
			}
			
			

			for (int i = diemCat1; i <= diemCat2; i++) {
				hangCon.set(i, pmxHangChaMe.get(0).get(i));
			}
			for (int i = diemCat1; i <= diemCat2; i++) {
				int gene = pmxHangChaMe.get(1).get(i);
				if (!hangCon.contains(gene)) {
					int j = i;
					while (hangCon.get(j) != 0) {
						j = pmxHangChaMe.get(1).indexOf(hangCon.get(j));
					}
					hangCon.set(j, gene);
				}
			}
			for (int i = 0; i < pmxHangChaMe.get(0).size(); i++) {
				if (hangCon.get(i) == 0) {
					hangCon.set(i, pmxHangChaMe.get(1).get(i));
				}
			}
			con.getGiaiPhapSudoku().add(hangCon);

		}

		con.setDoThichNghi(con.tinhDoThichNghi(this.kichThuocSudoku));
		return con;
	}
	
	/**
	 * @param chuKy
	 * @param quanTheDao
	 * @param doThichNghiQuanTheDao
	 * @return
	 */
	public ArrayList<ArrayList<Chromosome>> chonLocChaMe(int chuKy, ArrayList<Chromosome> quanTheDao,
			ArrayList<Integer> doThichNghiQuanTheDao) {
		ArrayList<ArrayList<Chromosome>> capCaMe = new ArrayList<>();
		ArrayList<Chromosome> chaMe = new ArrayList<Chromosome>((int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri());
		ArrayList<Chromosome> chaMeDao = new ArrayList<Chromosome>((int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri());
		int kichThuocChonLoc = 0;
		if (this.daDangThap) {
			kichThuocChonLoc = (int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri(); 
		} else { // nếu quần thể đa dạng
			kichThuocChonLoc = (2*(int)ConstantGA.KICH_THUOC_BO_ME.getGiaTri());

		}
		ArrayList<Integer> danhSachChonLoc = new ArrayList<Integer>(kichThuocChonLoc);//
		ArrayList<Integer> danhSachDoThichNghi = new ArrayList<Integer>(kichThuocChonLoc);// List of 5 caThe's
																						// fitness
		// Thêm các phần tử vào chuỗi ngẫu nhiên cho đến kích thước lựa chọn.
		while (danhSachChonLoc.size() < kichThuocChonLoc) {
			int randomSeq = new Random().nextInt(quanTheDao.size());
			// Nếu danh sách không chứa số ngẫu nhiên.
			if (!danhSachChonLoc.contains(randomSeq)) {
				danhSachChonLoc.add(randomSeq);
				danhSachDoThichNghi.add(doThichNghiQuanTheDao.get(randomSeq));
			}
		}

		// Tìm 2 trong số cá thể tốt nhất.
		int chaMe1 = 0; // pointer to the fittest parent.
		int chaMe2 = 1; // pointer to the second fittest parent.

		for (int i = 2; i < kichThuocChonLoc; i++) {
			if (danhSachDoThichNghi.get(i) < danhSachDoThichNghi.get(chaMe1)) {
				chaMe1 = i;
			} else if (danhSachDoThichNghi.get(i) < danhSachDoThichNghi.get(chaMe2)) {
				chaMe2 = i;
			} else {
				// Do nothing.
			}
		}

		chaMe.add(quanTheDao.get(danhSachChonLoc.get(chaMe1)));
		chaMe.add(quanTheDao.get(danhSachChonLoc.get(chaMe2)));
		
		chaMeDao.add(quanTheDao.get(danhSachChonLoc.get(chaMe2)));
		chaMeDao.add(quanTheDao.get(danhSachChonLoc.get(chaMe1)));
		
		capCaMe.add(chaMe);
		capCaMe.add(chaMeDao);
		
	
		
		return capCaMe;// return
	}
	
	
	public Chromosome dotBien(Chromosome caThe, int chuKy, int dao) {
		Chromosome caTheDotBien = null; // Khoi tao ca the dot bien ban bat dau la rong.
		// Cap nhat ti le dot bien
//		double tiLeDotBien = (1.0 - ((double) chuKy / (double) (int)ConstantGA.VONG_DOI_QUAN_THE_DAO.getGiaTri())); // Ti le dot bien bang 1 -
																							// chu ky tien hoa/chu ky
//		double tiLeDotBien = ConstantGA.TI_LE_DOT_BIEN.getGiaTri();																					// trao doi.
		double tiLeDotBien = this.tiLeDotBien;
		if (daDangThap) { // Neu do da dang qua thap
			tiLeDotBien = 0.9; 
//			caTheDotBien = dotBienHoanDoi(caThe);
			
		}
		double choPhepDotBien = new Random().nextDouble(); // Phat sinh ngau nhien 1 so trong pham vi tu [0.00.. - 1.00]

		if (choPhepDotBien <= tiLeDotBien) { // Neu ti le dot bien cao hon so random
			if (dao%2==0) {
				caTheDotBien = dotBienHoanDoi(caThe);
			}else {
				caTheDotBien = dotBienXaoTron(caThe);
			}

		} else {
			caTheDotBien = caThe;
		}

		
		return caTheDotBien;
	}
	
	
	

	/**
	 * @param caThe
	 * @return
	 */
	private Chromosome dotBienHoanDoi(Chromosome caThe) {
		// TODO Auto-generated method stub
		Chromosome caTheDotBien = new Chromosome();// Ca the dot bien

		// Lap lai tat ca cac dong
		for (int count = 0; count < this.kichThuocSudoku; count++) {

			ArrayList<Integer> hangDotBien = new ArrayList<Integer>(caThe.getGiaiPhapSudoku().get(count));
			//
			int tiLeDotBienHang = new Random().nextInt(2); // tạo 1 số random từ 0-3
			if (tiLeDotBienHang == 0) { // Nếu tạo random = 1
				int soLanHoanDoi = new Random().nextInt(2) + 1; // tạo một số nguyên ngẫu nhiên trong khoảng từ 1 đến 2 để
																// xác định số lần hoán đổi cặp giá trị trong dòng đó.
				for (int swaps = 0; swaps < soLanHoanDoi; swaps++) {
					// Tim vi tri bat dau de trao doi
					// Đảm bảo không chạm vào các phần tử cố định được đề cập trong phần tử.
					int viTriDau = -1, viTriCuoi = -1;
					// Tìm vị trí ban đầu.
					while (true) {
						viTriDau = new Random().nextInt(this.kichThuocSudoku); // Random phạm vi từ 0-8
						//
						if (this.cauHoiSudoku.get(count).get(viTriDau) == 0) { // Nếu vị trí random là số phát
																						// sinh thì thoát khỏi vòng lặp.
							break;
						}
					}

					// Tim vi tri cuoi cung
					while (true) {
						viTriCuoi = new Random().nextInt(this.kichThuocSudoku);
						// Vị trí cuối cùng phải là vị trí có giá trị ko cố định và khác với vị trí điểm
						// bắt đầu
						if (this.cauHoiSudoku.get(count).get(viTriCuoi) == 0 && viTriDau != viTriCuoi) {
							break;// thoát khỏi vòng lặp, thực hiện hoán vị.
						}
					}

					// Hoan vi cac ca the bằng phương pháp 3 ngôi
					int tempSwap = hangDotBien.get(viTriDau);
					hangDotBien.set(viTriDau, hangDotBien.get(viTriCuoi));
					hangDotBien.set(viTriCuoi, tempSwap);
				}
			}

			//
			caTheDotBien.getGiaiPhapSudoku().add(hangDotBien); // Thêm dòng vừa đột biến vào Cá thể đột biến khởi															// tạo.

		}

		caTheDotBien.setDoThichNghi(caTheDotBien.tinhDoThichNghi(this.kichThuocSudoku)); // tính toạn độ thích nghi của cá thể vừa đột
																			// biến
		return caTheDotBien;// Return.
	}
	
	

	/**
	 * @param caThe
	 * @return
	 */
	private Chromosome dotBienXaoTron(Chromosome caThe) {
		// TODO Auto-generated method stub
		Chromosome caTheDotBien = new Chromosome(); // khởi tạo cá thể đột biến.
		ArrayList<Integer> hangDotBien = null;
		// Lap lai tat ca cac hang de xao tron
		for (int hang = 0; hang < this.kichThuocSudoku; hang++) { // Duyệt qua từng hàng
			hangDotBien = new ArrayList<Integer>(this.cauHoiSudoku.get(hang)); // Lấy ra giá trị từng dòng của đề

			ArrayList<Integer> scrambleSizehang = new ArrayList<Integer>(); // Mảng số nguyên chứa giá trị hàng xáo trộn.
			ArrayList<Integer> mutatedScrambleSizehang = null; //
			int scramblehangSize = 0; // kich thuoc hang xao tron
			// Lặp lại qua tất cả các phần tử và tạo mảng mới cho đột biến Tranh giành với
			// các phần tử cố định đã bị xóa.
			for (int phanTuPosition = 0; phanTuPosition < this.kichThuocSudoku; phanTuPosition++) {
				// Nếu như giá trị của vị trí phanTuPosition ko cố định thì thêm giá trị Giải
				// pháp vào scrambleSizehang,
				// đồng thời tăng kích thước lên 1.
				if (hangDotBien.get(phanTuPosition) == 0) {
					scrambleSizehang.add(caThe.getGiaiPhapSudoku().get(hang).get(phanTuPosition));
					scramblehangSize++;
				}
			}

			// Tìm vị trí ban đầu và vị trí kết thúc trên hàng.
			int viTriDau = new Random().nextInt(scramblehangSize - 1);// Random vị trí ban đầu trong phạm vi từ (Số phần
																		// tử ko cố định trừ đi 1).
			int viTriCuoi = viTriDau;// khởi tạo phần tử kết thúc.
			//
			while (!(viTriCuoi - viTriDau > 0)) {
				viTriCuoi = new Random().nextInt(scramblehangSize);// Random đến khi vị trí kết thúc phải lớn hơn bị trí
																// ban đầu trong phạm vi (Số phần tử ko cố định)
			}

			// Khởi tạo mảng chứa giá trị đột biến có kích thước bằng Số phần tử ko cố định.
			mutatedScrambleSizehang = new ArrayList<Integer>(scrambleSizehang);
			ArrayList<Integer> scrambledPositions = new ArrayList<Integer>(viTriCuoi - viTriDau); // Mảng có kích thước
																								// bằng vị trí cuối - vị
																								// trí ban đầu
			for (int i = viTriDau; i <= viTriCuoi; i++) {
				int randomSwap = -2;
				do {
					randomSwap = new Random().nextInt(viTriCuoi - viTriDau + 1) + viTriDau; // Tạo số nguyên có phạm vị từ
																							// (Vị trí cuối - vị trí ban
																							// đầu + 1) + vị trí ban đầu
				} while (scrambledPositions.contains(randomSwap));// Vòng lặp while dừng khi

				scrambledPositions.add(randomSwap);// Thêm vị trí vào mảng vị trí được xáo trộn.

				// Thêm nó vào hàng Xáo trộn đột biến.
				mutatedScrambleSizehang.set(i, scrambleSizehang.get(randomSwap));// Put the phanTu in the small sized
																				// perdotBien.
			}

			// Bây giờ sao chép hoán vị kích thước nhỏ thành hoán vị kích thước ban đầu.
			int phanTuDotBien = 0; // Phần tử đột biến = 0;
			for (int phanTu = 0; phanTu < scramblehangSize; phanTu++) { // duyệt qua từng phần tử ko cố định
				while (true) {
					// If the position is not fixed size.
					if (hangDotBien.get(phanTuDotBien) == 0) { // Nếu phần tử ban đầu bằng 0 thì cập nhật phần tử kế
																// tiếp bằng giá trị hàng Xáo trộn đột biến vị trí
																// phanTu
						hangDotBien.set(phanTuDotBien++, mutatedScrambleSizehang.get(phanTu));// Set the phanTu on the
																								// blank position.
						break;// thoát khỏi while
					}
					phanTuDotBien++;
				}
			}
			caTheDotBien.getGiaiPhapSudoku().add(hangDotBien); // thêm dòng đột biến vào mảng cá thể đột biến
		}
		caTheDotBien.setDoThichNghi(caTheDotBien.tinhDoThichNghi(this.kichThuocSudoku));// tính toán độ thích nghi của cá thể

		return caTheDotBien;// return
	}

	/**
	 * @param caThe
	 * @return
	 */
//	
	/**
	 * @param quanTheDao
	 * @param doThichNghiQuanTheDao
	 */
	public void kiemTraDoDaDang(ArrayList<Chromosome> quanTheDao, ArrayList<Integer> doThichNghiQuanTheDao) {
		this.daDangThap = false;

		ArrayList<Integer> giaTriThichNghi = new ArrayList<Integer>();
		ArrayList<Integer> demGiaTriThichNghi = new ArrayList<Integer>();

		for (int quanTheCount = 0; quanTheCount < quanTheDao.size(); quanTheCount++) {
			// Kiểm tra xem mảng có chứa một giá trị cụ thể thich hop hay không.
			if (!giaTriThichNghi.contains(doThichNghiQuanTheDao.get(quanTheCount))) {
				giaTriThichNghi.add(doThichNghiQuanTheDao.get(quanTheCount));
				demGiaTriThichNghi.add(1);
			} else {
				// lap lai qua mang
				for (int count = 0; count < giaTriThichNghi.size(); count++) {
					// Nếu phần tử khớp với gia trị trong mảng thì thêm giá trị cho nó vào
					// demGiaTriThichNghi
					if (giaTriThichNghi.get(count) == doThichNghiQuanTheDao.get(quanTheCount)) {
						demGiaTriThichNghi.set(count, demGiaTriThichNghi.get(count) + 1);
					}
				}
			}
		}

		// Kiểm tra xem có bất kỳ bộ đếm nào làm tăng một nửa quy mô dân số không.
		for (int i : demGiaTriThichNghi) {
			if (i > (quanTheDao.size() / 5)) {
				this.daDangThap = true;
			}
		}
		
		

	}
	
	
	
	/**
	 * @param con
	 * @param chuKy
	 * @param quanTheDao
	 * @param doThichNghiQuanTheDao
	 */
	public void chonLocSinhTon(Chromosome con, int chuKy, ArrayList<Chromosome> quanTheDao,
			ArrayList<Integer> doThichNghiQuanTheDao) {
//      System.out.println("Survivor Selection");

//		if (chuKy % ((int)ConstantGA.VONG_DOI_QUAN_THE_DAO.getGiaTri()) == 0) {
//			kiemTraDoDaDang(quanTheDao, doThichNghiQuanTheDao);// Kiểm tra độ đa dạng quần thể trên đảo
//		}
		
		if (chuKy==0) {
			kiemTraDoDaDang(quanTheDao, doThichNghiQuanTheDao);
		}

		if (this.daDangThap) {
			chonLocNgauNhien(con, quanTheDao, doThichNghiQuanTheDao);// Thực thiện chọn lọc sinh tồn																		// ngẫu nhiên
		} else { // Nếu trường hợp còn lại
			chonLocTrungBinhKem(con, quanTheDao, doThichNghiQuanTheDao);// Chọn lọc cá thể trung bình
																						// tệ nhất
		}

	}

	/**
	 * @param con
	 * @param quanTheDao
	 * @param doThichNghiQuanTheDao
	 */
	private void chonLocTrungBinhKem(Chromosome con, ArrayList<Chromosome> quanTheDao,
			ArrayList<Integer> doThichNghiQuanTheDao) {
		// TODO Auto-generated method stub
		double avgFitness = 0;// độ thích nghi trung bình
		// Định tuyến qua toàn bộ danh sách và tìm danh sách phù hợp.
		for (int i = 0; i < doThichNghiQuanTheDao.size(); i++) {
			avgFitness += doThichNghiQuanTheDao.get(i);
		}

		avgFitness /= quanTheDao.size();// Tính độ thích nghi trung bình của quần thể đảo bằng tổng số chia cho
												// số cá thể trên đảo

		// Lặp lại cho đến khi thay thế xong.
		while (true) {
			int randomcaThe = new Random().nextInt(quanTheDao.size()); // Tạo số random từ 0-19

			// Nếu độ thích nghi của cá thể được chọn ngẫu nhiên kém hơn mức trung bình thì
			// thay thế cá thể bằng cái thể hiện tại.

			if (doThichNghiQuanTheDao.get(randomcaThe) >= avgFitness) {
				quanTheDao.set(randomcaThe, con);
				doThichNghiQuanTheDao.set(randomcaThe, con.getDoThichNghi());
				break;
			}
		}
		
	}
	
	public void chonLocSinhTonKemNhat(Chromosome con, ArrayList<Chromosome> quanTheDao,
			ArrayList<Integer> doThichNghiQuanTheDao) {
		// TODO Auto-generated method stub
		int positionWorst = 0;// độ thích nghi trung bình
		// Định tuyến qua toàn bộ danh sách và tìm danh sách phù hợp.
		for (int i = 0; i < doThichNghiQuanTheDao.size(); i++) {
			if(quanTheDao.get(i).getDoThichNghi()>quanTheDao.get(positionWorst).getDoThichNghi()) {
				positionWorst = i;
			}
		}

		if (con.getDoThichNghi()<quanTheDao.get(positionWorst).getDoThichNghi()) {
			quanTheDao.set(positionWorst, con);
			doThichNghiQuanTheDao.set(positionWorst, con.getDoThichNghi());
		}
		
	}

	/**
	 * @param con
	 * @param quanTheDao
	 * @param doThichNghiQuanTheDao
	 */
	private void chonLocNgauNhien(Chromosome con, ArrayList<Chromosome> quanTheDao,
			ArrayList<Integer> doThichNghiQuanTheDao) {
		// TODO Auto-generated method stub
		int randomcaThe = new Random().nextInt(quanTheDao.size()); // tạo số ngẫu nhiên từ 0-19

		// thay thế cá thể ngẫu nhiên bằng cá thể mới.
		quanTheDao.set(randomcaThe, con);
		doThichNghiQuanTheDao.set(randomcaThe, con.getDoThichNghi());
		
	}
	
	/**
	 * 
	 */
	public void khoiTaoQuanTheBanDau() {
		// Khởi tạo tất cả các phần tử trong quần thể.
		System.out.println("Khoi tao quan the...");
		while(this.quanThe.size()<this.soLuongQT) {
			Chromosome caThe = new Chromosome(kichThuocSudoku,cauHoiSudoku);
			if (true) {
				this.quanThe.add(caThe);
				this.doThichNghiQuanThe.add(caThe.getDoThichNghi());
			}
		}
			
		

	}
	
	
	
	
	public int trienKhaiGiaiThuatTienHoa(ArrayList<Chromosome> quanTheDao, ArrayList<Integer> doThichNghiQuanTheDao,
			int switchchuKy, int type) {
		int viTriDich = -1; // Khởi tạo vị trí
		if (vongDoiTienHoa%500==0) {
			if (type==0) {
				lineChart1.add(vongDoiTienHoa);
				lineChart1.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}else if(type==1) {
				lineChart2.add(vongDoiTienHoa);
				lineChart2.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}else if(type==2) {
				lineChart3.add(vongDoiTienHoa);
				lineChart3.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}else if(type==3) {
				lineChart4.add(vongDoiTienHoa);
				lineChart4.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}else if(type==4) {
				lineChart5.add(vongDoiTienHoa);
				lineChart5.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}else if(type==5) {
				lineChart6.add(vongDoiTienHoa);
				lineChart6.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
			}
		}

		// Lặp qua từng chu kỳ
		for (int chuKy = 0; chuKy < this.vongDoiMoiDao; chuKy++) {
			// Khởi chạy tìm cha mẹ sau đó cho lai ghép, đột biến và cuối cùng là chọn lọc
//			Chromosome con1 = dotBien(laiGhep(chonCheMeNgauNhien(quanTheDao, doThichNghiQuanTheDao).get(0), chuKy, type), chuKy, type);
//			Chromosome con2 = dotBien(laiGhep(chonCheMeNgauNhien( quanTheDao, doThichNghiQuanTheDao).get(1), chuKy, type), chuKy, type);
//			
			Chromosome con1 = dotBien(laiGhep(chonLocChaMe(chuKy, quanTheDao, doThichNghiQuanTheDao).get(0), chuKy, type), chuKy, type);
			Chromosome con2 = dotBien(laiGhep(chonLocChaMe(chuKy, quanTheDao, doThichNghiQuanTheDao).get(1), chuKy, type), chuKy, type);
			if(con1.getDoThichNghi()<con2.getDoThichNghi()) {
				chonLocSinhTon(con1, chuKy, quanTheDao, doThichNghiQuanTheDao);
			}else {
				chonLocSinhTon(con2, chuKy, quanTheDao, doThichNghiQuanTheDao);
			}
			
			System.out.println("Đảo "+ ((int)type+(int)1)+" - Chu kỳ: "+ ((int)vongDoiTienHoa+(int)chuKy) + ": "
			+getCaTheTotNhatVaXauNhat(quanTheDao, doThichNghiQuanTheDao));
			

			if (doThichNghiQuanTheDao.contains(0)) {

				for (int findPosition = 0; findPosition < quanTheDao.size(); findPosition++) {
					if (doThichNghiQuanTheDao.get(findPosition) == 0) {
						viTriDich = findPosition;
						thongBao += "- Chu kỳ thứ: " + ((int)vongDoiTienHoa+(int)chuKy) + "\n- Cá thể số: " + (viTriDich + 1)+"/"+quanTheDao.size()
								+ " trong quần thể.\n- Đảo: "+((int)type+(int)1)+"\n- Thời gian xử lý mất: " + soGiay / 1000 + "."
								+ soGiay % 1000 + " giây.";
//						System.out.println("Final : " + quanTheDao.get(findPosition));

						ketQua = quanTheDao.get(findPosition).ketQua();
						
						if (type==0) {
							lineChart1.add(vongDoiTienHoa+chuKy);
							lineChart1.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}else if(type==1) {
							lineChart2.add(vongDoiTienHoa+chuKy);
							lineChart2.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}else if(type==2) {
							lineChart3.add(vongDoiTienHoa+chuKy);
							lineChart3.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}else if(type==3) {
							lineChart4.add(vongDoiTienHoa+chuKy);
							lineChart4.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}else if(type==4) {
							lineChart5.add(vongDoiTienHoa+chuKy);
							lineChart5.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}else if(type==5) {
							lineChart6.add(vongDoiTienHoa+chuKy);
							lineChart6.add(thichNghiTotNhat(quanTheDao, doThichNghiQuanTheDao));
						}
						break;
					}
					
		
				} // end for.

				if (viTriDich != -1)
					break;
			}
			
			
		}

		return viTriDich;
	}
	
	/**
	 * @param tapHopQuanTheDao
	 * @param tapHopDoThichNghiQuanTheDao
	 * @param chuKy
	 */
	public void traoDoiCaTheGiuaCacDao(ArrayList<ArrayList<Chromosome>> tapHopQuanTheDao,
			ArrayList<ArrayList<Integer>> tapHopDoThichNghiQuanTheDao, int chuKy) {
		// iterate through each daos.
		for (int dao = 0; dao < this.soDao; dao++) {
			int caTheNgauNhien = new Random().nextInt(tapHopQuanTheDao.get(dao).size());
//			ArrayList<Integer> traoDoi5CaThe = new ArrayList<>();
//			for(int i=0;i<tapHopQuanTheDao.get(dao).size();i++) {
//				traoDoi5CaThe.add(i);
//			}
//			Collections.shuffle(traoDoi5CaThe);
//			traoDoi5CaThe = new ArrayList<>(traoDoi5CaThe.subList(0,1));
			
			// sao chép cá thể tốt nhất vào các quần thể của chúng
			for (int copyDao = 0; copyDao < this.soDao; copyDao++) {
				if (copyDao != dao){// Không sao chép vào đảo của nó
//					chonLocSinhTon(tapHopQuanTheDao.get(dao).get(caTheNgauNhien), chuKy,
//							tapHopQuanTheDao.get(copyDao), tapHopDoThichNghiQuanTheDao.get(copyDao));
					chonLocSinhTonKemNhat(tapHopQuanTheDao.get(dao).get(caTheNgauNhien), tapHopQuanTheDao.get(copyDao), tapHopDoThichNghiQuanTheDao.get(copyDao));
//					for(int i=0;i<traoDoi5CaThe.size();i++) {
//						chonLocSinhTonRandom(tapHopQuanTheDao.get(dao).get(traoDoi5CaThe.get(i)), tapHopQuanTheDao.get(copyDao), tapHopDoThichNghiQuanTheDao.get(copyDao));
//					}
				
			}
			}
		}
	}
	
	
	
	/**
	 * 
	 */
	public void trienKhaiGiaiThuatDiTruyen() {
		timer.scheduleAtFixedRate(new TimerTask() { //bắt đầu tính giờ
			@Override
			public void run() {
				soGiay++;
			}
		}, 0, 1);
		
		khoiTaoQuanTheBanDau(); //khởi tạo quần thể ban đầu
		
		for (int dao = 0; dao < this.soDao; dao++) {
			//tách quần thể ban đầu vào các đảo 

			int viTriDau = dao * (this.soLuongQT / this.soDao); // điểm đầu quần thể đảo thứ n
			int viTriCuoi = viTriDau + (this.soLuongQT / this.soDao); // điểm cuối quần thể đảo thứ n
			tapHopQuanTheDao.add(new ArrayList<Chromosome>(quanThe.subList(viTriDau, viTriCuoi)));
			tapHopDoThichNghiQuanTheDao.add(new ArrayList<Integer>(doThichNghiQuanThe.subList(viTriDau, viTriCuoi)));
		}
		for (int chuKy = 0; chuKy < this.vongDoi; chuKy += this.vongDoiMoiDao) {
			for (int dao = 0; dao < this.soDao; dao++) {
				// Run EA for all the SO_DAO.
				if (trienKhaiGiaiThuatTienHoa(tapHopQuanTheDao.get(dao), tapHopDoThichNghiQuanTheDao.get(dao), chuKy,
						dao) != -1) {
					
					chuKy = this.vongDoi;
					timer.cancel();
					break;
				}
				
			}
			vongDoiTienHoa+=this.vongDoiMoiDao;

			if (chuKy%(2*this.vongDoiMoiDao)==0) {
				traoDoiCaTheGiuaCacDao(tapHopQuanTheDao, tapHopDoThichNghiQuanTheDao, chuKy);
			}
				
		
		}
	}
	
	
	public String quaTrinhTienHoa() {
		String s = tienHoa;
		return s;
	}
	public String getCaTheTotNhatVaXauNhat(ArrayList<Chromosome> tapHopQuanTheDao,ArrayList<Integer> tapHopDoThichNghiQuanTheDao) {
		int caTheTotNhat = 0;
		int caTheXauNhat = 0;
		
		for(int viTri = 0; viTri < tapHopDoThichNghiQuanTheDao.size();viTri++) {
			if (tapHopDoThichNghiQuanTheDao.get(viTri) < tapHopDoThichNghiQuanTheDao.get(caTheTotNhat)) {
				caTheTotNhat = viTri;
			}
			if (tapHopDoThichNghiQuanTheDao.get(viTri)>tapHopDoThichNghiQuanTheDao.get(caTheXauNhat)) {
				caTheXauNhat = viTri;
			}
		}
		
		int thichNghiTotNhat = tapHopDoThichNghiQuanTheDao.get(caTheTotNhat);
		int thichNghiXauNhat = tapHopDoThichNghiQuanTheDao.get(caTheXauNhat);
		String chuoi = "Thích nghi tốt nhất: "+ thichNghiTotNhat + " - Thích nghi xấu nhất: "+ thichNghiXauNhat;
		return chuoi;
	}
	
	public int thichNghiTotNhat(ArrayList<Chromosome> tapHopQuanTheDao,ArrayList<Integer> tapHopDoThichNghiQuanTheDao) {
		int caTheTotNhat = 0;
		
		for(int viTri = 0; viTri < tapHopDoThichNghiQuanTheDao.size();viTri++) {
			if (tapHopDoThichNghiQuanTheDao.get(viTri) < tapHopDoThichNghiQuanTheDao.get(caTheTotNhat)) {
				caTheTotNhat = viTri;
			}
	
		}
		
		int thichNghiTotNhat = tapHopDoThichNghiQuanTheDao.get(caTheTotNhat);
		return thichNghiTotNhat;
	}
	
	
	
}
