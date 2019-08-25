package com.example.springbootrpc.client;

public interface HelloService {
    String hello(String name);

    String hello(Person person);
}
