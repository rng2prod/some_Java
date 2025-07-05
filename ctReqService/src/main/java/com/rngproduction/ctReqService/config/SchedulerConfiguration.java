package com.rngproduction.ctReqService.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "taskScheduler.enable", matchIfMissing = true)
public class SchedulerConfiguration {

}
