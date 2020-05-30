package it.polito.ezgas.controllertests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

public class TestController {
	
	//UserController
	@Test
	public void testGetUserById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/2");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		assertTrue(response.getStatusLine().getStatusCode() == 200);
		assertTrue(jsonFromResponse.contains("lucaoddone@polito.it"));
	}
	
	/*@Test
	public void testSaveUser() throws ClientProtocolException, IOException {
	} */
	
	@Test
	public void testGetAllUsers() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userDtoArray = mapper.readValue(jsonFromResponse, UserDto[].class);
		
		assertTrue(userDtoArray.length == 6);
	}
	
	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpDelete("http://localhost:8080/user/deleteUser/3");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertTrue(response.getStatusLine().getStatusCode() == 200);
	}
	
	//GasStationController
	@Test
	public void testGetGasStationById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStation/2");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		assertTrue(response.getStatusLine().getStatusCode() == 200);
		assertTrue(jsonFromResponse.contains("GasStation2"));
	}
	
	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userDtoArray = mapper.readValue(jsonFromResponse, UserDto[].class);
		
		assertTrue(userDtoArray.length == 5);
	}
	
	@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/3");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertTrue(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testGetGasStationsByGasolineType() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/Diesel");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationDtoArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		
		assertTrue(gasStationDtoArray.length == 2);
	}

	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.5066977/8.128328/Diesel/Car2Go");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationDtoArray = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		
		assertTrue(gasStationDtoArray.length == 2);
	}
	
}
