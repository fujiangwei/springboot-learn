package com.example.springbootrpc.client;

import java.util.List;

public interface PersonService {
    List<Person> GetTestPerson(String name, int num);
}
