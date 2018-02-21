import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Apsp {
	List<List<Integer>> dpTable;
	int numVertex;
	List<List<Integer>> next;
	Apsp(List<List<Integer>> graph) {
		dpTable = graph;
		numVertex = dpTable.size();
		next = new ArrayList<List<Integer>>();
		for(int i = 0; i< numVertex; i++) {
			List<Integer> currRow = new ArrayList<Integer>(Collections.nCopies(numVertex, 0));
			next.add(currRow);
			for(int j = 0; j< numVertex; j++){
				next.get(i).set(j, j);
			}
			dpTable.get(i).set(i,0);
		}
		for(int k = 0; k<numVertex; k++){
			for(int i = 0; i<numVertex; i++) {
				for(int j = 0; j<numVertex; j++) {
					if(dpTable.get(i).get(j)>dpTable.get(i).get(k)+dpTable.get(k).get(j)){
						dpTable.get(i).set(j, dpTable.get(i).get(k)+dpTable.get(k).get(j));
						next.get(i).set(j, next.get(i).get(k));
					}
				}
			}
		}
	}
	public int shortestPath(int from, int to) {
		return dpTable.get(from).get(to);
	}
	public void printTable() {
		for(int i = 0; i<numVertex; i++) {
			for(int j = 0; j < numVertex; j++) {
				System.out.print(dpTable.get(i).get(j)+"\t");
			}
			System.out.println();
		}
	}
	public ArrayList<Integer> path(int from, int to) {
		int current = from;
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(from);
		while(current!=to) {
			current = next.get(current).get(to);
			p.add(current);
		}
		return p;
	}
	public static void main(String[] args) {
		List<List<Integer>> g1 = new ArrayList<>();
		g1.add(Arrays.asList(0, 2, 3, 5, 7));
		g1.add(Arrays.asList(2, 0, 4, 5, 9));
		g1.add(Arrays.asList(1, 7, 0, 24, 3));
		g1.add(Arrays.asList(232, 1, 242, 0, 12));
		g1.add(Arrays.asList(12, 23, 34, 5, 0));
		Apsp apsp1 = new Apsp(g1);
		apsp1.printTable();
		System.out.println(apsp1.path(4,0));
	}
}