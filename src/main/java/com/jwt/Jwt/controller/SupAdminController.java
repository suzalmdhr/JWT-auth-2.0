package com.jwt.Jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/supadmin")
public class SupAdminController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){

        return "Only for sup admin";
    }

}
