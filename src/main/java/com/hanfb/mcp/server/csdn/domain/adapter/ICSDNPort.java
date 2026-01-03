package com.hanfb.mcp.server.csdn.domain.adapter;

import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionResponse;

import java.io.IOException;

public interface ICSDNPort {

    ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException;

}
