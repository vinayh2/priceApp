package priceApp;

import org.json.JSONArray;

public class dataResponse {
    private statusResponse status;
    private JSONArray data;
    
	public dataResponse(statusResponse status) {
		this.status = status;
	}
    
	public dataResponse(statusResponse status, JSONArray data) {
		this.status = status;
		this.data = data;
	}

	public statusResponse getStatus() {
		return status;
	}

	public JSONArray getData() {
		return data;
	}
}