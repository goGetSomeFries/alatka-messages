package com.alatka.messages;

import com.alatka.messages.core.definition.*;
import com.alatka.messages.definition.SpringMessageDefinitionBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@EnableConfigurationProperties(AlatkaMessagesProperties.class)
@ConditionalOnProperty(value = "alatka.messages.enabled", havingValue = "true", matchIfMissing = true)
public class AlatkaMessagesAutoConfiguration {

    public static final String BEAN_NAME = "messageDefinitionBuilder";

    @Bean(BEAN_NAME)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "alatka.messages.type", havingValue = "yaml", matchIfMissing = true)
    public MessageDefinitionBuilder yamlMessageDefinitionBuilder(AlatkaMessagesProperties properties) {
        SpringMessageDefinitionBuilder builder = new SpringMessageDefinitionBuilder();
        String classpath = Optional.ofNullable(properties.getClasspath()).orElse("");
        switch (properties.getMessageType()) {
            case iso:
                builder.addBuilder(new IsoYamlMessageDefinitionBuilder(classpath));
                break;
            case fixed:
                builder.addBuilder(new FixedYamlMessageDefinitionBuilder(classpath));
                break;
            case all:
            default:
                builder.addBuilder(new IsoYamlMessageDefinitionBuilder(classpath));
                builder.addBuilder(new FixedYamlMessageDefinitionBuilder(classpath));
        }
        return builder;
    }

    @Bean(BEAN_NAME)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "alatka.messages.type", havingValue = "xml")
    public MessageDefinitionBuilder xmlMessageDefinitionBuilder(AlatkaMessagesProperties properties) {
        SpringMessageDefinitionBuilder builder = new SpringMessageDefinitionBuilder();
        String classpath = Optional.ofNullable(properties.getClasspath()).orElse("");
        switch (properties.getMessageType()) {
            case iso:
                builder.addBuilder(new IsoXmlMessageDefinitionBuilder(classpath));
                break;
            case fixed:
                builder.addBuilder(new FixedXmlMessageDefinitionBuilder(classpath));
                break;
            case all:
            default:
                builder.addBuilder(new IsoXmlMessageDefinitionBuilder(classpath));
                builder.addBuilder(new FixedXmlMessageDefinitionBuilder(classpath));
        }
        return builder;
    }

    @Bean(BEAN_NAME)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "alatka.messages.type", havingValue = "annotation")
    public MessageDefinitionBuilder annotationMessageDefinitionBuilder(AlatkaMessagesProperties properties) {
        SpringMessageDefinitionBuilder builder = new SpringMessageDefinitionBuilder();
        String packageName = Optional.ofNullable(properties.getPackageName())
                .orElseThrow(() -> new IllegalArgumentException("packageName must not be null"));
        switch (properties.getMessageType()) {
            case iso:
                builder.addBuilder(new IsoAnnotationMessageDefinitionBuilder(packageName));
                break;
            case fixed:
                builder.addBuilder(new FixedAnnotationMessageDefinitionBuilder(packageName));
                break;
            case all:
            default:
                builder.addBuilder(new IsoAnnotationMessageDefinitionBuilder(packageName));
                builder.addBuilder(new FixedAnnotationMessageDefinitionBuilder(packageName));
        }
        return builder;
    }

    @Bean(BEAN_NAME)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "alatka.messages.type", havingValue = "database")
    public MessageDefinitionBuilder databaseMessageDefinitionBuilder(AlatkaMessagesProperties properties, DataSource dataSource) {
        SpringMessageDefinitionBuilder builder = new SpringMessageDefinitionBuilder();
        switch (properties.getMessageType()) {
            case iso:
                builder.addBuilder(new IsoDatabaseMessageDefinitionBuilder(dataSource));
            case fixed:
                builder.addBuilder(new FixedDatabaseMessageDefinitionBuilder(dataSource));
            case all:
            default:
                builder.addBuilder(new IsoDatabaseMessageDefinitionBuilder(dataSource));
                builder.addBuilder(new FixedDatabaseMessageDefinitionBuilder(dataSource));
        }
        return builder;
    }
}
