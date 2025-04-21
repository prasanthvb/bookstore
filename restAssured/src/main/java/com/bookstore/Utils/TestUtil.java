package com.bookstore.Utils;

import java.util.Map;
import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtil {
	
	// Build base request spec
	private static RequestSpecification getRequestSpecification() {
		return new RequestSpecBuilder().setContentType(ContentType.JSON).build().log().all();
	}

	// Log responses
	private static void logResponse(Response response) {
		response.then().log().all();
	}

	// Common method to attach headers
	private static RequestSpecification withHeaders(RequestSpecification spec, Map<String, String> headers) {
		if (headers != null) {
			spec.headers(headers);
		}
		return spec;
	}

	// POST - No Authorization
	public static Response post(String endpoint, Object payload) {
		Response response = RestAssured.given().spec(getRequestSpecification()).body(payload).post(endpoint);
		logResponse(response);
		return response;
	}

	// POST - With Authorization Token
	public static Response post(String endpoint, Object payload, String token) {
		Response response = getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .post(endpoint);
        logResponse(response);
        return response;
	}

	// POST - With custom headers
	public static Response post(String endpoint, Object payload, Map<String, String> headers) {
		RequestSpecification spec = RestAssured.given().spec(getRequestSpecification()).body(payload);
		withHeaders(spec, headers);
		Response response = spec.post(endpoint);
		logResponse(response);
		return response;
	}

	//GET - With Authorization
	public static Response get(String endpoint, String token) {
		return get(endpoint, Map.of("Authorization", "Bearer " + token));
	}

	// GET - With custom headers
	public static Response get(String endpoint, Map<String, String> headers) {
		RequestSpecification spec = RestAssured.given().spec(getRequestSpecification());
		withHeaders(spec, headers);
		Response response = spec.get(endpoint);
		logResponse(response);
		return response;
	}

	// PUT
	public static Response put(String endpoint, Object payload, String token) {
		RequestSpecification spec = RestAssured.given().spec(getRequestSpecification()).body(payload)
				.header("Authorization", "Bearer " + token);
		Response response = spec.put(endpoint);
		logResponse(response);
		return response;
	}

	// DELETE
	public static Response delete(String endpoint, String token) {
		Response response = RestAssured.given().spec(getRequestSpecification())
				.header("Authorization", "Bearer " + token).delete(endpoint);
		logResponse(response);
		return response;
	}

	//  PATCH
	public static Response patch(String endpoint, Object payload, String token) {
		Response response = RestAssured.given().spec(getRequestSpecification()).body(payload)
				.header("Authorization", "Bearer " + token).patch(endpoint);
		logResponse(response);
		return response;
	}

	// HEAD
	public static Response head(String endpoint, String token) {
		Response response = RestAssured.given().spec(getRequestSpecification())
				.header("Authorization", "Bearer " + token).head(endpoint);
		logResponse(response);
		return response;
	}
	
	public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(50) + 1; // returns number from 1 to 50
    }
}
