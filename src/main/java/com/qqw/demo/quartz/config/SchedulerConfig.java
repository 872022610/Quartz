package com.qqw.demo.quartz.config;

import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * demo for quartz
 *
 * @author quanqiwei
 * @date 2019年8月4日 16:02:04
 */
@Configuration
public class SchedulerConfig {

        @Bean
        public JobFactory jobFactory(ApplicationContext applicationContext) {
            AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
            jobFactory.setApplicationContext(applicationContext);
            return jobFactory;
        }

        //SchedulerFactoryBean
        @Bean
        public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory)
                throws IOException {
            SchedulerFactoryBean factory = new SchedulerFactoryBean();
            factory.setJobFactory(jobFactory);
            return factory;
        }
}
