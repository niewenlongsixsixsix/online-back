package com.jiefeng.ssm.aspect;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.Exception;
import com.jiefeng.ssm.bean.Logger;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dao.ExceptionDao;
import com.jiefeng.ssm.dao.LoggerDao;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

@Component
@Aspect
public class ControllerLogger {

    @Autowired
    private LoggerDao loggerDao;

    @Autowired
    private ExceptionDao exceptionDao;

    /**
     * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     * 其中的execution是执行器，他的参数指定了那些具体的类和那些方法，其中*占位符可以匹配多个方法或参数
     * 例如 * com.jiefeng.aop.CalculatorImpl.*(int,int))
     * 制定了这个包下的这个方法中所有的方法而不仅仅是public
     * @param joinPoint 这个参数可以获取当前执行方法的名字或者是当前执行方法的参数列表，以及其他的信息等
     */
    @Around("@annotation(com.jiefeng.ssm.annotation.LoggerOrException)")
    public Object AroundMethod(ProceedingJoinPoint joinPoint) throws IOException {
        Object obj = null;

        //获取用户名
        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        if(principal == null){
            System.out.println("aroundMethod: principal null ");
        }

        Admin admin = (Admin)principal.getObject();
        String username = admin.getName();

        //定义异常的返回结果
        Map<String,Object> failureResult = new HashMap<>();



        //获取参数类型
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        //获取request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request1 = sra.getRequest();

        //获取用户的登录IP
        String ip = request1.getRemoteAddr();

        //获取被切入的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        //方法执行开始
        long startTime = System.currentTimeMillis();

        //获取方法上的注解，从而得到方法的描述
        LoggerOrException loggerOrException = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(LoggerOrException.class);

        try {
            //执行方法
            obj = joinPoint.proceed(joinPoint.getArgs());

        } catch (Throwable e) {
            java.lang.Exception langE = (java.lang.Exception)e;

            //获取异常中的全部信息
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            langE.printStackTrace(printStream);
            String exceptionDetailInfo = new String(outputStream.toByteArray());

            //关闭资源
            outputStream.close();
            printStream.close();

            //封装异常信息
            Exception exception = new Exception();
            exception.setUsername(username);
            exception.setCreateTime(new Date());
            exception.setIp(ip);
            //获取当前方法的操作说明，意思就是这个方法是干什么的
            exception.setExceptionDesc(loggerOrException.operationName());
            exception.setMethodArgs(args.toString());
            exception.setMethod(methodName);
            exception.setExceptionDetail(exceptionDetailInfo);
            exceptionDao.addException(exception);

            //返回对应的异常到前台
            failureResult.put("success",false);
            failureResult.put("errMsg",exceptionDetailInfo);
            return failureResult;
        }

        // 方法执行结束时间
        long endTime = System.currentTimeMillis();

            Logger logger = new Logger();
            logger.setUsername(username);
            logger.setIp(ip);
            logger.setMethod(methodName);
            logger.setSpendTime((endTime - startTime));
            logger.setLoggerDesc(loggerOrException.operationName());
            logger.setCreateTime(new Date());
            logger.setMethodArgs(args.toString());
            loggerDao.addLogger(logger);

        return obj;
    }
}
