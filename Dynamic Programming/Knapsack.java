import java.util.ArrayList;
import java.util.Collections;
class Knapsack {
	int maxCapacity;
	ArrayList<Item> items;
	double maxValue;
	ArrayList<ArrayList<Double>> dpTable;
	boolean[][] used;
	Knapsack(int maxCapacity, ArrayList<Item> items) {
		this.maxCapacity = maxCapacity;
		this.items = items;
		int itemListSize = items.size();
		this.dpTable = new ArrayList<ArrayList<Double>>();
		this.used = new boolean[maxCapacity+1][itemListSize+1];
		ArrayList<Double> firstRow = new ArrayList<Double>(Collections.nCopies(itemListSize, 0.0));
		dpTable.add(firstRow);
		for(int i = 1; i<=maxCapacity; i++) {
			ArrayList<Double> currRow = new ArrayList<Double>();
			currRow.add(0.0);
			dpTable.add(currRow);
		}

		for(int i = 1; i<=maxCapacity; i++) {
			for(int j =1; j<=itemListSize; j++){
				if(items.get(j-1).getWeight()<=i){
					dpTable.get(i).add(Math.max(dpTable.get(i).get(j-1), dpTable.get(i-items.get(j-1).getWeight()).get(j-1)+items.get(j-1).getValue()));
					if(dpTable.get(i).get(j)==dpTable.get(i).get(j-1)){
						used[i][j]=false;
					}else{
						used[i][j]=true;
					}
				}else{
					dpTable.get(i).add(dpTable.get(i).get(j-1));
					used[i][j]=false;
				}
			}
		}
		this.maxValue = dpTable.get(maxCapacity).get(itemListSize);
	}
	public double maxValue(){
		return maxValue;
	}
	public ArrayList<Item> optimalChoice() {
		ArrayList<Item> picked = new ArrayList<Item>();
		int left = maxCapacity;
		for(int j=items.size(); j>=0; j--){
			if(used[left][j]){
				picked.add(items.get(j-1));
				left = left - items.get(j-1).getWeight();
			}
		}
		return picked;
	}
	public static void main(String[] args) {
		Item i1 = new Item(10, 20);
		Item i2 = new Item(99, 100);
		Item i3 = new Item(80, 200);
		Item i4 = new Item(70, 300);
		Item i5 = new Item(20, 40);
		ArrayList<Item> iList = new ArrayList<Item>();
		iList.add(i1);
		iList.add(i2);
		iList.add(i3);
		iList.add(i4);
		iList.add(i5);
		Knapsack k1 = new Knapsack(100, iList);
		System.out.println(k1.maxValue());
		System.out.println(k1.optimalChoice());
	}
}
class Item {
	private int weight;
	private double value;
	Item(int weight, double value) {
		this.weight = weight;
		this.value = value;
	}
	public int getWeight(){
		return weight;
	}
	public double getValue(){
		return value;
	}
	@Override
	public String toString(){
		return "(weight: "+weight+", value: "+value+")";
	}
}