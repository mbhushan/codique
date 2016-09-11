package main.java.com.textj;


public class TextJustifyClient {

	public static void main(String[] args) {
		TextJustifyClient tjc = new TextJustifyClient();
		
		//tjc.testWords();
		tjc.testTextJustify();
	}
	
	public void testTextJustify() {
		String text = "The Text Justification algorithm will ensure that the output from" + 
						" your program is both left and right justified when displayed in";
		int lineWidth = 30;
		TextJustification tj = new TextJustification(text, lineWidth);
		String output = tj.process();
		System.out.println(output);
	}
	
	public void testWords() {
		String text = "mani is-it    coding's           now.";
		
		Words word = new Words(text);
		for (Iterator it = word.getIterator(); it.hasNext();) {
			String wd = (String)it.next();
			System.out.println(wd);
		}
	}
	
}
