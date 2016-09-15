
public class DemoSet {

	public static void main(String[] args) {
		DemoSet ds = new DemoSet();
		
		A a = new B("bname");
		a.
	}
	
	
}

class A {
	protected String name;
	public void setName(String name) {
		this.name = name;
	}
}

class B extends A {
	B(String n) {
		name = n; 
	}
	
	public void show() {
		System.out.println("b-name: " + name);
	}
}