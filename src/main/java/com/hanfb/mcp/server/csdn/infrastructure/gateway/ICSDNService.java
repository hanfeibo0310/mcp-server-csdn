package com.hanfb.mcp.server.csdn.infrastructure.gateway;

import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICSDNService {

    @POST("/blog-console-api/v3/mdeditor/saveArticle")
    @Headers({
            "Accept-Encoding: gzip, deflate, br",
            "Connection: keep-alive",
            "accept: */*",
            "accept-language: zh-CN,zh;q=0.9",
            "content-type: application/json",
            "origin: https://editor.csdn.net",
            "priority: u=1, i",
            "referer: https://editor.csdn.net/",
            "sec-ch-ua: \"Chromium\";v=\"142\", \"Google Chrome\";v=\"142\", \"Not_A Brand\";v=\"99\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"Windows\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36",
            "x-ca-key: 203803574",
            "x-ca-nonce: 656a973d-a020-4bc8-9039-d9c68351c1a4",
            "x-ca-signature: /rbjX8+GfXIZe9Kl3RkvWvnysUdURmvINgrXQaRieoY=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce"
    })
    Call<ArticleResponseDTO> saveArticle(
            @Body ArticleRequestDTO request,
            @Header("Cookie") String cookie
    );
}