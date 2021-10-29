package com.example.flightstatusservice.common;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.flightstatusservice.entity.FlightInfo;
import com.example.flightstatusservice.repository.FlightStatusElasticRepository;
import com.example.flightstatusservice.service.FlightStatusService;

@Component
public class FlightStatusElasticDataSource {
	
	private static final Logger LOG = LoggerFactory.getLogger(FlightStatusElasticDataSource.class);
	
	@Autowired
	private FlightStatusElasticRepository flightStatusRepository;
	
	@Autowired
	@Qualifier("randomFlightStatusService")
	private FlightStatusService flightStatusService;
	
	@Autowired
	@Qualifier("webClientElasticsearch")
	private WebClient webClient;

	@EventListener(ApplicationReadyEvent.class)
	public void populateData() {
		var response = webClient.delete().uri("/flight-status").retrieve().bodyToMono(String.class).block();
		LOG.info("End delte with response {}", response);
		
		var flightInfo = new ArrayList<FlightInfo>();
		for(int i = 0; i < 500; i++) {
			flightInfo.add(flightStatusService.GetFlightInfo());
		}
		flightStatusRepository.saveAll(flightInfo);
		LOG.info("Saved {} flightInfo", flightInfo.size());
	}
}
