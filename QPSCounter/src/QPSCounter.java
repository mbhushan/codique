import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;


public class QPSCounter {

	private long startTime;
	private int currIndex;
	private int size = 6; //in seconds.
	private int lastMinuteCount;
	private int [] A = null;
	
	public QPSCounter(int size) {
		this.size = size;
		this.startTime = System.currentTimeMillis()/1000;
		this.currIndex = 0;
		this.lastMinuteCount = 0;
		this.A = new int[size];
	}
	
	public void hit() {
		
		int lastIndex = currIndex;
		this.currIndex = getIndex();
		
		if (lastIndex != this.currIndex) {
			//delete from lastIndex+1 to currIndex.
			for (int i= (lastIndex+1)%this.size; i<=this.currIndex; i++) {
				this.lastMinuteCount -= this.A[i];
				this.A[i] = 0;
			}
		}
		++this.A[this.currIndex];
		++this.lastMinuteCount;
	}
	
	public int lastMinuteCount() {
		return this.lastMinuteCount;
	}
	
	private int getIndex() {
		long currTime = System.currentTimeMillis()/1000;
		int diff = (int) (currTime - this.startTime);
		
		return diff % this.size;
	}
	
	public String toString() {
		int sum = IntStream.of(this.A).sum();
		StringBuffer sb = new StringBuffer();
		
		sb.append("sum: " + sum + ", ");
		sb.append("lastMinCount: "  + this.lastMinuteCount + ", ");
		sb.append("A: " + Arrays.toString(A) + " \n");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		QPSCounter qc = new QPSCounter(6);
		Random rd = new Random();
		for(int i=0;i<1E3;i++){
			System.out.println("i: " + i + " = " + qc.toString());
			qc.hit();
			try {
				Thread.sleep(rd.nextInt(100));//thread sleep a random time between [0,100] miliseconds 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
