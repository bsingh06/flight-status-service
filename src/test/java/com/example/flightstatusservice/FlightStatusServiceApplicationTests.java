package com.example.flightstatusservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


import com.example.flightstatusservice.entity.FlightInfo;
import com.example.flightstatusservice.service.FlightStatusService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class FlightStatusServiceApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	@Qualifier("randomFlightStatusService")
	private FlightStatusService flightStatusService;

	@Test
	void testGetFlightStatus() {
		flightStatusService.ResetFlightInfo();
		FlightInfo flightInfo = flightStatusService.GetFlightInfo();
		webTestClient.get().uri("api/flight/v1/" + flightInfo.getFlightNo()).exchange().expectStatus().is2xxSuccessful();
	}
	
	@Test
	void testGetFlightStatusNotFound() {
		webTestClient.get().uri("api/flight/v1/ABC101").exchange().expectStatus().isNotFound();
	}
	
	@Test
	void testUpdateFlightStatus() {
		flightStatusService.ResetFlightInfo();
		FlightInfo flightInfo = flightStatusService.GetFlightInfo();
		webTestClient.put().uri("api/flight/v1/"+ flightInfo.getFlightNo()+"/Delayed").exchange().expectStatus().is2xxSuccessful();
	}
	
	@Test
	void testUpdateFlightStatusNotFound() {
		webTestClient.put().uri("api/flight/v1/ABC101/Delayed").exchange().expectStatus().isNotFound();
	}
	
	@Test
	void testUpdateFlightStatusBadRequest() {
		flightStatusService.ResetFlightInfo();
		FlightInfo flightInfo = flightStatusService.GetFlightInfo();
		webTestClient.put().uri("api/flight/v1/" + flightInfo.getFlightNo()+ "/Not Arrived").exchange().expectStatus().isBadRequest();
	}


}
