import java.util.Random;

/*
You receive as input a set of people. You need to output a random and uniform list of pairs of these people for giving gifts 
to each other. E.g. the first person in the pair gives a gift to the second person in the pair. Make sure that everyone gets 
a gift and it is not allowed for a giver of a gift to receive a gift from the recipient of his gift. 
*/

public class SecretSanta {

	public static void main(String[] args) {
		SecretSanta ss = new SecretSanta();
		String st = "ABCDE";
		
		Pair [] result = ss.secretSantaPairs(st.toCharArray());
		for (Pair p: result) {
			System.out.println(p.toString());
		}
	}
	
	public Pair [] secretSantaPairs(char [] people) {
		if (people == null) {
			return null;
		}
		
		if (people.length < 3) {
			return null;
		}
		
		shuffle(people);
		Pair [] pairs = new Pair[people.length];
		int i=0;
		for (i=0; i<people.length-1; i++) {
			pairs[i] = new Pair(people[i], people[i+1]);
		}
		pairs[i] = new Pair(people[i], people[0]);
		
		return pairs;
	}
	
	private void shuffle(char [] people) {
		int len = people.length;
		Random rand = new Random();
		
		for (int i=len-1; i>=0; i--) {
			int r = rand.nextInt(i+1);
			char ch = people[i];
			people[i] = people[r];
			people[r] = ch;
		}
	}
	
}

class Pair {
	char x;
	char y;
	
	Pair(char x, char y) {
		this.x =x;
		this.y = y;
	}
	
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}
