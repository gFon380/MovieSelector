import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Webscraper {
	
	public static Movie scrapeMovie(String title) {
		
		try {
			Pattern p = Pattern.compile("[\\s\\w]*");
			Matcher m = p.matcher(title);
			boolean b = m.matches();
			if (!b) {
				System.out.println("Your input contained non movie title characters. Null movie returned.");
				return new Movie("null", "null");
			}
			String url = "https://www.imdb.com/find?q=";
			for (String word : title.split(" ")) {
				url = url.concat(word);
			}
			final Document searchPage = Jsoup.connect(url).get();
			String searchLink = findSearchLink(searchPage);
			final Document document = Jsoup.connect(searchLink).get();
			final String finalTitle = document.getElementsByAttributeValue("data-testid", "hero-title-block__title").text();
			final String description = document.getElementsByAttributeValue("data-testid", "plot-l").text();
			Movie movie = new Movie (finalTitle, description);
			return movie;
		} catch (Exception e) {
		}
		
		return new Movie(title, "Description not found.");
	}
	
	private static String findSearchLink(Document document) {
		try {
			Elements titles = document.getElementsByAttributeValue("name", "tt");
			 String movie = titles.first().parent().parent().getElementsByAttributeValue("class", "result_text").first().getElementsByTag("a").first().attr("abs:href");
			 return movie;
		} catch (Exception e) {
			return "";
		}
	}
}