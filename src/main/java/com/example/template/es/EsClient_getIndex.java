package com.example.template.es;

import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;

public class EsClient_getIndex {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //获取索引
    GetIndexRequest user = new GetIndexRequest("user");
    GetIndexResponse getIndexResponse = esclient.indices().get(user, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(getIndexResponse.getAliases());
    System.out.println(getIndexResponse.getMappings());
    System.out.println(getIndexResponse.getSettings());
    //关闭es客户端
    esclient.close();
  }
}
