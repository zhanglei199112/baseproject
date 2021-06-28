package com.example.template.es;

import com.example.template.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsClient_doc_update {

  public static void main(String[] args) throws Exception {
    //创建es客户端
    RestHighLevelClient esclient = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200,"http")));
    //插入数据
    UpdateRequest request = new UpdateRequest();
    request.index("user").id("1001");
    //将数据转换成json
    request.doc(XContentType.JSON,"sex","女");
    UpdateResponse indexResponse = esclient.update(request, RequestOptions.DEFAULT);
    //获取响应状态
    System.out.print(indexResponse.getResult());
    //关闭es客户端
    esclient.close();
  }
}
