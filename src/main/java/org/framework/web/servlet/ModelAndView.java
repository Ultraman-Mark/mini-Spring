package org.framework.web.servlet;

import com.sun.javafx.collections.MappingChange;

import java.util.Map;

/**
 *  <p>存储视图名称和数据</p>
 * Created by PYL
 */
public class ModelAndView {

    private String viewName;

    private Map<String,?> model;

    public ModelAndView(String viewName){
        this.viewName = viewName;
    }

    public ModelAndView(String viewName,Map<String,?> model){
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
