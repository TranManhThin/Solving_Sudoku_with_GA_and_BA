package gaWithBa;

import java.util.ArrayList;

public class BA {
	private ArrayList<ArrayList<Integer>> sudoku;
	private int kichThuocSudoku;
	public BA(ArrayList<ArrayList<Integer>> sudoku, int kichThuocSudoku) {
		// TODO Auto-generated constructor stub
		this.sudoku = sudoku;
		this.kichThuocSudoku = kichThuocSudoku;
	}
	
	public boolean giaiQuyet() {
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		
		for(int i = 0; i<this.kichThuocSudoku;i++) {
			for(int j = 0;j<this.kichThuocSudoku;j++) {
				if (sudoku.get(i).get(j) == 0) {
					row = i;
					col = j;
					
					isEmpty = false;
				}
			}
			if (!isEmpty) {
				break;
			}
		}
		
		if (isEmpty) {
			return true;
		}
		for(int num = 1;num<=this.kichThuocSudoku;num++) {
			if (hopLe(row, col, num)) {
				sudoku.get(row).set(col, num);
				if (giaiQuyet()) {
					return true;
				}
				
				sudoku.get(row).set(col, 0);
			}
		}
		return false;
	}
	private boolean hopLe(int row, int col, int num) {
		for(int i=0;i<this.kichThuocSudoku;i++) {
			if (sudoku.get(row).get(i) == num) {
				return false;
			}
		}
		
		for(int i=0;i<this.kichThuocSudoku;i++) {
			if (sudoku.get(i).get(col) == num) {
				return false;
			}
		}
		
		int boxRow =  row - row%((int)Math.sqrt(this.kichThuocSudoku));
		int boxCol = col - col%((int)Math.sqrt(this.kichThuocSudoku));
		for(int i=boxRow;i<boxRow+((int)Math.sqrt(this.kichThuocSudoku));i++) {
			for(int j=boxCol;j<boxCol+((int)Math.sqrt(this.kichThuocSudoku));j++) {
				if (sudoku.get(i).get(j) == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<ArrayList<Integer>> ketQua(){
		return this.sudoku;
	}
	
	
}
