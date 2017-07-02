package com.teedao.rbac.dao;

import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.SiteParam;

import java.util.List;

public interface SiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<Site> selectAll();

    int selectPageCount(SiteParam param);

    List<Site> selectPageList(SiteParam param);

    Site selectByClientId(String clientId);

    Site selectByClientSecret(String clientSecret);
}