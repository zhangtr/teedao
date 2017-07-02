package com.teedao.rbac.dao;

import com.teedao.rbac.entity.Role;
import com.teedao.rbac.param.RoleParam;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectListByRoleIds(String ids);

    List<Role> selectAll();

    int selectPageCount(RoleParam param);

    List<Role> selectPageList(RoleParam param);
}