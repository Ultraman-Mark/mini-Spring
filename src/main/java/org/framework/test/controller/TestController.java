package org.framework.test.controller;

import org.framework.annotation.Controller;
import org.framework.annotation.RequestMapping;
import org.framework.annotation.RequestParam;
import org.framework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PYL
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public ModelAndView test(@RequestParam("author") String author){
        Map<String,Object> model = new HashMap<>();
        model.put("author",author);
        return new ModelAndView("test",model);
    }

    @RequestMapping("/exception")
    public void exception(){
        int i = 1/0;
    }
}
