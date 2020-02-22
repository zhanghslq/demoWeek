package com.zhs.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDZ
 */
@Controller
public class NotFoundExcption implements ErrorController {
 
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
 
 
    @RequestMapping("/error")
    @ResponseBody
    public  Object  error(){
        Map<String, Object> map = new HashMap<>();
        map.put("error", "not found");
        map.put("code", "404");
        return map;
    }
 
 
}