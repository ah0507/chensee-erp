package net.chensee.platform.erp.log.aspect;

import lombok.extern.slf4j.Slf4j;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.common.HttpClientHelper;
import net.chensee.platform.erp.log.service.SysLogService;
import net.chensee.platform.erp.log.vo.SysLogVo;
import net.chensee.platform.erp.utils.RandomUUIDUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ah
 * @title: 切面之日志管理
 * @date 2019/11/29 9:28
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Value("${isLogFlag}")
    private boolean isLogFlag;

    @Pointcut("execution(* net.chensee.*.action.*.controller.*.*(..))")
    public void oprLog() {}

    @Autowired
    private SysLogService sysLogService;

    /**
     * 前置方法拦截
     */
    @Before("oprLog()")
    public void doBeforeMethod(JoinPoint joinPoint) {
        if (!isLogFlag) {
            return;
        }
        HttpServletRequest request = this.getRequest();
        String reqUrl = request.getRequestURL().toString();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Object[] args = joinPoint.getArgs();
        SysLogVo sysLogVo = new SysLogVo();
        Map paramMap = this.handleParamMap(parameterMap,args);
        if (!paramMap.isEmpty()) {
            sysLogVo.setArgs(JSONArray.fromObject(paramMap).toString());
        }
        sysLogVo.setId(RandomUUIDUtil.randomUUID());
        sysLogVo.setReqUrl(reqUrl);
        sysLogVo.setPackageName(declaringTypeName);
        sysLogVo.setMethodName(methodName);
        sysLogVo.setReqTime(System.currentTimeMillis());
        sysLogVo.setOprBy(UserUtil.getCurrentUser().getId());
        sysLogVo.setOprName(UserUtil.getCurrentUser().getUsername());
        request.setAttribute("sysLogVo", sysLogVo);
    }

    /**
     * 后置返回通知
     */
    @AfterReturning(pointcut = "oprLog()", returning = "resp")
    public void doAfterReturning(Object resp) {
        if (!isLogFlag) {
            return;
        }
        HttpServletRequest request = this.getRequest();
        SysLogVo sysLogVo = (SysLogVo) request.getAttribute("sysLogVo");
        if (sysLogVo != null) {
            sysLogVo.setReturnVal(JSONObject.fromObject(resp).toString());
            sysLogVo.setReturnTime(System.currentTimeMillis());
            sysLogService.insertLogManually(sysLogVo);
        }
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(pointcut = "oprLog()", throwing = "exp")
    public void doAfterThrowing(Throwable exp) {
        if (!isLogFlag) {
            return;
        }
        HttpServletRequest request = this.getRequest();
        SysLogVo sysLogVo = (SysLogVo) request.getAttribute("sysLogVo");
        if (sysLogVo != null) {
            sysLogVo.setExpContent(exp.toString());
            sysLogVo.setExpTime(System.currentTimeMillis());
            sysLogService.insertLogManually(sysLogVo);
        }
    }

    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes)requestAttributes).getRequest();
    }


    /**
     * 处理特殊的参数是上传文件时，获取文件的名称，文件大小，文件类型
     * @param parameterMap
     * @param args
     * @return
     */
    private Map handleParamMap(Map<String, String[]> parameterMap, Object[] args) {
        Map paramMap = new HashMap();
        for (String key : parameterMap.keySet()) {
            paramMap.put(key, parameterMap.get(key));
        }
        Map map;
        for (Object obj : args) {
            map = new HashMap();
            if (obj instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) obj;
                map.put("originalName", file.getOriginalFilename());
                map.put("contentType", file.getContentType());
                map.put("size", file.getSize());
                paramMap.put(file.getName(), map);
            }
        }
        return paramMap;
    }

}
