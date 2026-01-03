package com.hanfb.mcp.server.csdn;

import com.hanfb.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    // 新增：OkHttpClient Bean
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 可按需添加日志拦截器、超时设置等
                .build();
    }

    // 新增：Retrofit Bean（基于 CSDN BizAPI 域名）
    @Bean
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://bizapi.csdn.net/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    // 新增：ICSDNService Bean
    @Bean
    public ICSDNService icsdnService(Retrofit retrofit) {
        return retrofit.create(ICSDNService.class);
    }
}
