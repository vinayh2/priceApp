package priceApp;

public class quoteSet {
	private String date;
	private Double open;
	private Double close;
	private Double high;
	private Double low;
	
	public quoteSet(String i, Double open, Double close, Double high, Double low) {
		this.date = i;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
	}

	public String getDate() {
		return date;
	}

	public Double getOpen() {
		return open;
	}

	public Double getClose() {
		return close;
	}

	public Double getHigh() {
		return high;
	}

	public Double getLow() {
		return low;
	}
}
