package com.example.demo.WebFlux.webClient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/webclient")
public class server_controller{
    private final Logger log = LoggerFactory.getLogger(getClass());
	private int num = 0;
    @GetMapping("/{param}")
	public String test_get(
		@PathVariable String param, 
		@RequestHeader HttpHeaders headers,
		@CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {
		String msg = param + " => Working successfully !!!";

		return msg;
	}

	@PostMapping("/")
	public String test_post(
		@RequestHeader HttpHeaders headers,
		@CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {
		String msg = " => Working successfully !!!";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@GetMapping("/testBlock")
	public String test_block(
		@RequestHeader HttpHeaders headers,
		@CookieValue(name = "httpclient-type", required=false, defaultValue="undefined") String httpClientType) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "testing";
	}
}