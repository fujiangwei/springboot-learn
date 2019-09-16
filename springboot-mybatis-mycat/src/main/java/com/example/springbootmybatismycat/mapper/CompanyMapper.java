package com.example.springbootmybatismycat.mapper;

import com.example.springbootmybatismycat.domain.Company;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:43
 * @modifier:
 * @since:
 */
public interface CompanyMapper {

    List<Company> selectCompanyList();

    void addCompany(final Company company);
}
