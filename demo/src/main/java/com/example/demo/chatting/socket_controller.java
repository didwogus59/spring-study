package com.example.demo.chatting;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;
import com.example.demo.test;


@Controller
public class socket_controller {


  @GetMapping("/socket/test1")
  public String test() {
    return "chatting/index";
  }
  
  @GetMapping("/socket/test2")
  public String test2() {
    return "chatting/test";
  }

  @MessageMapping("/testM")
  @SendTo("/broker/testB")
  public test test_socket(test msg) throws Exception {
    System.out.println(msg.getData());
    return new test("hello", msg.getData());
  }

  @MessageMapping("/testMsg")
  @SendTo("/broker/greeting")
  public test greeting(test msg) throws Exception {
    Thread.sleep(1000); // simulated delay
    System.out.println(msg.getData());
    return new test("Hello", msg.getData());
  }
}