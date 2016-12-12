import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TwoSum {
	
	private List<Integer> list;
	private Set<Integer> hset;
	
	public TwoSum() {
		list = new ArrayList<Integer>();
		hset = new HashSet<Integer>();
	}

	public static void main(String [] args) {
		TwoSum ts = new TwoSum();
		ts.readInput();
	}
	
	public void readInput() {
		String fileName = "data/twosum.txt";
		BufferedReader br = null;
		try {
			int count = 0;
			String line = null;
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				Integer number = Integer.parseInt(line);
				list.add(number);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
