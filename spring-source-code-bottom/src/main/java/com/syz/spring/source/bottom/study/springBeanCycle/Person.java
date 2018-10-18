package com.syz.spring.source.bottom.study.springBeanCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
//我们用一个简单的Spring Bean来演示一下Spring Bean的生命周期。

//1、首先是一个简单的Spring Bean，调用Bean自身的方法和Bean级生命周期接口方法，
// 为了方便演示，它实现了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这4个接口，
// 同时有2个方法，对应配置文件中<bean>的init-method和destroy-method

/**
 * 让Bean对工厂有知觉
 * 作用：让Bean获取配置他们的BeanFactory的引用。
 * 实现BeanFactoryAware接口，其中只有一个方法即setFactory(BeanFactory factory)。
 * 使用上与BeanNameAware接口无异，只不过BeanFactoryAware注入的是个工厂，BeanNameAware注入的是个Bean的名字。
 * <p>
 * <p>
 * BeanNameAware
 * 作用：让Bean获取自己在BeanFactory配置中的名字（根据情况是id或者name）。
 * Spring自动调用。并且会在Spring自身完成Bean配置之后，且在调用任何Bean生命周期回调（初始化或者销毁）方法之前就调用这个方法。
 * 换言之，在程序中使用BeanFactory.getBean(String beanName)之前，Bean的名字就已经设定好了。
 * BeanFactoryAware
 * 作用：让Bean获取配置他们的BeanFactory的引用。
 * <p>
 * 这个方法可能是在根据某个配置文件创建了一个新工厂之后，Spring才调用这个方法，并把BeanFactory注入到Bean中。
 * 让bean获取配置自己的工厂之后，当然可以在Bean中使用这个工厂的getBean()方法，但是，实际上非常不推荐这样做，因为结果是进一步加大
 * Bean与Spring的耦合，而且，能通过DI注入进来的尽量通过DI来注入。
 * 当然，除了查找bean，BeanFactory可以提供大量其他的功能，例如销毁singleton模式的Bean。
 * factory.preInstantiateSingletons();方法。preInstantiateSingletons()方法立即实例化所有的Bean实例，有必要对这个方法和Spring加载bean的机制做个简单说明。
 * 方法本身的目的是让Spring立即处理工厂中所有Bean的定义，并且将这些Bean全部实例化。因为Spring默认实例化Bean的情况下，采用的是lazy机制，
 * 换言之，如果不通过getBean()方法（BeanFactory或者ApplicationContext的方法）获取Bean的话，那么为了节省内存将不实例话Bean，只有在Bean被调用的时候才实例化他们。
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println("【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("【注入属性】注入属性name");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", name=" + name + ", phone="
                + phone + "]";
    }


    /**
     * 这是BeanFactoryAware接口方法
     * 要直接在自己的代码中读取spring的bean,我们除了根据常用的set外,也可以通过spring的BeanFactoryAware接口实现,只要实现setBeanFactory方法就可以,
     * 使用ClassPathXmlApplicationContext 加载bean.xml文件才能将实现BeanFactoryAware接口的bean的BeanFactory注入进去。
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    /**
     * 这是BeanNameAware接口方法
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = s;
    }

    /**
     * 这是InitializingBean接口方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    /**
     * 这是DiposibleBean接口方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

}
