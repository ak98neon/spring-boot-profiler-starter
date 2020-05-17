package com.ak98neon.profiler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan
@ImportAutoConfiguration
public class ProfilingAutoConfigurationTest {
    @Autowired
    private DemoBean demoBean;

    @Test
    public void checkInitContext() {
        demoBean.sayHi();
    }

    @Configuration
    static class CustomConfiguration {
        @Bean
        DemoBean demoBean() {
            return new DemoBean();
        }
    }

    @EnableProfiling
    public static class DemoBean {
        public void sayHi() {
            System.out.println("Hi");
        }
    }
}
