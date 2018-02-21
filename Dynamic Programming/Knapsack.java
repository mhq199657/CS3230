import java.util.ArrayList;
class Knapsack {
	int maxCapacity;
	ArrayList<Item> items;
	double maxValue;
	ArrayList<ArrayList<Double>> dpTable;
	Knapsack(int maxCapacity, ArrayList<Item> items) {
		this.maxCapacity = maxCapacity;
		this.items = items;
	}
	public double maxValue(){
		return maxValue;
	}
	public ArrayList<Item> optimalChoice() {

	}
	public static void main(String[] args) {

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
}