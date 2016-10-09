package vmw_OutputComments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputComments {

	private List<String> comments = new ArrayList<String>();
	
	public static void main(String[] args) {
		OutputComments oc = new OutputComments();
		
		String fileName = "data/Demo.java";
		
		oc.readFile(fileName);
		oc.showComments();
	}
	
	public void showComments() {
		if (comments == null || comments.size() < 1) {
			System.out.println("No comments found in the file!");
			return;
		}
		
		for (String comm: comments) {
			System.out.println(comm);
		}
	}
	
	public void readFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		FileReader br = null;
		try {
			br = new FileReader(fileName);
			
			int ch = -1;
			boolean singleLineComment = false;
			boolean multiLineComment = false;
			char prev = (char)ch;
			while ((ch = br.read()) != -1) {
				if (ch == '/' && prev == ch && !singleLineComment) {
					singleLineComment = true;
				} else if (ch == '*' && prev == '/' && !multiLineComment) {
					multiLineComment = true;
				} else if (ch == '\n' && singleLineComment) {
					comments.add(sb.toString());
					singleLineComment = false;
					sb = new StringBuffer();
				} else if (ch == '/' && prev == '*' && multiLineComment) {
					comments.add(sb.toString());
					multiLineComment = false;
					sb = new StringBuffer();
				} else if (multiLineComment || singleLineComment) {
					sb.append((char)ch);
				}
				prev = (char)ch;
			}
			
			//System.out.println(comments);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


