package com.example.demo.WebFlux.webClient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class server_controller{
    private final Logger log = LoggerFactory.getLogger(getClass());
	private int num = 0;
    @GetMapping("/webclient/get/{param}")
	public String test_get(
		@PathVariable String param, 
		@RequestHeader HttpHeaders headers,
		@CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {

		String msg = param + " => Working successfully !!!";

		return msg;
	}

	@GetMapping("/webclient/post/{param}")
	public String test_post(
		@PathVariable String param, 
		@RequestHeader HttpHeaders headers,
		@CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {
		String msg = param + " => Working successfully !!!";
		return msg;
	}
}