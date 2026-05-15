package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    List<Company> findByIndustry(@Param("industry") String industry);

    List<Company> search(@Param("keyword") String keyword);

}