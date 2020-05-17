# Spring Profiler Starter


[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c61114ef947e4a51be90efcf548a13cd)](https://app.codacy.com/manual/ak98neon/spring-boot-profiler-starter?utm_source=github.com&utm_medium=referral&utm_content=ak98neon/spring-boot-profiler-starter&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/ak98neon/spring-boot-profiler-starter.svg?branch=master)](https://travis-ci.org/github/ak98neon/spring-boot-profiler-starter)

## Annotations
```text
@EnableProfiling
@Profiling
@PostProxy
```

### How to enable profiler
To enable profiling you need to add @EnableProfiling annotation 
to your main class
```java
@SpringBootApplication
@EnableProfiling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

### How to profile your class or method
You can add @Profiling annotation to your class bean, or you can add it to
method, also you can use @PostProxy annotation for run your test method without any
execution actions, method that annotated @PostProxy will be execute after context initialize
```java
@Component
@Profiling
public class DemoBean {

    @PostProxy
    public void testMethod() {
        System.out.println("lalalal");
    }

    @Profiling
    public void sayQuote() {
        System.out.println("lalalal");
    }
}
```
