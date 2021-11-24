package org.framework.context.annotation;

import org.framework.beans.factory.support.BeanDefinitionReader;
import org.framework.context.support.AbstractApplicationContext;

/**
 *  <p>基于注解作为配置的容器</p>
 * Created by PYL
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext {

    public AnnotationConfigApplicationContext(Class annotatedClass) throws Exception{
        super.reader = new BeanDefinitionReader(getScanPackage(annotatedClass));
        refresh();
    }

    @Override
    public void refresh() throws Exception{
        super.refresh();
    }

    /**
     * <p>获取@ComponentScan中的value值</p>
     */
    public String getScanPackage(Class annotatedClass) throws Exception{
        if(!annotatedClass.isAnnotationPresent(ComponentScan.class)){
            throw new Exception("请为注解配置类加上@ComponentScan注解！");
        }
        ComponentScan scan = (ComponentScan) annotatedClass.getAnnotation(ComponentScan.class);
        return scan.value().trim();
    }
}
