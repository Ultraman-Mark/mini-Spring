package org.framework.test.service;

import org.framework.annotation.Autowired;
import org.framework.annotation.Service;
import org.framework.test.controller.TestController;
import org.framework.test.dao.TestDAO;

/**
 * Created by PYL
 */
@Service
public class TestService {
    @Autowired
    TestController controller;

    public void echo(){
         System.out.println(controller);
    }
}
