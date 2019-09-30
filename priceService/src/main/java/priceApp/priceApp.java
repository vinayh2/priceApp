package priceApp;

import static spark.Spark.*;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

public class priceApp {
	public static void main(String[] args) {
        
		get("/priceService/:symbol/:startDate/:endDate", (request, response) -> {
			response.type("application/json");
			priceService service = new priceService(request.params(":symbol"), request.params(":startDate"),
					request.params(":endDate"));

			Collection<quoteSet> result = service.fetchData();

			return new JSONObject(result.isEmpty() ? new dataResponse(statusResponse.ERROR)
					: new dataResponse(statusResponse.SUCCESS, new JSONArray(result)));

		});

		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

}
