package com.example.flightstatusservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.flightstatusservice.entity.FlightInfo;

@Repository
public interface FlightStatusElasticRepository extends ElasticsearchRepository<FlightInfo, String> {

	FlightInfo findByFlightNo(String flightNo);
	

}
