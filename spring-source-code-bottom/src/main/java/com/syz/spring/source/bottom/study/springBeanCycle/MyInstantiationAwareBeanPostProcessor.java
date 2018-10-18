package com.syz.spring.source.bottom.study.springBeanCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * 3、InstantiationAwareBeanPostProcessor（实例化感知Bean后处理器） 接口本质是BeanPostProcessor的子接口，
 *  bean的实例化前后，即调用构造函数前后
 *  1、Bean构造出来之前调用postProcessBeforeInstantiation()方法
    2、Bean构造出来之后调用postProcessAfterInstantiation()方法
 通常不会直接实现InstantiationAwareBeanPostProcessor接口，而是会采用继承InstantiationAwareBeanPostProcessorAdapter这个抽象类的方式来使用。

 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器");
    }

    /**
     * 接口方法、实例化Bean之前调用
     */
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法-----作用执行bean构造器");
        return null;
    }

    /**
     * 接口方法、实例化Bean之后调用
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法-----作用为Bean注入属性");
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }
    /**
     *这个有3个方法，其中第二个方法postProcessAfterInitialization就是重写了BeanPostProcessor的方法。
     *第三个方法postProcessPropertyValues用来操作属性，返回值也应该是PropertyValues对象
     */

}
