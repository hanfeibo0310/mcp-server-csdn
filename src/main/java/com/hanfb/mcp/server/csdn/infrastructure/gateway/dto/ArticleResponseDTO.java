package com.hanfb.mcp.server.csdn.infrastructure.gateway.dto;

import lombok.Data;

@Data
public class ArticleResponseDTO {

    // 响应码
    private int code;

    // 跟踪ID
    private String traceId;

    // 提示信息
    private String msg;

    // 业务数据
    private ArticleData data;

    @Data
    public static class ArticleData {
        // 文章链接
        private String url;
        // 文章ID
        private long id;
        // 二维码链接
        private String qrcode;
        // 文章标题
        private String title;
        // 文章摘要
        private String description;
    }
}