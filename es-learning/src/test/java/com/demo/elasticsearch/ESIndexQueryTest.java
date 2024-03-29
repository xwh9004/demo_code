package com.demo.elasticsearch;

import com.demo.elasticsearch.bean.Movies;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.MultiGetItem;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ElasticSearchApplication.class)
public class ESIndexQueryTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void findById() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        Movies movie = elasticsearchOperations.get("wJCtlHsB1chFk4J-A8hm", Movies.class, index);
        System.out.println(movie);
    }

    @Test
    public void findByIds() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        NativeSearchQueryBuilder nativeSearchQuery = new NativeSearchQueryBuilder();
        Collection<String> ids = new ArrayList<>();
        ids.add("wZCtlHsB1chFk4J-A8hm");
        ids.add("wpCtlHsB1chFk4J-A8hm");
        nativeSearchQuery.withIds(ids);
        List<MultiGetItem<Movies>> movieList = elasticsearchOperations.multiGet(nativeSearchQuery.build(), Movies.class, index);
        System.out.println(movieList.size());
    }

    @Test
    public void findByFieldValues() {

        IndexCoordinates index = IndexCoordinates.of("movies");
        NativeSearchQueryBuilder nativeSearchQuery = new NativeSearchQueryBuilder();
        QueryBuilder queryBuilder = QueryBuilders.termsQuery("movieId", "1", "2", "3");
        nativeSearchQuery.withFilter(queryBuilder);
        SearchHits<Movies> movieList = elasticsearchOperations.search(nativeSearchQuery.build(), Movies.class, index);
        System.out.println(movieList.stream().count());
    }

    @Test
    public void findByField() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        Criteria criteria = new Criteria("title").is("Toy");
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Movies> hints = elasticsearchOperations.search(query, Movies.class, index);
        System.out.println(hints.stream().count());
        hints.forEach(System.out::println);

    }

    @Test
    public void matchPhraseWithSlop() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("title", "City the").slop(4);
        NativeSearchQueryBuilder nativeSearchQuery = new NativeSearchQueryBuilder();
        nativeSearchQuery.withQuery(queryBuilder);
        SearchHits<Movies> hints = elasticsearchOperations.search(nativeSearchQuery.build(), Movies.class, index);
        System.out.println(hints.stream().count());
        hints.forEach(System.out::println);

    }

    @Test
    public void findByStringQuery() {
        IndexCoordinates index = IndexCoordinates.of("movies");
        String source = "{\n" +
                "    \"term\": {\n" +
                "      \"title\": {\n" +
                "        \"value\": \"2012\"\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        StringQuery stringQuery = new StringQuery(source);
        SearchHits<Movies> hints = elasticsearchOperations.search(stringQuery, Movies.class, index);
        System.out.println(hints.stream().count());
        hints.forEach(System.out::println);
    }

    @Test
    public void queryByParams() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("movies");

        restHighLevelClient.search(request, RequestOptions.DEFAULT);

    }
}
