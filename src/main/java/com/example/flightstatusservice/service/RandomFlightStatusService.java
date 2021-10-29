package com.example.flightstatusservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flightstatusservice.entity.FlightInfo;

@Service
public class RandomFlightStatusService implements FlightStatusService {
	Integer flightNo = 100;
	Integer startCity = 0;
	Integer endCity = 1;
	public List<String> FLIGHT_STATUS = List.of("Delayed", "Ontime", "Not running", "Postpone", "Rescheduled");
	@Override
	public FlightInfo GetFlightInfo() {
		flightNo++; 
		startCity = startCity + 2;
		endCity = endCity + 2;
		
		return new FlightInfo("EW" + flightNo, "City_" + startCity, "City_" + endCity, LocalDateTime.now()
				, LocalDateTime.parse("2018-01-11T08:15:30"), FLIGHT_STATUS.get(flightNo % 5));
	}
	
	public void ResetFlightInfo() {
		flightNo = 100;
		startCity = 0;
		endCity = 1;
	}

}
