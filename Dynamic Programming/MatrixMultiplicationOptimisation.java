import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
class MatrixMultiplicationOptimisation{
	ArrayList<Matrix> matrices;
	long[][] dpTable;
	int[][] partitionTable;
	long minOp;

	MatrixMultiplicationOptimisation(ArrayList<Matrix> matrices){
		this.matrices = matrices;
		int dpTableSize = matrices.size()+1;
		dpTable = new long[dpTableSize][dpTableSize];
		partitionTable = new int[dpTableSize][dpTableSize];
		for(int r = 1; r<=dpTableSize-1; r++){
			for(int i = 1; i<=dpTableSize-1-r; i++){
				int j=i+r;
				dpTable[i][j]=-1;
				for(int k = i; k<=j-1; k++){
					long temp = dpTable[i][k]+dpTable[k+1][j]+matrices.get(i-1).getRow()*matrices.get(k-1).getColumn()*matrices.get(j-1).getColumn();
					if(dpTable[i][j]==-1||temp<dpTable[i][j]){
						dpTable[i][j]=temp;
						partitionTable[i][j]=k;
					}
				}
			}
		}
		minOp = dpTable[1][dpTableSize-1];
	}
	public long minOp(){
		return minOp;
	}
	public ArrayList<Integer> optimalPartition(){
		Stack<Integer> s = new Stack<Integer>();
		optimalPartition(1, matrices.size(), s);
		return new ArrayList<Integer>(s);

	}
	private void optimalPartition(int start, int end, Stack<Integer> stack){
		if(start == end){
			return;
		}
		int p = partitionTable[start][end];
		stack.push(p);
		optimalPartition(start, p ,stack);
		optimalPartition(p+1, end, stack);

	}
	public static void main(String[] args){
		Matrix m1 = new Matrix(6,6);
		Matrix m2 = new Matrix(6,3);
		Matrix m3 = new Matrix(3,4);
		Matrix m4 = new Matrix(4,4);
		Matrix m5 = new Matrix(4,8);
		ArrayList<Matrix> mList = new ArrayList<Matrix>();
		mList.add(m1);
		mList.add(m2);
		mList.add(m3);
		mList.add(m4);
		mList.add(m5);
		MatrixMultiplicationOptimisation mmo1 = new MatrixMultiplicationOptimisation(mList);
		System.out.println(mmo1.minOp());
		System.out.println(mmo1.optimalPartition());
	}
}
class Matrix{
	int row;
	int column;
	Matrix(int row, int column){
		this.row = row;
		this.column = column;
	}
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
}