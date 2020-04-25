package tests;

import org.testng.annotations.Test;

import base.Helper_Methods;
import io.restassured.response.Response;
import methods.ByIDorTitle_Methods;
import methods.BySearch_Methods;

import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Test01 {
	//Parameters for this test
	String searchWord = "Twilight";
	String searchType = "movie";
	String searchedMovie = "Twilight";
	
	BySearch_Methods bySearch = null;
	ByIDorTitle_Methods byIdOrTitle = null;
	Helper_Methods helperMethods = null;
	Response response = null;
	String movieId = null;
	
	//Step 1: Search title with search by title methods and locate the ID of asked movie.
	@Test(priority = 1)
	void test_step1() {
		bySearch = new BySearch_Methods();
		helperMethods = new Helper_Methods();
		
		//Getting first page of the search result
		response = bySearch.searchByTitleAndType(searchWord, searchType);
		response.then()
		.assertThat()
		.body("Search", notNullValue())
		.assertThat()
		.body("Search.Title", notNullValue())
		.assertThat()
		.body("Search.imdbID", notNullValue())
		.assertThat()
		.body("Search.Year", notNullValue())
		.assertThat()
		.body("Search.Type", notNullValue())
		.assertThat()
		.body("Search.Poster", notNullValue()); 
		
		HashMap<String, String> movieMap = new HashMap<String, String>();
		
		ArrayList<String> nameList = response
				.then()
				.extract()
				.path("Search.Title");

		ArrayList<String> idList = response
				.then()
				.extract()
				.path("Search.imdbID");
		
		int totalResults = Integer.parseInt(response
				.then()
				.extract()
				.path("totalResults").toString());
		
		System.out.println("Total Results : " + totalResults);
		
		//Putting Titles and IDs into a HashMap
		//IDs as keys and Titles as values
		movieMap = helperMethods.combineArrays(idList, nameList);
		
		//Part up to here is about getting the first page and with that, total result amount
		//Now we can use it to get all other pages.
		
		int pageNumber = helperMethods.calculateTotalPageNumber(totalResults);
		System.out.println("Page Number : " + pageNumber);
		
		for(int i = 2; i <= pageNumber; i++) {
			response = bySearch.searchByTitleAndTypeWithPage(searchWord, searchType, i);
			HashMap<String, String> tempMovieMap = new HashMap<String, String>();
			
			ArrayList<String> tempNameList = response
					.then()
					.extract()
					.path("Search.Title");

			ArrayList<String> tempIdList = response
					.then()
					.extract()
					.path("Search.imdbID");
			
			tempMovieMap = helperMethods.combineArrays(tempIdList, tempNameList);
			
			helperMethods.combineHashMaps(movieMap, tempMovieMap);
		}
		System.out.println("Movie Size:" + movieMap.keySet().size());
		
		for (String key : movieMap.keySet()) {
			if(movieMap.get(key).equals(searchedMovie)) {
				movieId = key;
				break;
			}
		}
		
		System.out.println(movieId);
	}
	//Step 2: Searching by ID with the ID obtained in step 1. 
	@Test(priority = 2)
	void test_step2() {		
		byIdOrTitle = new ByIDorTitle_Methods();
		response = byIdOrTitle.searchById(movieId);
		response.then()
				.assertThat()
				.statusCode(200)
				.assertThat()
				.body("Title", notNullValue())
				.assertThat()
				.body("Released", notNullValue())
				.assertThat()
				.body("Year", notNullValue());
		
		String movieTitle = response.then()
				.extract()
				.path("Title")
				.toString();
		String movieYear = response.then()
				.extract()
				.path("Title")
				.toString();
		String movieReleasedInfo = response.then()
				.extract()
				.path("Title")
				.toString();
		
		assertNotNull(movieTitle);
		assertNotNull(movieYear);
		assertNotNull(movieReleasedInfo);
		assertEquals(movieTitle, searchedMovie);
	}
}
