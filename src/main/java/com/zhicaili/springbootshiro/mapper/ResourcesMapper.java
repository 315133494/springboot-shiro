package com.zhicaili.springbootshiro.mapper;


import com.zhicaili.springbootshiro.pojo.Resources;
import com.zhicaili.springbootshiro.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface ResourcesMapper extends MyMapper<Resources> {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String, Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}