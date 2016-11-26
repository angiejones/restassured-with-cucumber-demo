package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;


public class BookTests
{
	private static String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";

	@Test
	public void testGetByISBN(){
		String isbn = "isbn:9781451648546";

		given().
		param("q", isbn).
		when().
		get(ENDPOINT_GET_BOOK_BY_ISBN)
		.then().
		statusCode(HttpStatus.SC_OK).
		body(	"totalItems", equalTo(1),
				"kind", equalTo("books#volumes"),
				"items.volumeInfo.title", containsInAnyOrder("Steve Jobs"),
				"items.volumeInfo.authors", containsInAnyOrder((Object)Arrays.asList("Walter Isaacson")),
				"items.volumeInfo.publisher", containsInAnyOrder("Simon and Schuster"),
				"items.volumeInfo.pageCount", containsInAnyOrder(630));
	}
}
