package priceApp;

import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class priceService {
	private String url = "https://query1.finance.yahoo.com/v7/finance/chart";
	private String symbol;
	private String startDate;
	private String endDate;
	
	public priceService(String symbol, String startDate, String endDate) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Collection<quoteSet> fetchData() {	
		try {
			JSONObject json = new JSONObject(IOUtils.toString(new URL(formattedUrl()), Charset.forName("UTF-8")));
			Object error = json.getJSONObject("chart").get("error");
			if (!error.toString().equals("null")) {
			    System.err.println(error.toString());
			    return null;
			}
			
			JSONArray results = json.getJSONObject("chart").getJSONArray("result");
			if (results == null || results.length() != 1) {
				return null;
			}
			
			JSONArray timestamps = results.getJSONObject(0).getJSONArray("timestamp");
			JSONArray quotes = results.getJSONObject(0).getJSONObject("indicators").getJSONArray("quote");
			if (quotes == null || quotes.length() != 1) {
				return null;
			}
			
			JSONObject quote = quotes.getJSONObject(0);
			
			JSONArray open = quote.getJSONArray("open");
			JSONArray close = quote.getJSONArray("close");
			JSONArray high = quote.getJSONArray("high");
			JSONArray low = quote.getJSONArray("low");
			
			return IntStream.range(0, timestamps.length())
				.mapToObj(i -> new quoteSet(toYYYYMMDD(timestamps.getInt(i)), 
						open.getDouble(i), close.getDouble(i), high.getDouble(i), low.getDouble(i)))
				.collect(Collectors.toList());
			
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
	}

	private String formattedUrl() {
		return new StringBuilder(this.url)
				.append("/").append(this.symbol)
				.append("?period1=").append(toEpoch(this.startDate))
				.append("&period2=").append(toEpoch(this.endDate))
				.append("&interval=1d&indicators=quote&includeTimestamps=true")
				.toString();
	}

	private String toEpoch(String date) {
		return String.valueOf(LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE)
				.atStartOfDay(ZoneId.systemDefault())
				.toEpochSecond());
	}
	
	private String toYYYYMMDD(long date) {
		return Instant.ofEpochMilli(date*1000)
				  .atZone(ZoneId.systemDefault())
				  .toLocalDate()
				  .format(DateTimeFormatter.BASIC_ISO_DATE);
	}

}
