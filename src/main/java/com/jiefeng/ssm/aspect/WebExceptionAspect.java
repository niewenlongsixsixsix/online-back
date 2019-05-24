package com.jiefeng.ssm.aspect;

import com.jiefeng.ssm.annotation.ArchivesLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

public class WebExceptionAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")//连接点是@RequestMapping注解的方法
    private void webPointcut() {}

    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")//切点在webpointCut()
    public void handleThrowing(JoinPoint joinPoint, Exception e) {//controller类抛出的异常在这边捕获
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        ArchivesLog archivesLog = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ArchivesLog.class);
        System.out.println("archivesLog" + archivesLog.operationName());
        Object[] args = joinPoint.getArgs();
        //开始打log
        System.out.println("异常:" + e.getMessage());
        System.out.println("异常所在类：" + className);
        System.out.println("异常所在方法：" + methodName);
        System.out.println("异常中的参数：");
        System.out.println(methodName);
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i].toString());
        }
    }

}
