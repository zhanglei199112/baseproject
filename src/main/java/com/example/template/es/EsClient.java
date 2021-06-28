package com.example.template.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

public class EsClient {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //创建索引
    CreateIndexRequest user = new CreateIndexRequest("user");
    CreateIndexResponse createIndexResponse = esclient.indices()
        .create(user, RequestOptions.DEFAULT);
    //获取响应状态
    boolean acknowledged = createIndexResponse.isAcknowledged();
    System.out.println("索引操作:"+acknowledged);
    //关闭es客户端
    esclient.close();
  }
}
