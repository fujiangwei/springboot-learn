package com.example.springbootmybatismycat.service.impl;

import com.example.springbootmybatismycat.domain.Company;
import com.example.springbootmybatismycat.domain.User;
import com.example.springbootmybatismycat.mapper.CompanyMapper;
import com.example.springbootmybatismycat.mapper.UserMapper;
import com.example.springbootmybatismycat.service.CompanyService;
import com.example.springbootmybatismycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:56
 * @modifier:
 * @since:
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> listCompany() {
        return companyMapper.selectCompanyList();
    }

    @Override
    public void addCompany(Company company) {
        companyMapper.addCompany(company);
    }
}
