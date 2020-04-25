package methods;

import base.Enums.KEYS;
import base.Enums.URL;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BySearch_Methods {
	Response response = null;

	//Search only by title
	public Response searchByTitle(String title) {
		response = RestAssured.get(URL.TARGET_URL.getTargetURL() + "?apikey=" + KEYS.API_KEY.getApiKey() + "&s=" + title);
		return response;
	}
	//Search by title and type
	public Response searchByTitleAndType(String title, String type) {
		response = RestAssured.get(URL.TARGET_URL.getTargetURL() + "?apikey=" + KEYS.API_KEY.getApiKey() + "&s=" + title + "&type=" + type);
		return response;
	}
	//Search by title and type with page number
	public Response searchByTitleAndTypeWithPage(String title, String type, int page) {
		response = RestAssured.get(URL.TARGET_URL.getTargetURL() + "?apikey=" + KEYS.API_KEY.getApiKey() + "&s=" + title + "&type=" + type + "&page="+ page);
		return response;
	}

}
