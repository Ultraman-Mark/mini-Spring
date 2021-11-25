package org.framework.beans;

/**
 * <p>bean的包装类</p>
 * Created by PYL
 */
public class BeanWrapper {

    /**
     * <p>返回由该类封装的bean实例</p>
     * */
    private Object wrappedInstance;

    public BeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    }

    /**
     *  <p>返回包装的bean类型</>
     * */
    private Class<?> wrappedClass;

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public void setWrappedInstance(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }

    public void setWrappedClass(Class<?> wrappedClass) {
        this.wrappedClass = wrappedClass;
    }
}
