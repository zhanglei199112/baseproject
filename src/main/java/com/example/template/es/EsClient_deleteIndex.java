package com.example.template.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

public class EsClient_deleteIndex {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //获取索引
    DeleteIndexRequest user = new DeleteIndexRequest("user");
    AcknowledgedResponse delete = esclient.indices().delete(user, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.println(delete.isAcknowledged());
    //关闭es客户端
    esclient.close();
  }
}
