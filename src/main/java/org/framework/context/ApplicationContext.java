package org.framework.context;

import org.framework.beans.factory.BeanFactory;

/**
 * <p>容器抽象类</p>
 * Created by PYL
 */
public interface ApplicationContext extends BeanFactory {
    void refresh() throws Exception;
}
