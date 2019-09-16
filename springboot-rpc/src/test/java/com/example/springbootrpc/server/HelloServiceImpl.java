package com.example.springbootrpc.server;


import com.example.springbootrpc.annotation.RpcService;
import com.example.springbootrpc.client.HelloService;
import com.example.springbootrpc.client.Person;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl() {

    }

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
