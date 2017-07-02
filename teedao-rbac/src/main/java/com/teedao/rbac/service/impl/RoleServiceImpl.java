package com.teedao.rbac.service.impl;

import com.teedao.rbac.dao.RoleMapper;
import com.teedao.rbac.entity.Role;
import com.teedao.rbac.service.RoleService;
import com.teedao.rbac.param.RoleParam;
import com.teedao.rbac.result.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    public boolean createRole(Role role) {
        role.setCreateTime(System.currentTimeMillis());
        role.setLastModifyTime(System.currentTimeMillis());
        return roleMapper.insertSelective(role) > 0 ? true : false;
    }

    public boolean updateRole(Role role) {
        role.setLastModifyTime(System.currentTimeMillis());
        return roleMapper.updateByPrimaryKeySelective(role) > 0 ? true : false;
    }

    public boolean deleteRole(Long roleId) {
        return roleMapper.deleteByPrimaryKey(roleId) > 0 ? true : false;
    }


    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    @Override
    public DataTable<Role> getRolePage(RoleParam params) {
        int count = roleMapper.selectPageCount(params);
        List<Role> list = roleMapper.selectPageList(params);
        DataTable<Role> pager = new DataTable<>(params.getDraw(), count, list);
        return pager;
    }

}
