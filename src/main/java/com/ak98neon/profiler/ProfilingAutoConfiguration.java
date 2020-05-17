package com.ak98neon.profiler;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ProfilingAutoConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        final String bean = "com.ak98neon.context.ProfilingHandlerBeanPostProcessor";
        final String beanPostProxy = "com.ak98neon.context.PostProxyInvokerContextListener";

        if (!registry.containsBeanDefinition(
                bean)) {

            registry.registerBeanDefinition(bean,
                    new RootBeanDefinition(ProfilingHandlerBeanPostProcessor.class));
        }

        if (!registry.containsBeanDefinition(
                beanPostProxy)) {

            registry.registerBeanDefinition(beanPostProxy,
                    new RootBeanDefinition(PostProxyInvokerContextListener.class));
        }
    }
}
