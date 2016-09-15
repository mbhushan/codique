import java.util.HashMap;
import java.util.Map;


public class DemoCopy {

	public static void main(String[] args) {
		DemoCopy dc = new DemoCopy();
		
		dc.demo();
	}
	
	public void demo() {
		Test t1 = new Test();
		
		t1.h1.put("k1", "v1");
		t1.h1.put("k2", "v2");
		
		Test t2 = new Test(t1);
		
		t1.h1.put("kx", "vx");
		
		t2.h1.put("kt2", "v3t2");
		
		System.out.println(t2.h1);
		
		System.out.println(t1.h1);
	}
}

class Test {
	Map<String, String> h1 ;
	
	Test() {
		h1 = new HashMap<String, String>();
	}
	
	Test(Test t) {
		this.h1 = new HashMap<String, String>(t.h1);
	}
}
