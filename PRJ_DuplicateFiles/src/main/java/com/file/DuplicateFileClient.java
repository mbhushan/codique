package main.java.com.file;

import java.security.NoSuchAlgorithmException;

public class DuplicateFileClient {

	public static void main(String[] args) {
		String [] folders = {
				"data",
				"music",
				"vids",
				"docs",
				"pics"
		};
		
		for (int i=0; i<folders.length; i++) {
			try {
				DuplicateFiles df = new DuplicateFiles(folders[i]);
				df.showDuplicateFiles();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
		}
		//DuplicateFiles df = new DuplicateFiles(folder)
		
//		String [] files = {
//				"data/file1.txt",
//				"data/file2.ini",
//				"data/beethoven01.mp4",
//				"data/pianomusic.mp4"
//				};
//		try {
//			FileFingerprint ff = new FileFingerprint();
//
//			for (int i = 0; i < files.length; i++) {
//				String hash = ff.getFingerPrint(files[i]);
//				System.out.println("file name: " + files[i]);
//				System.out.println("file hash:" + hash);
//			}
//
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
