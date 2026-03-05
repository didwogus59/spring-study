package com.example.demo.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;
import com.example.demo.PostForm;


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

  

  @SendTo("/sub/test1")
  @MessageMapping("/test1")
  public String test_socket(PostForm msg) throws Exception {
    System.out.println(msg.getData());
    return msg.getData() + " from server";
  }


  @SendTo("/sub/greeting")
  @MessageMapping("/testMsg")
  public PostForm greeting(PostForm msg) throws Exception {
    Thread.sleep(1000); // simulated delay
    System.out.println(msg.getData());
    return new PostForm("Hello", msg.getData());
  }
}