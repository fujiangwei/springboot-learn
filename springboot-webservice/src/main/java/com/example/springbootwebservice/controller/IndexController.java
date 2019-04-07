package com.example.springbootwebservice.controller;

import com.example.springbootwebservice.config.client.WsClient;
import com.example.springbootwebservice.domain.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private WsClient wsClient;

    @RequestMapping("getCountry")
    public Object getCountry(String name) {
        GetCountryResponse response = wsClient.getCountry(name);

        return response.getCountry();
    }

}