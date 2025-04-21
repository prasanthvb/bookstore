package com.bookstore.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bookstore.Constants.Endpoints;
import com.bookstore.Utils.TestUtil;
import com.bookstore.base.TestBase;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class BookTestCase extends TestBase {
	Faker faker = new Faker();
	static int bookID;
	static String authToken;

	private record userData(String email, String password) {
	}
	private record bookData(String name, String author, int publishedYear, String bookSummary) {
	}

	@Test(priority = 1)
	public void newSignup() {
		userData userdata = new userData(faker.internet().emailAddress(), getProperty("password"));
		Response response = TestUtil.post(Endpoints.SIGNUP, userdata);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.getBody().asString().contains("User created successfully"));
	}

	@Test(priority = 2)
	public void validLogin() {
		userData userdata = new userData(getProperty("email"), getProperty("password"));
		Response response = TestUtil.post(Endpoints.LOGIN, userdata);
		Assert.assertEquals(response.getStatusCode(), 200);
		//access token is required for all the test cases - Store globally
		authToken = response.jsonPath().getString("access_token");
		Assert.assertNotNull(authToken);
	}

	@Test(priority = 3)
	public void invalidLogin() {
		userData userdata = new userData(faker.internet().emailAddress(), faker.internet().password());
		Response response = TestUtil.post(Endpoints.LOGIN, userdata);
		Assert.assertEquals(response.getStatusCode(), 400);
	}
	
	@Test(priority = 4, dependsOnMethods = "validLogin")
    public void addBook() {
//		bookID= TestUtil.getRandomNumber();
		bookData bookdata = new bookData("Playwright Automation", 
				"Kailash Pathak", 2021, 
				"This book is crafted to be your all-in-one guide for mastering Playwright...");
       
        Response response = TestUtil.post(Endpoints.BOOKS, bookdata, authToken);
        Assert.assertEquals(response.getStatusCode(), 200);
        bookID = response.jsonPath().getInt("id");
//        Assert.assertEquals(response.jsonPath().getInt("id"), bookID);
       // test.pass("Book created successfully with ID: " + bookID);
    }

    @Test(priority = 5, dependsOnMethods = "addBook")
    public void getBookById() {
        Response response = TestUtil.get(Endpoints.BOOKS + bookID, authToken);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), bookID);
//        test.pass("Book verified by ID successfully");
    }

//    @Test(priority = 6, dependsOnMethods = "addBook")
//    public void updateBookwithID() {
//    	bookData newBookdata = new bookData(bookID, "API Automation", 
//				"Prasanth", 2021, 
//				"Update API book for Testing");
//
//        Response response = TestUtil.put(Endpoints.BOOKS + bookID, newBookdata, authToken);
//        Assert.assertEquals(response.getStatusCode(), 200);  
//        Assert.assertEquals(response.jsonPath().getString("author"), "Prasanth");
////        test.pass("Book updated successfully");
//    }
//    @Test(priority = 7, dependsOnMethods = "addBook")
//    public void getAllBooks() {
//        Response response = TestUtil.get(Endpoints.BOOKS, authToken);
//        Assert.assertEquals(response.getStatusCode(), 200);
////        test.pass("All books retrieved successfully");
//    }
//
//    @Test(priority = 8, dependsOnMethods = {"addBook", "updateBookwithID", "getBookById"})
//    public void testDeleteBook() {
//        Response response = TestUtil.delete(Endpoints.BOOKS + bookID, authToken);
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertTrue(response.getBody().asString().contains("Book deleted successfully"));
////        test.pass("Book deleted successfully");
//    }

}
