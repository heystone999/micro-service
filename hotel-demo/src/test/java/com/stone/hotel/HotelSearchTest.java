package com.stone.hotel;

import com.alibaba.fastjson.JSON;
import com.stone.hotel.pojo.HotelDoc;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class HotelSearchTest {
    private RestHighLevelClient client;


    @Test
    void testMatchAll() throws IOException {
        // 准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 准备DSL
        request.source().query(QueryBuilders.matchAllQuery());
        // 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 解析响应
        handleResponse(response);
    }

    @Test
    void testMatch() throws IOException {
        // 准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 准备DSL
        request.source().query(QueryBuilders.matchQuery("all", "如家"));
        // 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 解析响应
        handleResponse(response);
    }

    private static void handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            System.out.println("hotelDoc = " + hotelDoc);
        }
    }

    @Test
    void testBool() throws IOException {
        // 准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 准备DSL
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("city", "上海"));
        boolQuery.filter(QueryBuilders.rangeQuery("price").lte(250));
        request.source().query(boolQuery);
        // 发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 解析响应
        handleResponse(response);
    }


    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://localhost:9200")));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
