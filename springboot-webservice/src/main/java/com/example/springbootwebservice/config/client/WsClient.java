package com.example.springbootwebservice.config.client;

import com.example.springbootwebservice.domain.GetCountryRequest;
import com.example.springbootwebservice.domain.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WsClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String name) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        //连接服务端
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8080/ws/countries.wsdl",
                        request);

        return response;
    }
}