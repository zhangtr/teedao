package com.teedao.rbac.service.impl;

import com.teedao.rbac.dao.SiteMapper;
import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.SiteParam;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    SiteMapper siteMapper;

    public boolean createSite(Site site) {
        site.setCreateTime(System.currentTimeMillis());
        site.setLastModifyTime(System.currentTimeMillis());
        return siteMapper.insertSelective(site) > 0 ? true : false;
    }

    public boolean updateSite(Site site) {
        site.setLastModifyTime(System.currentTimeMillis());
        return siteMapper.updateByPrimaryKeySelective(site) > 0 ? true : false;
    }

    public boolean deleteSite(Long siteId) {
        return siteMapper.deleteByPrimaryKey(siteId) > 0 ? true : false;
    }


    public Site findOne(Long siteId) {
        return siteMapper.selectByPrimaryKey(siteId);
    }

    @Override
    public DataTable<Site> getSitePage(SiteParam params) {
        int count = siteMapper.selectPageCount(params);
        List<Site> list = siteMapper.selectPageList(params);
        DataTable<Site> pager = new DataTable<>(params.getDraw(),count, list);
        return pager;
    }

    @Override
    public List<Site> findAll() {
        return siteMapper.selectAll();
    }

    @Override
    public Site findByClientId(String clientId) {
        return siteMapper.selectByClientId(clientId);
    }

    @Override
    public Site findByClientSecret(String clientSecret) {
        return siteMapper.selectByClientSecret(clientSecret);
    }
}
