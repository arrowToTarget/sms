package com.lewis.cms.controller;

import com.lewis.cms.common.annotation.TspServiceInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangminghua on 2016/5/17.
 */
@Controller
@Scope("prototype")
public class UserController {

    @RequestMapping(value = "/queryUser",method = RequestMethod.GET)
    @ResponseBody
    @TspServiceInfo(name = "SMS.CMS.UserController.queryUser",description = "query user info")
    public String queryUser(){

        return "a user";
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    @TspServiceInfo(name = "SMS.CMS.UserController.updateUser",description = "update user info")
    public String updateUser(){

        return "a user";
    }
}
