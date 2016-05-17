package com.lewis.cms.common.base;


import com.lewis.cms.common.annotation.TspServiceInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangminghua on 2016/5/17.
 */
@Component
public class TspServiceComponent {

    private List<RequestParam> requestList = new LinkedList<RequestParam>();

    @PostConstruct
    public void init() throws InterruptedException, UnknownHostException {
        ApplicationContext ctx = null;
        while (ctx == null) {
            ctx = AppContext.getApplicationContext();
            Thread.sleep(10);
        }
        Map<String, Object> beansWithAnnotationMap = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansWithAnnotationMap.values();
        loadServiceConfigIntoRequestParamList(requestList, values);
        System.out.println(requestList);
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress()+":8081";
        System.out.println(hostAddress);
        if (CollectionUtils.isNotEmpty(requestList)) {
            for (RequestParam requestParam : requestList) {
                requestParam.setRequestUrl(hostAddress+requestParam.getRequestUrl());
            }
        }
        System.out.println(requestList);
    }

    private void loadServiceConfigIntoRequestParamList(List<RequestParam> requestList, Collection<Object> values) {
        if (CollectionUtils.isNotEmpty(values)) {


            for (Object controllerInstance : values) {
                Class<?> clazz = controllerInstance.getClass();
                StringBuilder sb = new StringBuilder();
                RequestMapping classRequestMapping = clazz.getAnnotation(RequestMapping.class);
                String classMapping = null;
                if (classRequestMapping != null) {
                    if (classRequestMapping.value() != null && classRequestMapping.value().length > 0) {
                        classMapping = classRequestMapping.value()[0];
                        sb.append(classMapping);
                    }
                }
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        RequestParam requestParam = new RequestParam();
                        if (methodRequestMapping != null) {
                            if (methodRequestMapping.value() != null && methodRequestMapping.value().length > 0) {
                                sb.append(methodRequestMapping.value()[0]);
                            }
                        }
                        System.out.println(sb.toString());
                        if (methodRequestMapping.method() != null) {
                            String methodString = getMethodString(methodRequestMapping.method());
                            requestParam.setMethod(methodString);
                        }
                        requestParam.setRequestUrl(sb.toString());
                        requestParam.setReturnValue(method.getReturnType().getName());
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        String paramterTypeName = null;
                        if (parameterTypes != null && parameterTypes.length > 0) {
                            for (Class paramterType : parameterTypes) {
                                if (paramterTypeName == null) {
                                    paramterTypeName = paramterType.getName() + "#";
                                } else {
                                    paramterTypeName += paramterType.getName() + "#";
                                }
                            }
                        }
                        if (StringUtils.isNotEmpty(paramterTypeName)) {
                            paramterTypeName = paramterTypeName.substring(0, paramterTypeName.length() - 1);
                            requestParam.setParam(paramterTypeName);
                        }
                        sb = new StringBuilder();
                        if (StringUtils.isNotEmpty(classMapping)) {
                            sb.append(classMapping);
                        }
                        TspServiceInfo tspAnnotation = method.getAnnotation(TspServiceInfo.class);
                        if (tspAnnotation != null) {
                            requestParam.setName(tspAnnotation.name());
                            requestParam.setDesc(tspAnnotation.description());
                        }
                        requestList.add(requestParam);
                    }
                }

            }
        }
    }

    private String getMethodString(RequestMethod[] methods) {
        String methodString = RestMethod.GET;
        if (methods != null && methods.length > 1) {
            RequestMethod method = methods[0];
            switch (method) {
                case POST:
                    methodString = RestMethod.POST;
                    break;
                case PUT:
                    methodString = RestMethod.PUT;
                    break;
                case DELETE:
                    methodString = RestMethod.DELETE;
                    break;
                default:
                    methodString = RestMethod.GET;
                    break;
            }
        }
        return methodString;
    }


}
