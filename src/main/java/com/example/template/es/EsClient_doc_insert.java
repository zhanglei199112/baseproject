package com.example.template.es;

import com.example.template.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

public class EsClient_doc_insert {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    IndexRequest request = new IndexRequest();
    request.index("user").id("1001");
    User user = new User();
    user.setName("张三");
    user.setAge(30);
    user.setSex("男");
    //将数据转换成json
    ObjectMapper mapper = new ObjectMapper();
    String userJson = mapper.writeValueAsString(user);
    request.source(userJson, XContentType.JSON);
    IndexResponse indexResponse = esclient.index(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.print(indexResponse.getResult());
    //关闭es客户端
    esclient.close();
  }
}
