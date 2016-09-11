package main.java.com.textj;

public class Words implements Container {

	private String text;
	private int textLen = 0;

	public Words(String text) {
		this.text = text;
		this.textLen = text.length();
	}
	
	@Override
	public Iterator getIterator() {
		return new WordIterator();
	}
	
	private class WordIterator implements Iterator {
		
		int index = 0;
		
		@Override
		public boolean hasNext() {
			return index != textLen;
		}

		@Override
		public Object next() {
			return getWord();
		}
		
		private String getWord() {
			if (index >= textLen) {
				return null;
			}
			
			StringBuffer sb = new StringBuffer();
			
			skipWhiteSpace();
			
			char ch;
			while ((index < textLen) && (ch = text.charAt(index)) != ' ') {
				sb.append(ch);
				++index;
			}
			
			if (sb.length() > 0) {
				return sb.toString();
			}
			return null;
		}
		
		private void skipWhiteSpace() {
			while((index < textLen) && (text.charAt(index) == ' ')) {
				++index;
			}
		}
		
	}

}

