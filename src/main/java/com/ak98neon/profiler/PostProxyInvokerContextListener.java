package com.ak98neon.profiler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class PostProxyInvokerContextListener {

    private ConfigurableListableBeanFactory beanFactory;

    public PostProxyInvokerContextListener(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(name -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String originalBeanClassName = beanDefinition.getBeanClassName();
            try {
                if (originalBeanClassName != null) {
                    Class<?> originalClass = Class.forName(originalBeanClassName);
                    Method[] methods = originalClass.getDeclaredMethods();
                    Arrays.stream(methods).forEach(method -> {
                        if (method.isAnnotationPresent(PostProxy.class)) {
                            method.setAccessible(true);
                            Object bean = context.getBean(name);
                            try {
                                if (Proxy.isProxyClass(bean.getClass())) {
                                    Proxy.getInvocationHandler(bean).invoke(bean, method, new Object[]{});
                                } else {
                                    Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                                    currentMethod.invoke(bean);
                                }
                            } catch (Throwable e) {
                                throw new ProfilerException(e.getMessage());
                            }
                        }
                    });
                }
            } catch (ClassNotFoundException e) {
                throw new ProfilerException(e.getMessage());
            }
        });
    }
}
