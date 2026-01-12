package com.hanfb.mcp.server.csdn.infrastructure.adapter;

import com.hanfb.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.CSDNApiGateway;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import com.hanfb.mcp.server.csdn.type.properties.CSDNApiProperties;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
public class CSDNPort implements ICSDNPort {

    @Resource
    private CSDNApiGateway csdnApiGateway;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Override
    public ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException {

        ArticleRequestDTO articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setMarkdowncontent(request.getMarkdowncontent());
        articleRequestDTO.setContent(request.getContent());
        articleRequestDTO.setTags(request.getTags());
        articleRequestDTO.setDescription(request.getDescription());
        articleRequestDTO.setCategories(csdnApiProperties.getCategories());

        ArticleResponseDTO response = csdnApiGateway.saveArticle(csdnApiProperties.getCookie(), articleRequestDTO);

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (null == response) return null;
        ArticleResponseDTO.ArticleData articleData = response.getData();

        ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
        articleFunctionResponse.setCode(response.getCode());
        articleFunctionResponse.setMsg(response.getMsg());
        articleFunctionResponse.setArticleData(ArticleFunctionResponse.ArticleData.builder()
                .url(articleData.getUrl())
                .id(articleData.getId())
                .qrcode(articleData.getQrcode())
                .title(articleData.getTitle())
                .description(articleData.getDescription())
                .build());

        return articleFunctionResponse;

    }

}
