package com.demo.elasticsearch;

import com.demo.elasticsearch.bean.Student;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = ElasticSearchApplication.class)
public class StudentIndexTest {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void saveStudent() {
        Student student = new Student();
        student.setStudentId(uuid());
        student.setName("Robbin");
        student.setCourses(new ArrayList<>());
        elasticsearchOperations.save(student);

    }

    @Test
    public void findById() {
        SearchRequest searchRequest = new SearchRequest("student_1");
        try {
            QueryBuilder queryBuilder = QueryBuilders.termsQuery("studentId","742c36321f6c487c98a10fbfc2aa8245");
            searchRequest.source().query(queryBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(response.getHits().getHits()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void scriptTest() {
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("student_1");
        try {
            QueryBuilder queryBuilder = QueryBuilders.termsQuery("studentId","dad486e87dbd4188b9c11d4e8107d444");
            updateByQueryRequest.setQuery(queryBuilder);
            Map<String, Object> params = new HashMap<>();
            params.put("course","1");
            Script script =new Script(Script.DEFAULT_SCRIPT_TYPE,"painless",
                    "ctx._source.courses.add(params.course)",params);
            updateByQueryRequest.setScript(script);
            BulkByScrollResponse response = restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            System.out.println(response.getUpdated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
