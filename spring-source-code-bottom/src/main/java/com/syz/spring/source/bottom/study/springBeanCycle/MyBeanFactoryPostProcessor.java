package com.syz.spring.source.bottom.study.springBeanCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 4、演示工厂后处理器接口方法  实例化Bean 后处理器实现类
 * Bean创建之前，读取Bean的元属性，并根据自己的需求对元属性进行改变，比如将Bean的scope从singleton改变为prototype)
 *
 * beanFactoryPostprocessor的作用是在beanFactory初始化之后提供一个修改的机会。spring已经提供了不少实现，、
 * 我们自己也可以写一些实现配置在xml中 或者手动调用。
 *
 * BeanFactoryPostProcessor允许使用者修改容器中的bean definitions
   BeanFactoryPostProcessor可以与bean definitions打交道，但是千万不要进行bean实例化（感觉这里应该说的是不要在BeanFactoryPostProcessor进行可能触发bean实例化的操作）。
   这么做可能会导致bean被提前实例化，会破坏容器造成预估不到的副作用。如果你需要hack到bean实例化过程，请考虑使用BeanPostProcessor
   BeanFactoryPostProcessor的主要作用是让你能接触到bean definitions，对bean definitions进行一定hack，但是也仅此而已了。绝对不允许在BeanFactoryPostProcessor中触发到bean的实例化
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    /**
     *修改容器中的bean definitions信息，他是用来存储bean定义的信息。后面对bean操作就是直接对definitions进行
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("person");
        bd.getPropertyValues().addPropertyValue("phone", "110");

    }
}
