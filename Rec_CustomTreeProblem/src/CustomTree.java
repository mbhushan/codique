import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CustomTree {

	public static void main(String[] args) {
		CustomTree ct = new CustomTree();
		
		String [][] nodes = {
				{"a", "b"},
				{"b", "c"},
				{"b", "d"},
				{"a", "e"}
		};
		
		ct.printForest(nodes);
		System.out.println("--------------------------------------------------");
		String [][] nodes1 = {
				{"a", "b"},
				{"a", "g"},
				{"b", "c"},
				{"c", "d"},
				{"d", "e"},
				{"c", "f"},
				{"z", "y"},
				{"y", "x"},
				{"x", "w"},
		};
		ct.printForest(nodes1);
	}
	
	public void printForest(String [][] nodes) {
		Set<String> start = new HashSet<String>();
		Set<String> end = new HashSet<String>();
		Map<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
		
		for (int i=0; i<nodes.length; i++) {
			start.add(nodes[i][0]);
			end.add(nodes[i][1]);
			ArrayList<String> list = new ArrayList<String>();
			if (hmap.containsKey(nodes[i][0])) {
				list = hmap.get(nodes[i][0]);
			}
			list.add(nodes[i][1]);
			hmap.put(nodes[i][0], list);
		}
		
		List<String> roots = new ArrayList<String>();
		for (String st: start) {
			if (!end.contains(st)) {
				roots.add(st);
			}
		}
		
		System.out.println("roots: " + roots.toString());
		System.out.println("map: " + hmap.toString());
		
		int step = 1;
		HashSet<String> visited = new HashSet<String>();
		for (String src: roots) {
			printTree(src, hmap, visited, step);
		}
	}
	
	private void printTree(String src, Map<String, ArrayList<String>> hmap, HashSet<String> visited, int step) {
		visited.add(src);
		
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < step; i++) {
			sb.append(" ");
		}
		sb.append("|-->");
		
		System.out.println(sb.toString() + src);
		
		if (hmap.containsKey(src)) {
			List<String> neighbors = hmap.get(src);
			for (String dest: neighbors) {
				if (!visited.contains(dest)) {
					printTree(dest, hmap, visited, step+4);
					//System.out.println();
				}
			}
		}
	}
}
