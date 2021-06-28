package com.example.template.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

public class EsClient_doc_get {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    GetRequest request = new GetRequest();
    request.index("user").id("1001");
    GetResponse getResponse = esclient.get(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(getResponse.getSourceAsString());
    //关闭es客户端
    esclient.close();
  }
}
