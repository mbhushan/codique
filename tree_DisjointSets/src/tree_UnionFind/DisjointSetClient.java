package tree_UnionFind;

public class DisjointSetClient {

	DisjointSet<Integer> ds = new DisjointSet<Integer>();
	
	public static void main(String[] args) {
		DisjointSetClient dsc = new DisjointSetClient();
		
		dsc.experiment();
	}
	
	public void experiment() {
		int n = 7;
		for (int i=1; i<=n; i++) {
			this.ds.makeSet(i);
		}
		
		ds.union(1, 2);
		ds.union(3, 4);
		ds.union(5, 6);
		ds.union(6, 7);
		ds.union(2, 4);
		
		for (int i=1; i<=n; i++) {
			System.out.println("set for: " + i + " => " + ds.findSet(i));
		}
	}
}
