package com.example.springboot;

import com.example.springboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    Properties properties;

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, properties.getEnv()));
    }


    @RequestMapping(value="/simpleGet",method = RequestMethod.GET)
    public PostResponse getTest(){
        PostResponse response = new PostResponse();
        response.setId(100);
        response.setMessage("env " + properties.getEnv());
        response.setExtra("Some text");
        return  response;
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public PostResponse testApp(@RequestBody PostRequest inputPayload) {
        PostResponse response = new PostResponse();
        response.setId(inputPayload.getId()*100);
        response.setMessage("Hello "+inputPayload.getName());
        response.setExtra("Some text");
        return response;
    }


    @RequestMapping(value = "/calculatorAddition", method = RequestMethod.POST)
    public CalculatorResult test(@RequestBody Calculator calculator) {
       int result = calculator.getNumberOne() + calculator.getNumberTwo();
       System.out.println("result is"+result);
       CalculatorResult calculatorResult = new CalculatorResult();
       calculatorResult.setResult(result);
       return calculatorResult;

    }


}