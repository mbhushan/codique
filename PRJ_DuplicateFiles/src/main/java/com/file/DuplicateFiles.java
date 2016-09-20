package main.java.com.file;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateFiles {
	
	private File folder;
	private FileFingerprint fprint;
	private Map<String, ArrayList<String>> hmap;
	
	public DuplicateFiles(String folder) throws NoSuchAlgorithmException {
		this.folder = new File(folder);
		this.fprint = new FileFingerprint();
		this.hmap = new HashMap<String, ArrayList<String>>();
	}
	
	public void showDuplicateFiles() {
		File [] files = folder.listFiles();
		
		for (int i=0; i<files.length; i++) {
			String hash = fprint.getFingerPrint(files[i].getPath());
			ArrayList<String> fnames = new ArrayList<String>();
			if (hmap.containsKey(hash)) {
				fnames = hmap.get(hash);
			}
			fnames.add(files[i].getName());
			hmap.put(hash, fnames);
		}
		showFiles();	
	}
	
	private void showFiles() {
		if (hmap == null) {
			return;
		}
		int size = hmap.size();
		if (size == 0) {
			System.out.println("No duplicate files in this folder.");
			return;
		}
		
		for (String key: hmap.keySet()) {
			List<String> list = hmap.get(key);
			if (list.size() > 1) {
				System.out.println("duplicate files in folder: " + this.folder.getName());
				System.out.println(list.toString());
				System.out.println();
			}
			//System.out.println(hmap.get(key));
		}
	}
	
	
}
