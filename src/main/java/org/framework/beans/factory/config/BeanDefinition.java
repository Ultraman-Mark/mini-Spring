package org.framework.beans.factory.config;

/**
 * Created by PYL
 */
public class BeanDefinition {
    /*bean对应的全类名*/
    private String beanClassName;

    /*是否懒加载*/
    private boolean lazyInit = false;

    /*保存在IOC容器时发key值*/
    private String factoryBeanName;

    public String getBeanClassName(){
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName){
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit(){
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit){
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
