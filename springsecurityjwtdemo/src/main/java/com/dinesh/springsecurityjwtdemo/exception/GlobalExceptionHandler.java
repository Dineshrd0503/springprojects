package com.dinesh.springsecurityjwtdemo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(AlienNotFoundException.class)
        public ResponseEntity<Object> handleException(AlienNotFoundException ex, WebRequest request){
                Map<String,Object> map=new HashMap<>();
                map.put("timestamp", LocalDateTime.now());
                map.put("msg",ex.getMessage());
                map.put("details",request.getDescription(false));
                return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<Object> handleException(UserNotFoundException ex, WebRequest request){
                Map<String,Object> map=new HashMap<>();
                map.put("timestamp", LocalDateTime.now());
                map.put("msg",ex.getMessage());
                map.put("details",request.getDescription(false));
                return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request){
                Map<String,Object> map=new HashMap<>();
                map.put("timestamp", LocalDateTime.now());
                map.put("msg",ex.getMessage());
                map.put("details",request.getDescription(false));
                return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
