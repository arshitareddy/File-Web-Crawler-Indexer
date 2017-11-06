import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebCrawler implements Runnable{
	
	private CopyOnWriteArrayList<String> links = new CopyOnWriteArrayList<String>(); 
	private static int non_repeat_count = 1;
	private String url_name;

	public WebCrawler(String url_name) {
	//	links = new CopyOnWriteArrayList<String>();
		this.url_name = url_name;
	}

	public void getPageLinks(String url) {
		if (!links.contains(url)) {
			try {

				Document document = Jsoup.connect(url).get();
				String content = Jsoup.connect(url).get().html();
				String link_name = "Link";
				if (links.add(url)) {
					System.out.println(url);
					Files.write(Paths.get(link_name + non_repeat_count + ".html"), content.getBytes(),
							StandardOpenOption.CREATE);
				}
				Elements page_links = document.select("a[href]");

				for (Element reffred : page_links) {
					if (non_repeat_count <= 10) {
						non_repeat_count++;
						getPageLinks(reffred.attr("abs:href"));
					} else {
						return;
					}
				}

			} catch (IOException e) {
				System.err.println("For '" + url + "': " + e.getMessage());
			}
		}
	}
	
	public void run(){
		getPageLinks(this.url_name);
		}
	}
	
