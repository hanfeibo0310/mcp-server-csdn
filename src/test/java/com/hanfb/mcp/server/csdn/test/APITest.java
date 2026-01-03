package com.hanfb.mcp.server.csdn.test;

import com.alibaba.fastjson.JSON;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.CSDNApiGateway;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.hanfb.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class APITest {

    @Autowired
    private CSDNApiGateway gateway;

    @Test
    public void testSaveArticle() throws Exception {
        String cookie = "uuid_tt_dd=10_9894679500-1734859691772-971583; fid=20_80883252794-1763394740515-583571; UserName=hfb_love_ld; UserInfo=24153e726cee4f8996bc0631af284199; UserToken=24153e726cee4f8996bc0631af284199; UserNick=hfb_love_ld; AU=4C5; UN=hfb_love_ld; BT=1763394766687; p_uid=U010000; csdn_newcert_hfb_love_ld=1; __gads=ID=f98b3e552ecca5d7:T=1734859694:RT=1764489206:S=ALNI_MYQgS3_HXSuiMf8ZI_uJiXIiVxkvA; __gpi=UID=00000fb221f0de94:T=1734859694:RT=1764489206:S=ALNI_MY55QKf1jCxINMnEFi92wZHCu1Dgw; __eoi=ID=b9108db50fc69d5b:T=1764489206:RT=1764489206:S=AA-AfjYE-qwaKFqldt_FDK_qIrwC; FCCDCF=[null,null,null,null,null,null,[[32,\"[\\\"1f2727a2-a742-46e9-8183-c0f9e4809215\\\",[1764489208,96000000]]\"]]]; FCNEC=[[\"AKsRol_jEIpVy8VGMR5f-hjzwur-MChZv80KeYvm5oBadTX5DHp7IEVvmAKMSHgFugieR2fcBV05vMnvn7D7UI-gG6373iUJ1AktHCzf3n4znBia5yy09xdFTKrEhxstmU-nQQYXiEN4VRHVzm3HSijhi-4ld_tJdA==\"]]; _clck=14pbw1l^2^g1t^0^2159; dc_sid=c0a425532822c98b58ce7bcc76ff9e49; dc_session_id=11_1767251350687.674187; c_first_ref=articles.zsxq.com; c_first_page=https://editor.csdn.net/md/; c_segment=15; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1765609248,1767251353; HMACCOUNT=AA8CC7E790FB1D1E; c_dsid=11_1767251514089.096721; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1767251515; c_pref=https://articles.zsxq.com/; c_ref=https://editor.csdn.net/; c_page_id=default; log_Id_pv=4; log_Id_view=78; log_Id_click=10; dc_tos=t86cco; SESSION=5efa49bc-0986-41bc-acb5-73f9596c7cad";
        if (cookie == null || cookie.isEmpty()) {
            cookie = System.getProperty("CSDN_COOKIE", "");
        }
        if (cookie == null || cookie.isEmpty()) {
            System.out.println("Cookie 未设置，请通过环境变量 CSDN_COOKIE 或 JVM 参数 -DCSDN_COOKIE=... 提供。");
            return;
        }
        ArticleRequestDTO req = new ArticleRequestDTO();
        req.setTitle("mcp发帖测试");
        req.setMarkdowncontent("这是一个用于验证 CSDNPort 发帖的 Markdown 内容。\n" +
                "\n" +
                "- 由单元测试发送\n");
        req.setContent("<p>这是一个用于验证 CSDNPort 发帖的 Markdown 内容。</p>\n" +
                "<ul>\n" +
                "<li>由单元测试发送</li>\n" +
                "</ul>\n");
        req.setReadType("public");
        req.setLevel("0");
        req.setTags("java,unit-test");
        req.setStatus(0);
        req.setCategories("Java场景面试宝典");
        req.setType("original");
        req.setOriginal_link("");
        req.setAuthorized_status(false);
        req.setDescription("CSDNPort 单元测试发布文章");
        req.setNot_auto_saved("1");
        req.setSource("pc_mdeditor");
        req.setCover_images(Collections.emptyList());
        req.setCover_type(1);
        req.setIs_new(1);
        req.setVote_id(0);
        req.setResource_id("");
        req.setPubStatus("publish");
        req.setSync_git_code(0);


        String str = "{\"categories\":\"Java场景面试宝典\",\"content\":\"<p>这是一个用于验证 CSDNPort 发帖的 Markdown 内容。</p>\\n<ul>\\n<li>由单元测试发送</li>\\n</ul>\\n\",\"description\":\"CSDNPort 单元测试发布文章\",\"markdowncontent\":\"这是一个用于验证 CSDNPort 发帖的 Markdown 内容。\\n\\n- 由单元测试发送\\n\",\"tags\":\"java,unit-test\",\"title\":\"mcp发帖测试 - CSDNPort\"}";
        ArticleRequestDTO requestDTO = JSON.parseObject(str, ArticleRequestDTO.class);


        ArticleResponseDTO resp = gateway.saveArticle(cookie, requestDTO);
        if (resp != null) {
            System.out.println("code=" + resp.getCode());
            System.out.println("msg=" + resp.getMsg());
            ArticleResponseDTO.ArticleData data = resp.getData();
            System.out.println("url=" + (data != null ? data.getUrl() : ""));
        } else {
            System.out.println("响应体为空");
        }
    }
}
