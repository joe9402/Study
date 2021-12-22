package com.example.selfStudy.controller;

import com.example.selfStudy.dto.ResponseDTO;
import com.example.selfStudy.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String testController(){
        return "Hello World";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Hello World testGetMapping";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! id : " + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! id : " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "Hello World! id : " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
    }
    
    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("\"Hello World! I'm ResponseEntity. And you get 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/testResponseEntityOK")
    public ResponseEntity<?> testControllerResponseEntityOK(){
        List<String> list = new ArrayList<>();
        list.add("\"Hello World! I'm ResponseEntity. And you get OK!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }


}
