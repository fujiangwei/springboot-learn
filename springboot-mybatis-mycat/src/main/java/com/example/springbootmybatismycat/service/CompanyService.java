package com.example.springbootmybatismycat.service;

import com.example.springbootmybatismycat.domain.Company;
import com.example.springbootmybatismycat.domain.User;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:55
 * @modifier:
 * @since:
 */
public interface CompanyService {

    List<Company> listCompany();

    void addCompany(final Company company);
}
