
public class TestRun {

	public static void main(String[] args) {
		TestRun tr = new TestRun();
		
		A a = new B();
		a.show();
		
		a = new A();
		a.show();
	}
	
}

class A {
	int x = 0;
	
	public void show() {
		System.out.println("A-x: " + x);
	}
}

class B extends A {
	B() {
		x = 2;
	}
	
	public void show() {
		System.out.println("B - x: " + x);
	}
}
