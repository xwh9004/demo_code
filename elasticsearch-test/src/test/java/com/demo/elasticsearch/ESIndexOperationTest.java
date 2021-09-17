package com.demo.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.Map;

@SpringBootTest(classes = ElasticSearchApplication.class)
public class ESIndexOperationTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Test
    public void indexExists() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        System.out.println(elasticsearchOperations.indexOps(index).exists());
    }

    @Test
    public void mappings() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        Map<String, Object> mappings = elasticsearchOperations.indexOps(index).getMapping();
        System.out.println(mappings);
    }

    @Test
    public void settings() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        Map<String, Object> settings = elasticsearchOperations.indexOps(index).getSettings();
        System.out.println(settings);
    }



}
