package com.example.template.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClient_doc_delete {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    DeleteRequest request = new DeleteRequest();
    request.index("user").id("1001");
    DeleteResponse getResponse = esclient.delete(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(getResponse.toString());
    //关闭es客户端
    esclient.close();
  }
}
