package com.example.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.entity.CompanyReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyReviewMapper extends BaseMapper<CompanyReview> {

    List<CompanyReview> findByCompanyId(@Param("companyId") Long companyId);

    List<CompanyReview> findByUserId(@Param("userId") Long userId);

    Double getAverageRating(@Param("companyId") Long companyId);

    int countByCompanyId(@Param("companyId") Long companyId);

}