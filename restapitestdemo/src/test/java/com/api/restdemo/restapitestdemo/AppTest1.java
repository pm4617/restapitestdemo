package com.api.restdemo.restapitestdemo;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class AppTest1 {

	// reads folder files and
	public HashMap<String, String> putJsonFilesInHashMap(String pathoffolder) {
		HashMap<String, String> hm = new HashMap<String, String>();

		File folder = new File(pathoffolder);

		File[] filesinfolder = folder.listFiles();
		System.out.println(pathoffolder);
		String filecontents = "", line = "";
		for (File f : filesinfolder) {
			Charset charset = Charset.forName("UTF-8");
			BufferedReader reader;
			filecontents = "";
			// read file and put contents in filecontents variable
			try {
				reader = Files.newBufferedReader(f.toPath(), charset);
				while ((line = reader.readLine()) != null) {
					filecontents += line;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// add file name and file contente in hashmap
			// {key:abc.json , value:filecontents}
			hm.put(f.getName(), filecontents);
		}

		// prints hashmap
		System.out.println("Size of Hashmap containing file Name and contents is = " + hm.size());
		System.out.println("\nPrinting Hashmap Key Values...");
		for (String key : hm.keySet()) {
			System.out.println("Key is " + key);
			System.out.print("Value is " + hm.get(key) + "\n");
		}

		return hm;
	}

	@Test
	public void httpPost() throws JSONException, InterruptedException {
		URL apiURI = null;
		HashMap<String, String> hm = putJsonFilesInHashMap("src/jsonfiles");
		Set<Map.Entry<String, String>> entrySet = hm.entrySet();
		RequestSpecBuilder builder = null;
		RequestSpecification requestSpec = null;

		builder = new RequestSpecBuilder();

		builder.setBaseUri("http://gitrepoz.cloudapp.net");
		builder.setBasePath("/NLPSuggest/");
		builder.setContentType(ContentType.JSON);
		String apibody = "";
		Response response = null;
	
		int i = 1;
		int totalRequests = entrySet.size();
		for (Entry entry : entrySet) {
			System.out.println("\n:::Sending API request " + i + " of " + totalRequests);
			i++;
			apibody = (String) entry.getValue();
			System.out.print(entry.toString());
			builder.setBody(apibody);
			requestSpec = builder.build();
			response = given().spec(requestSpec).when().post();
			
			System.out.println("\n Json Response status code is =" + response.statusCode());
			System.out.println("\n Json Response Content Type is = " + response.getContentType());
			System.out.println("\n Json Response Body to String is = " + response.getBody().asString());
			
			// Convert response body to Json to fetch data 
			JSONObject jsonresbody = new JSONObject(response.getBody().asString());
			System.out.println("\n Response status is = " + jsonresbody.getString("status"));
			System.out.println("\n Response statusCode is = "+ jsonresbody.getInt("statusCode"));
			System.out.println("\n Response result array[0] is =  " + jsonresbody.getJSONArray("result").get(0));
			JSONObject jo = (JSONObject) jsonresbody.getJSONArray("result").get(0);
			System.out.println("\n Response resolution is = " + jo.getString("Resolution"));
		}

	}

}


