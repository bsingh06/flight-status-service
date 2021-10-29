package com.example.flightstatusservice.service;


import com.example.flightstatusservice.entity.FlightInfo;

public interface FlightStatusService {
	
	FlightInfo GetFlightInfo();
	void ResetFlightInfo();
}
