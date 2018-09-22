package com.zhicaili.springbootshiro.controller;

import com.github.pagehelper.PageInfo;
import com.zhicaili.springbootshiro.pojo.Resources;
import com.zhicaili.springbootshiro.service.ResourcesService;
import com.zhicaili.springbootshiro.shiro.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;
    @Resource
    private ShiroService shiroService;

    @RequestMapping
    public Map<String,Object> getAll(Resources resources, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Resources> pageInfo = resourcesService.selectByPage(resources, start, length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/loadMenu.do")
    public List<Resources> loadMenu(){
        Map<String, Object> map = new HashMap<>();
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type",1);
        map.put("userid",userId);
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    @RequestMapping("resourcesWithSelected")
    public List<Resources> resourcesWithSelected(Integer rid){
        return resourcesService.queryResourcesListWithSelected(rid);
    }

    //@CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/add")
    public String add(Resources resources){
        try{
            resourcesService.save(resources);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //@CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            resourcesService.delete(id);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}