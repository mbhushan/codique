package main.java.com.textj;

/*
OUTPUT:
The Text Justification algorithm will ensure that the output from your program is both left and right justified when displayed in a mono-spaced font  such as Courier.  This  paragraph is an example of such  justification.  All the  lines (except  the last) of the output from  a given run  of your  program should  have the  same length, and  the last  line is  to be  no longer  than the  other lines.
line width: 65
The Text Justification  algorithm will  ensure   that  the output
from   your  program is  both  left    and  right  justified when
displayed in  a  mono-spaced font such as Courier. This paragraph
is an example of such  justification. All   the lines (except the
last) of  the  output  from a  given run of your  program  should
have the same  length, and the last line is  to be no longer than
the other lines. 
*/
public class TextJustifyClient {

	public static void main(String[] args) {
		TextJustifyClient tjc = new TextJustifyClient();

		// tjc.testWords();
		tjc.testTextJustify();
	}

	public void testTextJustify() {
		String input = "The Text Justification algorithm will ensure that the output from "
				+ "your program is both left and right justified when displayed in a "
				+ "mono-spaced font  such as Courier.  This  paragraph is an example "
				+ "of such  justification.  All the  lines (except  the last) of the "
				+ "output from  a given run  of your  program should  have the  same "
				+ "length, and  the last  line is  to be  no longer  than the  other "
				+ "lines.";
		int lineWidth = 65;
		System.out.println(input);
		System.out.println("line width: " + lineWidth);
		TextJustification tj = new TextJustification(input, lineWidth);
		String output = tj.leftRightJustify();
		System.out.println(output);
	}

	public void testWords() {
		String text = "mani is-it    coding's           now.";

		Words word = new Words(text);
		for (Iterator it = word.getIterator(); it.hasNext();) {
			String wd = (String) it.next();
			System.out.println(wd);
		}
	}

}
