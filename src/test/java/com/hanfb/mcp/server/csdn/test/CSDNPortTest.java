package com.hanfb.mcp.server.csdn.test;
import com.hanfb.mcp.server.csdn.infrastructure.adapter.CSDNPort;
import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.hanfb.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.hanfb.mcp.server.csdn.type.properties.CSDNApiProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CSDNPortTest {
    @Autowired
    private CSDNPort csdnPort;
    @Autowired
    private CSDNApiProperties csdnApiProperties;

    //@Test
    public void testWriteArticle() throws Exception {
        String cookie = csdnApiProperties.getCookie();
        Assumptions.assumeTrue(cookie != null && !cookie.isBlank(), "CSDN Cookie 未配置，跳过测试。请通过环境变量 CSDN_API_COOKIE 或 application.yml 设置 csdn.api.cookie。");
        ArticleFunctionRequest req = new ArticleFunctionRequest();
        req.setTitle("mcp发帖测试 - CSDNPort");
        req.setMarkdowncontent("这是一个用于验证 CSDNPort 发帖的 Markdown 内容。\n\n- 由单元测试发送\n");
        req.setTags("java,unit-test");
        req.setDescription("CSDNPort 单元测试发布文章");
        ArticleFunctionResponse resp = csdnPort.writeArticle(req);
        org.junit.jupiter.api.Assertions.assertNotNull(resp, "响应体为空");
        System.out.println("code=" + resp.getCode());        System.out.println("msg=" + resp.getMsg());
        org.junit.jupiter.api.Assertions.assertNotNull(resp.getCode(), "响应 code 为空");
        org.junit.jupiter.api.Assertions.assertNotNull(resp.getMsg(), "响应 msg 为空");    }}