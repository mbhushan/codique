package main.java.com.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileFingerprint {

	private RandomAccessFile file;
	private final static long SIZE = 500;
	private final static long CHUNK = 100;
	private MessageDigest md ; 
	
	public FileFingerprint() throws NoSuchAlgorithmException {
		md= MessageDigest.getInstance("MD5");
	}
	
	public String getFingerPrint(String filePath) {
		byte [] bytes = null;
		try {
			this.file = new RandomAccessFile(filePath, "r");
			long fileLen = this.file.length();
			
			if (fileLen <= SIZE) {
				bytes = new byte[(int)fileLen];
				file.read(bytes);
				md.update(bytes);
				
			} else {
				readFileBytes(0); //read start chunk
				readFileBytes(fileLen/2); //read mid chunk
				readFileBytes(fileLen - CHUNK-1); //read end chunk.
			}
			
			this.file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return getMD5HashString();
	}
	
	private String getMD5HashString() {
		byte [] bytes = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b: bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	private void readFileBytes(long position) throws IOException {
		
		byte [] bytes = new byte[(int)CHUNK];
		this.file.seek(position);
		this.file.read(bytes);
		this.md.update(bytes);
	}
}
