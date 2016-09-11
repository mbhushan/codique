package main.java.com.textj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextJustification {

	private String text = "";
	private int lineWidth = 15;
	private Words words;
	private static final char NEW_LINE = '\n';
	
	public TextJustification(String text, int lineWidth) {
		this.text = text;
		this.lineWidth = lineWidth;
		this.words = new Words(this.text);
	}
	
	
	public String process() {
		StringBuffer sb = new StringBuffer();
		String space = " ";
		
		int width = 0;
		int spaceLeft = lineWidth;
		
		Iterator iter = words.getIterator();
		List<String> list = new ArrayList<String>();
		List<Integer> indexes = new ArrayList<Integer>();
		Random rand = new Random();
		
		while (iter.hasNext()) {
			String word = (String)iter.next();
			int len = word.length();
			if ((width + len + 1) < lineWidth) {
				list.add(word);
				list.add(space);
				indexes.add(list.size()-1);
				width += len;
				spaceLeft = spaceLeft - width;
			} else if ((width + len + 1) > lineWidth) {
				list.remove(list.size()-1);
				indexes.remove(indexes.size()-1);
				spaceLeft += 1;
				//distribute the space evenly.
				int count = 0;
				int size = indexes.size();
				System.out.println("size: " + size);
				while (count < spaceLeft) {
					int r = rand.nextInt(size);
					list.set(r, list.get(r) + space);
					++count;
				}
				sb.append(listToString(list) + NEW_LINE);
				list = new ArrayList<String>();
				indexes = new ArrayList<Integer>();
				width = len;
				spaceLeft = lineWidth - width;
				list.add(word);
				list.add(space);
				indexes.add(list.size()-1);
				
				
			} else {
				sb.append(listToString(list) + NEW_LINE);
				list = new ArrayList<String>();
				indexes = new ArrayList<Integer>();
				width = 0;
				spaceLeft = lineWidth;
			}
			//add the overflow word.
			
		}
		if (list.size() > 0) {
			sb.append(listToString(list) + NEW_LINE);
		}
		
		return sb.toString();
	}
	
	public String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str);
		}
		
		return sb.toString();
	}
	
	
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (! (o instanceof TextJustification)) {
			return false;
		}
		
		TextJustification tj = (TextJustification) o;
		
		return tj.text.equals(o.toString());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (this.text != null) {
			sb.append("text: " + text + ", ");
		}
		sb.append("width: " + this.lineWidth);
		sb.append("]");
		
		return sb.toString();
	}
}
