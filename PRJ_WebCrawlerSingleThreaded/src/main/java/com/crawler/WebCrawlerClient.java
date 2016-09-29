package main.java.com.crawler;

public class WebCrawlerClient {

	public static void main(String[] args) {
		WebCrawlerClient wc = new WebCrawlerClient();
		wc.test();
	}
	
	public void test() {
		WebCrawler crawler = new WebCrawler("test");
		String st = crawler.getMD5("manib");
		System.out.println(st);
	}
}
