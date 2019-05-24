package com.jiefeng.ssm.shiro.realm.filter;

import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyUserFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",false);
        modelMap.put("errMsgCode",-2);
        modelMap.put("errMsg","登录凭证过期");
        System.out.println("-----------------------------------------");
        System.out.println("进来了user");
        httpServletResponse.getWriter().write(JSONObject.fromObject(modelMap).toString());
        return false;
    }
}
