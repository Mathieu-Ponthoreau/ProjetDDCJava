package com.projetddc.demo.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    
    @PostMapping("/test")
    public HttpStatusCode testPost(){
        logger.info("Testing post mapping");
        return(HttpStatus.OK);
    }
    
}
