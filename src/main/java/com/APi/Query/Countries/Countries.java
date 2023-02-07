package com.APi.Query.Countries;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import ch.qos.logback.core.subst.Token.Type;

public class Countries {

	public String matchingCountry(String val) 
	{
		String country= "";
		try {

			URL url = new URL("https://api.covid19api.com/countries");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Getting the response code
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} 
			else 
			{

			String inline = "";
			Scanner scanner = new Scanner(url.openStream());

			// Write all the JSON data into a string using a scanner
			while (scanner.hasNext()) 
			{
				inline += scanner.nextLine();
			}

			// Close the scanner
			scanner.close();
			Gson gson = new Gson();
			// Using the JSON simple library parse the string into a json object
			JsonArray convertedObject = gson.fromJson(inline, JsonArray.class);

			System.out.println(convertedObject);

			for (int i = 0; i < convertedObject.size(); i++) 
			{
			JsonObject object = convertedObject.get(i).getAsJsonObject();

			country = object.get("Country").getAsString();
			if (country.equalsIgnoreCase(val)) 
			{
					System.out.println("name: " + country);
					break;
			}

			}

			}

		    } 
			catch (Exception e) 
		    {
			e.printStackTrace();
		    }
		return country;
	}

}
