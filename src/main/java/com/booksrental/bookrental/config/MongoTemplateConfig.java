package com.booksrental.bookrental.config;

import com.booksrental.bookrental.converters.ZonedDateTimeReadConverter;
import com.booksrental.bookrental.converters.ZonedDateTimeWriteConverter;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoTemplateConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String databaseHost;

    @Override
    protected String getDatabaseName() {
        return "book_rental_test";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(databaseHost);
}

//    @Bean
//    public MongoTemplate getMongoTemplate() throws UnknownHostException {
//        MappingMongoConverter converter = new MappingMongoConverter(
//                new DefaultDbRefResolver(mongoDbFactory()), new MongoMappingContext());
//        converter.setCustomConversions(customConversions());
//        converter.afterPropertiesSet();
//        return new MongoTemplate(mongoDbFactory(), converter);
//    }

    public @Bean
    MongoDbFactory mongoDbFactory()  {
        return new SimpleMongoDbFactory(new MongoClient(), databaseName);
    }

//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }

//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), databaseName);
//    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }

}
