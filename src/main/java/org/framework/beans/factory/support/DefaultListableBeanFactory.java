package org.framework.beans.factory.support;

import org.framework.beans.factory.BeanFactory;
import org.framework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by PYL
 */
public class DefaultListableBeanFactory implements BeanFactory {

    /*用来存储bd*/
    public final Map<String , BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
