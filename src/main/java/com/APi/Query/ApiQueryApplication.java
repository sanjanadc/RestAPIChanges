package com.APi.Query;
import com.APi.Query.Countries.Countries;



public class ApiQueryApplication {

	public static void main(String[] args)
	{
		Countries countries = new Countries();
		countries.matchingCountry("Dominica");
	}



}

