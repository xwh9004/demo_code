package com.demo.elasticsearch;

import com.demo.elasticsearch.bean.Movies;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest(classes = ElasticSearchApplication.class)
public class CriteriaQueryTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;



    @Test
    public void findByField() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        Criteria criteria = new Criteria("title").is("Toy");
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Movies> hints = elasticsearchOperations.search(query, Movies.class, index);
        System.out.println(hints.stream().count());
        hints.forEach(System.out::println);

    }


}
