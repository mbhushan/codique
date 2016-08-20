
public class CacheDriver {

	private LRUCache<Integer, String> cache;
	
	public CacheDriver(int capacity) {
		this.cache = new LRUCache<Integer, String>(capacity);
	}
	
	public static void main(String[] args) {
		CacheDriver cd = new CacheDriver(1);
		
		cd.experiment();
	}
	
	public void experiment() {
		int [] keys = {1,2,3,4,5};
		String [] vals = {"A", "B", "C", "D", "E"};
		
		for (int i=1; i<keys.length; i++) {
			System.out.println("i-1 getting: " + keys[i-1] + " => " + cache.get(keys[i-1]));
			cache.set(keys[i], vals[i]);
			System.out.println("i-1 getting: " + keys[i-1] + " => " + cache.get(keys[i-1]));
			System.out.println("i getting: " + keys[i] + " => " + cache.get(keys[i]));
			System.out.println("cache capacity: " + cache.size());
			System.out.println();

		}
	}
}
