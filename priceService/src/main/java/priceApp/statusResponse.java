package priceApp;

public enum statusResponse {
	SUCCESS("success"),
	ERROR("error");
	
	private String status;

	private statusResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
