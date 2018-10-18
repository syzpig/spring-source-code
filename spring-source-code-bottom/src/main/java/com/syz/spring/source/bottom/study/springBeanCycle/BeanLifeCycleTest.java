package com.syz.spring.source.bottom.study.springBeanCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 5、配置文件如下beans.xml，很简单，使用ApplicationContext,处理器不用手动注册：
 * <p>
 * 6、下面测试一下：
 */
public class BeanLifeCycleTest {

    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
        /**
         *关闭容器使用的是实际是AbstractApplicationContext的钩子方法。
         */
    }

}
