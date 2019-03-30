package com.example.springbootmybatismycat.controller;

import com.example.springbootmybatismycat.domain.Company;
import com.example.springbootmybatismycat.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 21:58
 * @modifier:
 * @since:
 */
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "listCompany")
    public List<Company> listCompany() {
        return companyService.listCompany();
    }

    @GetMapping(value = "addCompany")
    public String addCompany(Company company) {
        companyService.addCompany(company);

        return "ok";
    }
}
