package com.teedao.rbac.service.impl;

import com.teedao.rbac.dao.OplogMapper;
import com.teedao.rbac.entity.Oplog;
import com.teedao.rbac.param.OplogParam;
import com.teedao.rbac.result.DataTable;
import com.teedao.rbac.service.OplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OplogServiceImpl implements OplogService {
    @Autowired
    OplogMapper oplogMapper;

    @Override
    public boolean createLog(Oplog log) {
        int i = oplogMapper.insertSelective(log);
        return i > 0 ? true : false;
    }

    @Override
    public Oplog findOne(Long id) {
        return oplogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Oplog> findAll() {
        return oplogMapper.selectAll();
    }

    @Override
    public DataTable<Oplog> getOplogPage(OplogParam params) {
        int count = oplogMapper.selectPageCount(params);
        List<Oplog> list = oplogMapper.selectPageList(params);
        DataTable<Oplog> pager = new DataTable<>(params.getDraw(),count,list);
        return pager;
    }
}
