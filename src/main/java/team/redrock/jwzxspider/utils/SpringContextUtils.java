package team.redrock.jwzxspider.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.stereotype.Component;
//
//
//@Component
//public class SpringContextUtils implements ApplicationContextAware {//Spring的工具类，用来获得配置文件中的bean
//
//    private static ApplicationContext applicationContext = null;
//
//    /***
//     * 当继承了ApplicationContextAware类之后，那么程序在调用
//     * getBean(String)的时候会自动调用该方法，不用自己操作
//     */
//    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
//                SpringContextUtils.applicationContext = applicationContext;
//            }
//
//
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//    /***
//     * 根据一个bean的id获取配置文件中相应的bean
//     * @param name
//     * @return
//     * @throws BeansException
//     */
//    public static Object getBean(String name) throws BeansException {
//        return applicationContext.getBean(name);
//    }
//    /***
//     * 类似于getBean(String name)只是在参数中提供了需要返回到的类型。
//     * @param name
//     * @param requiredType
//     * @return
//     * @throws BeansException
//     */
//    public static Object getBean(String name, Class requiredType) throws BeansException {
//        return applicationContext.getBean(name, requiredType);
//    }
//
//    /**
//     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
//     * @param name
//     * @return boolean
//     */
//    public static boolean containsBean(String name) {
//        return applicationContext.containsBean(name);
//    }
//
//    /**
//     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
//     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
//     * @param name
//     * @return boolean
//     * @throws NoSuchBeanDefinitionException
//     */
//    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
//        return applicationContext.isSingleton(name);
//    }
//
//    /**
//     * @param name
//     * @return Class 注册对象的类型
//     * @throws NoSuchBeanDefinitionException
//     */
//    public static Class getType(String name) throws NoSuchBeanDefinitionException {
//        return applicationContext.getType(name);
//    }
//
//    /**
//     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
//     * @param name
//     * @return
//     * @throws NoSuchBeanDefinitionException
//     */
//    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
//        return applicationContext.getAliases(name);
//    }
//}



/**
 * Springboot 获取上下文<br/>
 * Author:杨杰超<br/>
 * Date:2017年5月20日 下午20:30:27 <br/>
 * Copyright (c) 2017, yangjiechao@dingtalk.com All Rights Reserved.<br/>
 *
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
