package com.teedao.rbac.service;

import com.teedao.rbac.entity.Oplog;
import com.teedao.rbac.param.OplogParam;
import com.teedao.rbac.result.DataTable;

import java.util.List;

public interface OplogService {
    Oplog findOne(Long id);

    List<Oplog> findAll();

    DataTable<Oplog> getOplogPage(OplogParam params);

    boolean createLog(Oplog log);

}
