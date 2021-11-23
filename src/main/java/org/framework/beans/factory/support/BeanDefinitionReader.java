package org.framework.beans.factory.support;

import org.framework.beans.factory.config.BeanDefinition;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

/**
 * <p>用于扫描</p>
 * Created by PYL
 */
public class BeanDefinitionReader {
    /**
     * <p>存储扫描出来的bean的全类名</p>
     * */
    private List<String> registryBeanClasses = new ArrayList<>();

    public BeanDefinitionReader(String scanPackage) throws Exception{
        doScan(scanPackage);
    }

    /**
     * <p>存储扫描出来的bean的全类名</p>
     * */
    public void doScan(String scanPackage) throws Exception{
        URL url = this.getClass().getResource("/"+scanPackage.replaceAll("\\.","/"));
        if(url == null){
            throw new Exception("包"+scanPackage+"不存在");
        }
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()){
            if(file.isDirectory()){
                doScan(scanPackage+"."+file.getName());
            }else {
                if(!file.getName().endsWith(".class")){
                    continue;
                }
                String className = scanPackage+"."+file.getName().replace(".class","");
                registryBeanClasses.add(className);
            }
        }
    }

    /**
     * <p>将扫描到的类信息转化为bd对象</p>
     * */
    public List<BeanDefinition> loadBeanDefinitions(){
        List<BeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registryBeanClasses){
                Class<?> beanClass = Class.forName(className);
                if(beanClass.isInterface()){
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),beanClass.getName()));

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> anInterface : interfaces){
                    result.add(doCreateBeanDefinition(anInterface.getSimpleName(),anInterface.getName()));
                }
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <p>将扫描到的类信息转化为bd对象</p>
     * */
    public BeanDefinition doCreateBeanDefinition(String factoryBeanName,String beanClassName){
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     *  <p>将类名首字母小写</p>
     * */
    public String toLowerFirstCase(String simpleName){
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
