package com.hanfb.mcp.server.csdn.infrastructure.gateway;

import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Component
public class CSDNApiGateway {

    private final ICSDNService icsdnService;

    public CSDNApiGateway(ICSDNService icsdnService) {
        this.icsdnService = icsdnService;
    }

    public ArticleResponseDTO saveArticle(String cookie, ArticleRequestDTO request) throws IOException {
        Call<ArticleResponseDTO> call = icsdnService.saveArticle(request, cookie);
        Response<ArticleResponseDTO> response = call.execute();
        String s = new String(response.errorBody().bytes());
        if (!response.isSuccessful()) {
            throw new IOException("CSDN saveArticle HTTP " + response.code() + " - " + s);
        }
        return response.body();
    }
}