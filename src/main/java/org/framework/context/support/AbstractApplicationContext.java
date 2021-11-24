package org.framework.context.support;

import org.framework.beans.BeanWapper;
import org.framework.beans.factory.config.BeanDefinition;
import org.framework.beans.factory.support.BeanDefinitionReader;
import org.framework.beans.factory.support.DefaultListableBeanFactory;
import org.framework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>容器抽象类</p>
 * Created by PYL
 */
public abstract class AbstractApplicationContext extends DefaultListableBeanFactory implements ApplicationContext {

    protected BeanDefinitionReader reader;

    /**
     * <p>保存单例对象</p>
     * */
    private Map<String,Object> factoryBeanObjectCache = new HashMap<>();

    /**
     * <p>保存包装对象</p>
     * */
    private Map<String, BeanWapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    @Override
    public void refresh() throws Exception {
        // 扫描需要扫描的包，并把相关的类转化为beanDefinition
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        // 注册，将beanDefinition放入IOC容器存储
        doRegisterBeanfinition(beanDefinitions);
        //将非懒加载的类初始化
        doAutowired();
    }

    /**
     * <p>将beanDefinition放入IOC容器存储</p>
     */
    private void doRegisterBeanfinition(List<BeanDefinition> beanDefinitions) throws Exception{
        for (BeanDefinition beanDefinition : beanDefinitions){
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception(beanDefinition.getFactoryBeanName()+"已经存在！");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    /**
     * <p>将beanDefinition放入IOC容器存储</p>
     */
    private void doAutowired(){
        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }
    }

    @Override
    public Object getBean(String beanName){
        return null;
    }
}
