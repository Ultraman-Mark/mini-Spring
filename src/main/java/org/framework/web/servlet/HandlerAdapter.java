package org.framework.web.servlet;

import com.sun.net.httpserver.HttpServer;
import org.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>完成参数列表与Method实参的对应关系</p>
 * Created by PYL
 */
public class HandlerAdapter {

    /**
     * <p>判断handler是否属于HandlerMapping</p>
     * */
    public boolean supports(Object handler){
        return handler instanceof HandlerMapping;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws InvocationTargetException,IllegalAccessException{
        HandlerMapping handlerMapping = (HandlerMapping) handle;

        //方法的形参列表
        Map<String,Integer> paramMapping = new HashMap<>();

        //处理被@RequestParam标记的参数
        Annotation[][] paramAnnotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i=0;i<paramAnnotations.length;i++){
            for (Annotation paramAnnotation : paramAnnotations[i]){
                if (paramAnnotation instanceof RequestParam){
                    String paramName = ((RequestParam) paramAnnotation).value();
                    if(!"".equals(paramName.trim())){
                        paramMapping.put(paramName,i);
                    }
                }
            }
        }

        //处理HttpServletRequest和HttpServletResponse参数
        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i=0;i<paramTypes.length;i++){
            Class<?> type = paramTypes[i];
            if(type==HttpServletRequest.class||type==HttpServletResponse.class){
                paramMapping.put(type.getName(),i);
            }
        }

        //用户通过URL传递到的参数列表
        Map<String,String[]> requestParamMap = request.getParameterMap();

        //实参列表
        Object[] paramValues = new Object[paramTypes.length];

        //设置@RequestParam标记的参数
        for (Map.Entry<String,String[]> param : requestParamMap.entrySet()){
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","");

            if (!paramMapping.containsKey(param.getKey())){
                continue;
            }
            int index = paramMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value,paramTypes[index]);
        }

        //设置request参数
        if (paramMapping.containsKey(HttpServletRequest.class.getName())){
            paramValues[paramMapping.get(HttpServletRequest.class.getName())] = request;
        }

        //设置response参数
        if (paramMapping.containsKey(HttpServletResponse.class.getName())){
            paramValues[paramMapping.get(HttpServletResponse.class.getName())] = response;
        }

        //执行目标方法
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController());

        if(handlerMapping.getMethod().getReturnType() == ModelAndView.class){
            return (ModelAndView) result;
        }

        return null;
    }

    private Object caseStringValue(String value,Class<?> clazz){
        if(clazz==String.class){
            return value;
        }
        if(clazz==Integer.class||clazz==int.class){
            return Integer.valueOf(value);
        }
        return null;
    }
}
