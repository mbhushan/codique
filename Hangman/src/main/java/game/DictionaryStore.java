package main.java.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictionaryStore {

	private Map<Integer, ArrayList<String>> lenDict;
	
	public DictionaryStore() {
		this.lenDict = new HashMap<Integer, ArrayList<String>>();
		init();
	}
	
	private void init() {
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("data/wordsEn.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				int len = line.length();
				ArrayList<String> words = new ArrayList<String>();
				if (lenDict.containsKey(len)) {
					words = lenDict.get(len);
				}
				words.add(line);
				lenDict.put(len, words);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getWords(int wordLen) {
		if (this.lenDict.containsKey(wordLen)) {
			return lenDict.get(wordLen);
		}
		return null;
	}
}
