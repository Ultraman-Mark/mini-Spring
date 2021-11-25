package org.framework.context.support;

import org.framework.annotation.*;
import org.framework.beans.BeanWrapper;
import org.framework.beans.factory.config.BeanDefinition;
import org.framework.beans.factory.support.BeanDefinitionReader;
import org.framework.beans.factory.support.DefaultListableBeanFactory;
import org.framework.context.ApplicationContext;

import java.lang.reflect.Field;
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
    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

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
        BeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        try {
            // 通过bd实例化bean
            Object instance = instantiateBean(beanDefinition);
            if(instance==null){
                return null;
            }
            //将实例化的bean使用bw包装
            BeanWrapper beanWapper = new BeanWrapper(instance);

            this.factoryBeanInstanceCache.put(beanDefinition.getBeanClassName(),beanWapper);

            populateBean(instance);

            return instance;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>通过bd，实例化bean</p>
     */
    private Object instantiateBean(BeanDefinition beanDefinition){
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            if(this.factoryBeanObjectCache.containsKey(className)){
                instance = this.factoryBeanObjectCache.get(className);
            }
            else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(),instance);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    public void populateBean(Object instance){
        Class clazz = instance.getClass();

        if(!(clazz.isAnnotationPresent(Component.class)||
                clazz.isAnnotationPresent(Controller.class)||
                clazz.isAnnotationPresent(Service.class)||
                clazz.isAnnotationPresent(Repository.class))){
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            if (!field.isAnnotationPresent(Autowired.class)){
                continue;
            }
            String autowiredBeanName = field.getType().getName();

            field.setAccessible(true);

            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            }
            catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
