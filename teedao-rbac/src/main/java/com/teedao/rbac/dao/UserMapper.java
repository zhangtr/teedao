package com.teedao.rbac.dao;

import com.teedao.rbac.entity.User;
import com.teedao.rbac.param.UserParam;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    int selectPageCount(UserParam param);

    List<User> selectPageList(UserParam param);
}