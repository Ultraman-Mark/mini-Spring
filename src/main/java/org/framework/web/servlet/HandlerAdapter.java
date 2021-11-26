package org.framework.web.servlet;

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

    public ModelAndView handle(){
        
    }
}
