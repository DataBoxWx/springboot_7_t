package com.wkcto.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wkcto.springboot.model.User;
import com.wkcto.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:UserController
 * package:com.wkcto.springboot.controller
 * Description:
 *
 * @Data:2018/7/29 10:45
 * @Auther:wangxin
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping(value = "/all/users")
    public String getUser(@RequestParam(value = "currentPage",required = false) Integer currentPage,
                        Model model){
        if(currentPage == null){
            currentPage = 1;
        }
        int pageSize = 6;
        int startRow = (currentPage - 1) * pageSize;
        List<User> userList = userService.getUserByPage(startRow, pageSize);
        int totalRows = userService.getUserByTotal();
        int totalPage = totalRows / pageSize;
        if(totalRows % pageSize > 0){
            totalPage++;
        }

        model.addAttribute("userList",userList);
        model.addAttribute("totalRows",totalRows);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        return "users";
    }

    @GetMapping(value = "/all/user")
    public String toAddUser(){
        return "user";
    }

    @PostMapping("/all/user")
    public String addUser(User user){
        System.out.println("添加用户");
        userService.insertUser(user);

        return "redirect:/all/users";
    }

    @GetMapping("/all/user/{id}")
    public String toUpdateUser(@PathVariable("id") Integer id,Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "user";
    }

    @PutMapping(value = "/all/user")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/all/users";
    }

    @DeleteMapping("/all/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/all/users";
    }
}
