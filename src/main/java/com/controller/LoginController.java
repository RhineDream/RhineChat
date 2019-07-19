package com.controller;

import com.model.User;
import com.service.UserService;
import com.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    /**
     * 登陆界面
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 聊天界面
     */
    @GetMapping("/index")
    public ModelAndView index(String username, String password, HttpServletRequest request) throws UnknownHostException {
        ModelAndView mav = new ModelAndView("chat");
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }

        User user = userService.getUserByName(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                System.out.println("系统用户登录...."+username);
                mav.addObject("username", user.getName());
            }else {
                System.out.println("系统用户登录....账号密码不对！！！"+username);
                return new ModelAndView("login");
            }

        }else {
            System.out.println("临时用户登录...."+username);
            mav.addObject("username", username);
        }

        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
        return mav;
    }

    /**
     * 聊天界面
     */
    @GetMapping("/index2")
    public ModelAndView index2(String username, String password, HttpServletRequest request) throws UnknownHostException {
        ModelAndView mav = new ModelAndView("chat2");
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }

        User user = userService.getUserByName(username);
        /*if(user != null){
            if(user.getPassword().equals(password)){
                System.out.println("系统用户登录...."+username);
                mav.addObject("username", user.getName());
            }else {
                System.out.println("系统用户登录....账号密码不对！！！"+username);
                return new ModelAndView("login");
            }

        }else {
            System.out.println("你还没注册，赶紧注册一个玩玩吧....！！！"+username);
            return new ModelAndView("login");
        }*/

        mav.addObject("username", user.getUsername());
        mav.addObject("name", user.getName());
        mav.addObject("userId", user.getId());

        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
        return mav;
    }

    /**
     * 聊天界面
     */
    @GetMapping("/register")
    public ModelAndView register(String username, String password, HttpServletRequest request) throws UnknownHostException {
        ModelAndView mav = new ModelAndView("reg");
        return mav;
    }

    /**
     * 聊天界面
     */
    @RequestMapping("/dologin")
    @ResponseBody
    public ResponseResult dologin(@RequestBody User currUser, HttpServletRequest request,HttpSession session) throws UnknownHostException {
        ModelAndView mav = new ModelAndView("chat2");
        /*if (StringUtils.isEmpty(currUser.getUsername())) {
            currUser.getUsername() = "匿名用户";
        }*/

        User user = userService.getUserByName(currUser.getUsername());
        if(user != null){
            if(user.getPassword().equals(currUser.getPassword())){
                System.out.println("系统用户登录...."+currUser.getUsername());
                mav.addObject("username", user.getName());
            }else {
                System.out.println("系统用户登录....账号密码不对！！！"+currUser.getUsername());
                return ResponseResult.build(400,"账号密码不对！！！");
            }

        }else {
            System.out.println("你还没注册，赶紧注册一个玩玩吧....！！！"+currUser.getUsername());
            return ResponseResult.build(400,"你还没注册，赶紧注册一个玩玩吧！！！");
        }

        session.setAttribute("userInfo",user);
        User userInfo = (User) session.getAttribute("userInfo");
        String username = userInfo.getUsername();

        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
        return ResponseResult.build(200,"登录成功");
    }

    /**
     * 聊天界面
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseResult logout(@RequestBody(required = false) User currUser, HttpSession session) throws UnknownHostException {

        User userInfo = (User) session.getAttribute("userInfo");
//        String username = userInfo.getUsername();
        //session.invalidate();
//        System.out.println("用户---"+username+"---已退出登录");
        return ResponseResult.build(200,"退出成功！欢迎下次再来");
    }

    /**
     * 聊天界面
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public ResponseResult doRegister(@RequestBody User currUser, HttpServletRequest request) throws UnknownHostException {

        Assert.hasText(currUser.getUsername(),"账号不能为空");
        Assert.hasText(currUser.getPassword(),"密码不能为空");
        Assert.hasText(currUser.getName(),"昵称不能为空");

        User user = userService.getUserByName(currUser.getUsername());

        if(user != null){
            return ResponseResult.build(400,"账号已存在");
        }else {
            currUser.setId(UUID.randomUUID().toString());
            currUser.setCreateDate(new Date());
            currUser.setIsOnline("0");
            userService.insert(currUser);
            return ResponseResult.build(200,"注册成功");
        }


    }
}
