package com.playonlinux.multithreading;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultithreadingConfiguration {
    @Bean
    public ControlledThreadPoolExecutorService scriptExecutorService() {
        return new ControlledThreadPoolExecutorService("Scripts", 10, 50);
    }

    @Bean
    public ControlledThreadPoolExecutorService appsExecutorService() {
        return new ControlledThreadPoolExecutorService("Apps", 1, 1);
    }

    @Bean
    public ControlledThreadPoolExecutorService containersExecutorService() {
        return new ControlledThreadPoolExecutorService("Containers", 1, 5);
    }

    @Bean
    public ControlledThreadPoolExecutorServiceCloser controllerThreadPoolExecutorServiceCloser() {
        return new ControlledThreadPoolExecutorServiceCloser(appsExecutorService(), containersExecutorService(), scriptExecutorService());
    }
}
