package com.teedao.rbac.dao;

import com.teedao.rbac.entity.Oplog;
import com.teedao.rbac.param.OplogParam;

import java.util.List;

public interface OplogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Oplog record);

    int insertSelective(Oplog record);

    Oplog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Oplog record);

    int updateByPrimaryKey(Oplog record);

    List<Oplog> selectAll();

    int selectPageCount(OplogParam param);

    List<Oplog> selectPageList(OplogParam param);
}