package com.bookstore.Constants;

import com.bookstore.base.TestBase;

public class Endpoints extends TestBase{

	public static final String BASE_URL = getProperty("baseurl");
    public static final String SIGNUP = BASE_URL + "signup";
    public static final String LOGIN = BASE_URL + "login";
    public static final String BOOKS = BASE_URL + "books/";
    
}
