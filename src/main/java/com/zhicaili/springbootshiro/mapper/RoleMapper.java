package com.zhicaili.springbootshiro.mapper;


import com.zhicaili.springbootshiro.pojo.Role;
import com.zhicaili.springbootshiro.util.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}