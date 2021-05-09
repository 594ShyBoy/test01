package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){

        return "框架整合初步完成!!!";

    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){

        //1. 查询业务层 获取数据
        List<User> userList = userService.findAll();
        //2. 将数据保存到Model对象中返回给页面
        return userList;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Integer deleteById(@PathVariable("id") Integer id){


        return userService.deleteUserById(id);
    }

    @RequestMapping("/updatePage")
    public String updatePage(){



        return "userUpdate";
    }

    /**
     * 查询所有用户列表数据,在userList.html中展现数据
     */

    @RequestMapping("/userList")
    public String userList(Model model){

        //1. 查询业务层 获取数据
        List<User> userList = userService.findAll();
        //2. 将数据保存到Model对象中返回给页面
        model.addAttribute("userList",userList);

        return "userList";
    }

    /*@GetMapping("/findById" + "/{id}")
    public String findById(@PathVariable Integer id, Model model){

        //1. 查询业务层 获取数据
        User user = userService.findById();
        //2. 将数据保存到Model对象中返回给页面
        model.addAttribute("user",user);

        return "userList";
    }*/

    /**
     * 利用restFul实现用户数据新增 之后重定向到userList.html页面
     * url地址:/user/tom/18/男
     * 优化策略:
     *      如果有多个参数提交 则可以使用对象接收 但是要求参数名称必须与属性名称一致
     * @return
     */
    @GetMapping("/user" + "/{name}" + "/{age}" + "/{sex}")
    public String addUser( User user
                           /*@PathVariable String name,
                           @PathVariable Integer age,
                           @PathVariable String sex*/){

        int i = userService.addUser(user);
        System.out.println(i);
        System.out.println(i);

        return "redirect:/userList";//重新请求userList
    }

    /**
     * 利用restFul实现用户数据修改
     *      之后重定向到userList
     *      url:/user/id/name
     * @param user
     * @return
     */
    @GetMapping("/user" + "/{id}" + "/{name}")
    public String updateUser( User user){

        int i = userService.updateUser(user);
        System.out.println(i);

        return "redirect:/userList";//重新请求userList
    }

    /**
     * 利用restFul实现数据删除
     *      url:/user/100(id)
     *      之后重定向到userList.html页面
     * @param id
     * @return
     */
    @GetMapping("/user" + "/{id}")
    public String deleteUserById(@PathVariable("id") Integer id){

        int i = userService.deleteUserById(id);
        System.out.println(i);

        return "redirect:/userList";//重新请求userList
    }

    /**
     * 用户通过http://localhost:8090/userAjax 要求跳转到userAjax页面中
     * 通过Ajax请求向后端服务器
     */
    @RequestMapping("/userAjax")
    public String userAjax(){

        return "userAjax1";
    }

    /**
     * 接收Ajax请求  /findAjaxUser
     * 返回值结果: JSON字符串
     * @return
     */
    @RequestMapping("/findAjaxUser")
    @ResponseBody
    public List<User> findAjaxUser(){
        //[{},{}]

        return userService.findAll();

    }

}













