package org.framework.test;

import org.framework.context.ApplicationContext;
import org.framework.context.annotation.AnnotationConfigApplicationContext;
import org.framework.test.config.ApplicationConfig;
import org.framework.test.service.TestService;


/**
 * Created by PYL
 */
public class ApplicationTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        TestService service = (TestService) applicationContext.getBean("testService");

        service.echo();
    }
}
