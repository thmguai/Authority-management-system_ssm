package com.it.ssm.controller;

import com.it.ssm.domain.Syslog;
import com.it.ssm.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService syslogService;

    private Date visitTime;//访问时间
    private Class executionClass;//访问类
    private Method executionMethod;//访问方法

    @Before("execution(* com.it.ssm.controller.*.*(..)) && !execution(* com.it.ssm.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {


        //获取访问时间
        visitTime = new Date();
        executionClass= jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0){
            //方法无参数
            executionMethod = executionClass.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* com.it.ssm.controller.*.*(..)) && !execution(* com.it.ssm.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp){

        Syslog syslog = new Syslog();

        //获取url
        //获取class上的RequestMapping注解对象
        if (executionClass != LogAop.class){
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                //获取method上的RequestMapping注解对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                    syslog.setUrl(url);
                }
            }
        }

        //获取方法名
        String method = "[类名 ]"+executionClass.getName()+"[方法名 ]"+ executionMethod.getName();

        //访问时长
        Long executionTime = new Date().getTime() - visitTime.getTime();

        //获取ip
        String ip = request.getRemoteAddr();
        //获取当前操作者用户名 也可以用request获取
        //User user = (User) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        syslog.setIp(ip);
        syslog.setExecutionTime(executionTime);
        syslog.setMethod(method);
        syslog.setUsername(username);
        syslog.setVisitTime(visitTime);

        syslogService.save(syslog);
    }
}
