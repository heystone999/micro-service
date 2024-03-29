package com.stone.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.stone.hotel.constants.HotelConstants.MAPPING_TEMPLATE;

public class HotelIndexTest {
    private RestHighLevelClient client;

    @Test
    void testInit() {
        System.out.println(client);
    }

    @Test
    void createHotelIndex() throws IOException {
        // 创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 准备请求参数: DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void testDeleteHotelIndex() throws IOException {
        // 创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        // 发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testExistsHotelIndex() throws IOException {
        // 创建Request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 准备请求参数: DSL语句
        // 发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.err.println(exists ? "索引库已经存在" : "索引库不存在");
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
