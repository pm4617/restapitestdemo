package com.api.restdemo.restapitestdemo;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Unit test for simple App.
 */
public class AppTest {
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

			/*
			 * Another way Using Inputstream int c ; StringBuffer filecontents =
			 * new StringBuffer(""); String s=""; if (f.isFile()) {
			 * System.out.println("Filename is " + f.getName());
			 * System.out.println("Filepath is " + f.getPath()); InputStream is
			 * = null; try { is = new FileInputStream(f); while ((c = is.read()
			 * ) != -1) { strContent.append((char) c); }
			 * System.out.println(strContent); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * System.out.println(f.getName()); hm.put(f.getName(),
			 * filecontents); }
			 */
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

		String apiurl = "https://postman-echo.com/post"; // post request to
															// postman echo api

		// String APIBody = "{\"key1\":\"test1\",\"key2\":\"test2\"}"; // you
		// can read file from here and return string

		HashMap<String, String> hm;
		hm = putJsonFilesInHashMap("src/jsonfiles");
		 
		Set<Map.Entry<String, String>> entrySet = hm.entrySet();

		// Iterate using EntrySet
		int i = 1;
		int totalRequests = entrySet.size();
		for (Entry entry : entrySet) {
			System.out.println("\n:::Sending API request "+ i + " of " + totalRequests);
			i++;
			String apibody = (String) entry.getValue();
			System.out.println("\n with apiurl "+ apiurl );
			System.out.println("\n with apibody "+ apibody );
			// Building request using requestSpecBuilder
			RequestSpecBuilder builder = new RequestSpecBuilder();

			// Setting API's body
			builder.setBody(apibody);

			// Setting content type as application/json or application/xml
			builder.setContentType("application/json; charset=UTF-8");
			System.out.println("\n with content type application/json; charset=UTF-8");
			
			RequestSpecification requestSpec = builder.build();

			Response response = given().spec(requestSpec).when().post(apiurl);
			
			System.out.println("\n Json Response status code is = "+ response.getStatusCode());
			// given().spec(requestSpec).when().post(APIUrl).then().body("data.key1",equalTo("test1")).body("data.key2",equalTo("test2"));

			JSONObject jsonresbody = new JSONObject(response.body().asString());

			System.out.println("\n Json Response header is = "+ jsonresbody.get("headers"));
			System.out.println("\n Json Response data is = "+jsonresbody.get("data"));
			
			// data we passing as part of body is JSON cast to JSONObject
			JSONObject dataObject = (JSONObject) jsonresbody.get("data");
			String result = jsonresbody.toString(); // will print entire
			// response
			System.out.println("\n Full Response of API request is " + result);

			/*
			 * {"args":{},
			 * "headers":{"content-length":"31","x-forwarded-proto":"https",
			 * "host":
			 * "postman-echo.com","x-forwarded-port":"443","content-type":"
			 * application/json;
			 * charset=UTF-8","accept-encoding":"gzip,deflate","accept":"*
			 * ","user- agent":"Apache-HttpClient/4.3.5 (java 1.5)"},
			 * "data":{"key1":"test1","key2":"test2"},"form":{},"files":{},
			 * "json":{ "key1":"test1","key2":"test2"},"url":
			 * "https://postman-echo.com/post"}
			 */

		}
	}
}
