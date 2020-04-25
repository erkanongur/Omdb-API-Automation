package methods;

import base.Enums.KEYS;
import base.Enums.URL;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ByIDorTitle_Methods {
	Response response = null;
	
	//Search by ID
	public Response searchById(String id) {
		response = RestAssured.get(URL.TARGET_URL.getTargetURL() + "?apikey=" + KEYS.API_KEY.getApiKey() + "&i=" + id);
		return response;
	}
}
