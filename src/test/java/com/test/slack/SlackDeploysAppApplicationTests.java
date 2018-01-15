package com.test.slack;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.test.slack.model.Engineer;
import com.test.slack.model.Engineers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SlackDeploysAppApplicationTests {

	private static final String ENDPOINT_ENGINEERS = "/engineers";

	@LocalServerPort
	protected int port;

	private RequestSpecification requestSpec() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("http://localhost").setPort(port);
		requestSpecBuilder.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return requestSpecBuilder.build();

	}

	protected ResponseSpecification respOk() {
		ResponseSpecification respOK = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(MediaType.APPLICATION_JSON_VALUE)
				.build();
		return respOK;
	}


	@Test
	public void testGetAllEngineers() {

        Response response =
				given(requestSpec())
				.when()
					.get(ENDPOINT_ENGINEERS)
				.then()
					.log().body()
					.spec(respOk())
				.extract().response();

		ResponseBody responseBody = response.getBody();

		Engineers engineers = responseBody.as(Engineers.class);
		assertNotNull(engineers);

		Set<Engineer> engineerSet = engineers.getEngineers();
		assertFalse(engineerSet.isEmpty());

		assertTrue(engineerSet.contains(new Engineer("brady")));
	}





}
