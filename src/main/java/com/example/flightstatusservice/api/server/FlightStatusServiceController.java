package com.example.flightstatusservice.api.server;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.flightstatusservice.response.ErrorResponse;
import com.example.flightstatusservice.service.RandomFlightStatusService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import com.example.flightstatusservice.entity.FlightInfo;
import com.example.flightstatusservice.repository.FlightStatusElasticRepository;

@RestController
@RequestMapping("/api/flight/v1")
public class FlightStatusServiceController {
	private static final Logger LOG = LoggerFactory.getLogger(FlightStatusServiceController.class);
	
	@Autowired
	private RandomFlightStatusService randomFlightStatusService;
	
	@Autowired
	private FlightStatusElasticRepository flightStatusElasticRepository;
	
	@GetMapping("/{flightNo}")
	@Operation(summary="Get flight information", description = "Get flight information for the flight no")
	public ResponseEntity<Object> GetFlightStatus(@PathVariable String flightNo) {
		LOG.info("Flight No: {}", flightNo);
		FlightInfo flightInfo = flightStatusElasticRepository.findByFlightNo(flightNo);
		
		if (flightInfo == null) {
			var errorResponse = new ErrorResponse("Flight No " + flightNo + " does not exist",  LocalDateTime.now());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		flightInfo.setFlightNo(flightNo);
		return new ResponseEntity<Object>(flightInfo, HttpStatus.OK);
	}
	
	@PutMapping("/{flightNo}/{status}")
	@Operation(summary="Update flight status", description = "Update flight status for the flight no")
	public ResponseEntity<Object> UpdateFlightStatus(
			@Parameter(description = "Flight no of thr flight") @PathVariable String flightNo, 
			@Parameter(description = "Status values can be \"Delayed\", \"Ontime\", \"Not running\", \"Postpone\", \"Rescheduled\"") @PathVariable String status ) {
		FlightInfo flightInfo = flightStatusElasticRepository.findByFlightNo(flightNo);
		
		if (flightInfo == null) {
			var errorResponse = new ErrorResponse("Flight No " + flightNo + " does not exist",  LocalDateTime.now());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
		}
		if (!randomFlightStatusService.FLIGHT_STATUS.contains(status)) {
			var errorResponse = new ErrorResponse("Flight status " + status + " is invalid",  LocalDateTime.now());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
		flightInfo.setStatus(status);
		flightStatusElasticRepository.save(flightInfo);
		return new ResponseEntity<Object>(flightInfo, HttpStatus.OK);
	}
}
