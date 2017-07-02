package com.teedao.rbac.service;

import com.teedao.rbac.entity.Role;
import com.teedao.rbac.param.RoleParam;
import com.teedao.rbac.result.DataTable;

import java.util.List;

public interface RoleService {


    public boolean createRole(Role role);

    public boolean updateRole(Role role);

    public boolean deleteRole(Long roleId);

    public Role findOne(Long roleId);

    public List<Role> findAll();

    public DataTable<Role> getRolePage(RoleParam params);

}
