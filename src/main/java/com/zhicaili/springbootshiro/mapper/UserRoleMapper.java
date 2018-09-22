package com.zhicaili.springbootshiro.mapper;


import com.zhicaili.springbootshiro.pojo.UserRole;
import com.zhicaili.springbootshiro.util.MyMapper;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);
}