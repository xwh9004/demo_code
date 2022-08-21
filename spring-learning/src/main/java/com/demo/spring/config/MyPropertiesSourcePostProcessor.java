package com.demo.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class MyPropertiesSourcePostProcessor implements EnvironmentPostProcessor {

    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
    private YamlPropertySourceLoader ymlLoader = new YamlPropertySourceLoader();


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Resource customResource = new ClassPathResource("custom.yml");
        Resource customResource1 = new ClassPathResource("custom.properties");
        try {
            PropertySource ymlSources = ymlLoader.load("customYml", customResource).get(0);
            PropertySource propertySource = loader.load("customProperties", customResource1).get(0);
            propertySources.addLast(ymlSources);
            propertySources.addFirst(propertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
