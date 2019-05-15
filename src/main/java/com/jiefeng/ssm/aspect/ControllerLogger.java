package com.jiefeng.ssm.aspect;


import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.redis.JedisUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@Aspect
public class ControllerLogger {

    Logger logger =LoggerFactory.getLogger(ControllerLogger.class);

    @Autowired
    private static JedisUtil.Strings strings;

    /**
     * Before注解可以创建代理，然后将方法中的放在特定的时间执行，这里的before是通知在方法前执行。但是需要在xml文件中开启：
     * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     * 其中的execution是执行器，他的参数指定了那些具体的类和那些方法，其中*占位符可以匹配多个方法或参数
     * 例如 * com.jiefeng.aop.CalculatorImpl.*(int,int))
     * 制定了这个包下的这个方法中所有的方法而不仅仅是public
     * @param joinPoint 这个参数可以获取当前执行方法的名字或者是当前执行方法的参数列表，以及其他的信息等
     */
    @Around("execution(* com.jiefeng.ssm.web.admin.*.* (..))")
    public Object beforeMethod(ProceedingJoinPoint joinPoint){
        Object obj = null;

        UserExtend principal = (UserExtend)SecurityUtils.getSubject().getPrincipal();
        String username = principal.getUsername();
        Object[] args = joinPoint.getArgs();

        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String ip = request.getRemoteAddr();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        Map<String,Object> modelMap = (Map<String, Object>) obj;

        String describe = (String) modelMap.get("describe");

        // 打印耗时的信息
        logger.info("IP地址为: " + ip + " 用户: " + username + " 执行了:" +
                methodName +"方法   " + "耗时: " + (endTime - startTime) +"ms " + "  描述: " + describe);

        return obj;
    }





    public static void main(String[] args) {
        strings.set("view","0");

        strings.incrBy("view",1);

        strings.get("view");
    }
}
