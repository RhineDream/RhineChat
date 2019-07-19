package com.controller;

import com.model.ChatRecord;
import com.model.User;
import com.service.ChatService;
import com.service.UserService;
import com.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author：RhineDream
 * 聊天controller
 */
@RestController
@RequestMapping("/user")
public class ChatController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    @RequestMapping("/getUserList")
    public ResponseResult getUserList(@RequestBody(required = false) User user){
        List<User> list = userService.getUserList(user);
        return ResponseResult.ok(list);
    }

}
