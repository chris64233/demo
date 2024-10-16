package com.example.demo.mapper;

import com.example.demo.domain.DesignMaterial;

public interface DesignMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DesignMaterial record);

    int insertSelective(DesignMaterial record);

    DesignMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesignMaterial record);

    int updateByPrimaryKey(DesignMaterial record);
}