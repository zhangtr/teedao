package com.teedao.rbac.dao;

import com.teedao.rbac.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectListByResourceIds(String ids);

    List<Resource> selectAll();

    List<Resource> selectBySiteNo(String siteNo);
}