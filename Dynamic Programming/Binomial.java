import java.math.BigInteger;
import java.util.ArrayList;
//Time and space both Theta(nk)
class Binomial {
	private ArrayList<ArrayList<BigInteger>> dpTable;
	private int n;
	private int k;
	Binomial(int n, int k) {
		this.n = n;
		this.k = k;
		dpTable = new ArrayList<ArrayList<BigInteger>>();
		//Initialise base case
		for(int i = 0; i <= n; i++) {
			ArrayList<BigInteger> currRow = new ArrayList<BigInteger>();
			for(int j = 0; j <= i; j++) {
				if(j == 0 || j == i){
					currRow.add(new BigInteger("1"));
				}else{
					currRow.add(new BigInteger("-1"));
				}
			}
			dpTable.add(currRow);
		}
		//Compute
		for(int i = 2; i <= n; i++) {
			for(int j = 1; j < i; j++) {
				dpTable.get(i).set(j, dpTable.get(i-1).get(j-1).add(dpTable.get(i-1).get(j)));
			}
		}
	}
	public BigInteger getResult() {
		return dpTable.get(n).get(k);
	}
	public void printTable() {

		for(int i = 0; i< dpTable.size(); i++) {
			for(int j = 0; j < dpTable.get(i).size(); j++) {
				System.out.print(dpTable.get(i).get(j).toString()+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Binomial b1 = new Binomial(14,6);
		b1.printTable();
		System.out.println(b1.getResult());
	}
}