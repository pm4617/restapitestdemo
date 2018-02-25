/*
Copyright Open-source 2017
For Devlops branch
*/
package com.api.restdemo.restapitestdemo;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AppTest1 {

	// reads folder files and
	public HashMap<Integer, String> putJsonFilesInHashMap(String pathoffolder) {
		HashMap<String, String> hm = new HashMap<String, String>();
		HashMap<Integer, String> vf = new HashMap<Integer, String>();
		Integer i = 0;
		File folder = new File(pathoffolder);
		boolean lastLineReadEmpty = false;
		File[] filesinfolder = folder.listFiles();
		System.out.println(pathoffolder);
		String filecontents = "", line = "";
		for (File f : filesinfolder) {
			Charset charset = Charset.forName("UTF-8");
			BufferedReader reader;
			filecontents = "";
			System.out.println(f.getName());
			// read file and put contents in filecontents variable
			try {
				reader = Files.newBufferedReader(f.toPath(), charset);
				while ((line = reader.readLine()) != null) {
					
					if (!line.isEmpty() ) {
						 
						filecontents += line;
						System.out.println(line);
						// if line is not blank set 
						lastLineReadEmpty = false;
					}
					// line is empty and last read line is not blank
					else if(!lastLineReadEmpty) {
						vf.put(i, filecontents);
						filecontents = "";
						lastLineReadEmpty = true;
						i++;
					}
					// to add if current line is last line 
					vf.put(i, filecontents);					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// add file name and file contente in hashmap
			// {key:abc.json , value:filecontents}
			hm.put(f.getName(), filecontents);
		
		}
		
		
		// prints hashmap and removes entries which are empty
		System.out.println("Size of Hashmap containing file contents is = " + vf.size());
		System.out.println("\nPrinting Hashmap Key Values...");
		
		// Remove empty entries from vf 
		Iterator<Map.Entry<Integer, String>> entrySetIterator = vf.entrySet().iterator();
		while (entrySetIterator.hasNext())
		{			
		Map.Entry<Integer, String> entry = entrySetIterator.next();
		
		Integer key = entry.getKey();
		String value = entry.getValue();
		  if (value.isEmpty() ||  value.trim().isEmpty() )
			{	
				System.out.println("\nRemoving Hashmap Key Values @ ..." + key);
				entrySetIterator.remove();
			}
		 
		}
		System.out.println("Size of Hashmap containing file Name and contents is = " + vf.size());
		return vf;
	}

	@Test
	public void httpPost() throws JSONException, InterruptedException {
		URL apiURI = null;
		HashMap<Integer, String> hm = putJsonFilesInHashMap("src/jsonfiles");
		Set<Entry<Integer, String>> entrySet = hm.entrySet();
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


