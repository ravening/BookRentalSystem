package com.booksrental.bookrental.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Configuration
public class ActuatorConfig {
//    @Autowired
//    private DataSource dataSource;
//
    @Autowired
    private MeterRegistry meterRegistry;
//
//    @Bean
//    DataSourceStatusProbe dataSourceStatusProbe(DataSource dataSource) {
//        return new DataSourceStatusProbe(dataSource);
//    }

//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> metricsCommonTag() {
//        return registry -> registry.config().commonTags("app.name", "books-rental-app");
//    }

    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
