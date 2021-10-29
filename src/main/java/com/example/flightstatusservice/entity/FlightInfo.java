package com.example.flightstatusservice.entity;

import java.time.LocalDateTime;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "flight-status")
public class FlightInfo {
	@Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	private String flightNo;
	private String from;
	@Id
	private String Id;
	@Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	private String status;
	private String to;

	public FlightInfo() {

	}

	public FlightInfo(String flightNo, String from, String to, LocalDateTime startTime, LocalDateTime endTime,
			String status) {
		super();
		this.flightNo = flightNo;
		this.from = from;
		this.to = to;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getFrom() {
		return from;
	}

	public String getId() {
		return Id;
	}

	public String getStatus() {
		return status;
	}

	public String getTo() {
		return to;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
