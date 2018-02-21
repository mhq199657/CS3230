import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class TransitiveClosure {
	ArrayList<List<List<Integer>>> dpTable;
	int numVertex;
	TransitiveClosure(List<List<Integer>> graph) {
		dpTable = new ArrayList<List<List<Integer>>>();
		numVertex = graph.size();
		dpTable.add(graph);
		for(int k = 1; k<=numVertex; k++) {
			dpTable.add(graph);
			for(int i = 0; i<numVertex; i++) {
				for(int j = 0; j<numVertex; j++) {
					if(dpTable.get(k-1).get(i).get(j)==1 ||
					   (dpTable.get(k-1).get(i).get(k-1)==1 && dpTable.get(k-1).get(k-1).get(j)==1)){
						dpTable.get(k).get(i).set(j, 1);
					}else{
						dpTable.get(k).get(i).set(j, 0);
					}
				}
			}
		}
	}
	//0-indexed
	public boolean isTransitive(int from, int to) {
		return dpTable.get(numVertex).get(from).get(to) == 1;
	}
	public void printTable() {
		List<List<Integer>> result = dpTable.get(numVertex);
		for(int i = 0; i<numVertex; i++) {
			for(int j = 0; j < numVertex; j++) {
				System.out.print(result.get(i).get(j)+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		List<List<Integer>> g1 = new ArrayList<>();
		g1.add(Arrays.asList(0, 1, 1, 0, 0));
		g1.add(Arrays.asList(0, 1, 0, 1, 0));
		g1.add(Arrays.asList(1, 0, 0, 0, 0));
		g1.add(Arrays.asList(0, 0, 0, 1, 0));
		g1.add(Arrays.asList(0, 0, 0, 0, 0));
		System.out.println(g1);
		TransitiveClosure tc1 = new TransitiveClosure(g1);
		System.out.println(tc1.isTransitive(0,2));
		tc1.printTable();
	}
}