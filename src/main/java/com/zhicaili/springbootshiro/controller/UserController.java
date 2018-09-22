package com.zhicaili.springbootshiro.controller;

import com.github.pagehelper.PageInfo;
import com.zhicaili.springbootshiro.pojo.User;
import com.zhicaili.springbootshiro.pojo.UserRole;
import com.zhicaili.springbootshiro.service.UserRoleService;
import com.zhicaili.springbootshiro.service.UserService;
import com.zhicaili.springbootshiro.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 查询用户列表
     * @param user
     * @param draw
     * @param start
     * @param lenght
     * @return
     */
    @RequestMapping
    public Map<String,Object> getAll(User user , String draw,
                                     @RequestParam(required =false,defaultValue = "1") int start,
                                     @RequestParam(required = false,defaultValue = "10")int lenght){
        Map<String ,Object> map=new HashMap<>();
        PageInfo<User> pageInfo = userService.selectByPage(user, start, lenght);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        try {
            userService.delUser(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public String add(User user){
        User u = userService.selectByUsername(user.getUsername());
        if (u!=null){
            return "error";
        }
        try {
            user.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            //加盐
            passwordHelper.encryptPassword(user);
            userService.save(user);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 保存用户角色
     * @param userRole  用户角色
     * @return
     */
    @RequestMapping("saveUserRoles")
    public String saveUserRoles(UserRole userRole){
        if (StringUtils.isEmpty(userRole.getUserid())){
            return "error";
        }
        try {
            userRoleService.addUserRole(userRole);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
