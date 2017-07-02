package com.teedao.rbac.service;

import com.teedao.rbac.entity.Site;
import com.teedao.rbac.param.SiteParam;
import com.teedao.rbac.result.DataTable;

import java.util.List;

public interface SiteService {


    public boolean createSite(Site site);
    public boolean updateSite(Site site);
    public boolean deleteSite(Long siteId);

    public Site findOne(Long siteId);
    public List<Site> findAll();
	public DataTable<Site> getSitePage(SiteParam params);

    Site findByClientId(String clientId);

    Site findByClientSecret(String clientSecret);
}
