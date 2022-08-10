package cn.yjlearn.msgpushapp.service.msgsend.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.yjlearn.msgpushapp.bean.WeChatMsgBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class WeServiceChatSendServiceTest {

    @Test
    public void getAccessToken() {
    }

    /**
     * {"errcode":0,"errmsg":"ok","access_token":"_Z-Rixva5sJY468V3V1cpq0mY9qAEC8vgBvek93l5mms4tHCTQ03wTWialV5Dz7LVnZfrObGQox1Dq1y_Y1xdhZCKurQ2UDZb_SDH-OPf6YI-dzDWH7NC-jnOMATyN9wbk4Xof7e_i5THX900VNQPcDeKIAHLtFXLHvgQcGVWFt5szdw3oBDJPlf_Sp8EJH4vaX2oQwPtfZsupr7J6FJjw","expires_in":7200}
     */
    @Test
    public void send() {
        WeServiceChatSendService s = new WeServiceChatSendService();
        WeServiceChatSendService.WechatCfg cfg = WeServiceChatSendService.WechatCfg.builder().corpid("").corpsecret("").build();
        WeChatMsgBean msg = WeChatMsgBean.builder().agentId(1000002).text(new WeChatMsgBean.Text("这是测试消息标题", "测试消息内容")).build();
        s.send(cfg, msg);
    }

    private static final String MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={ACCESS_TOKEN}&debug=1";

    String accessToken = "_Z-Rixva5sJY468V3V1cpq0mY9qAEC8vgBvek93l5mms4tHCTQ03wTWialV5Dz7LVnZfrObGQox1Dq1y_Y1xdhZCKurQ2UDZb_SDH-OPf6YI-dzDWH7NC-jnOMATyN9wbk4Xof7e_i5THX900VNQPcDeKIAHLtFXLHvgQcGVWFt5szdw3oBDJPlf_Sp8EJH4vaX2oQwPtfZsupr7J6FJjw";


    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Test
    public void send2() {
        String url = StrUtil.format(MSG_URL, HashMap.of("ACCESS_TOKEN", accessToken).toJavaMap());
        WeChatMsgBean wcMsg = WeChatMsgBean.builder().agentId(1000002).text(new WeChatMsgBean.Text("这是测试消息标题", "测试消息内容")).build();

        ObjectMapper mapper = new ObjectMapper();
        String json = JSONUtil.toJsonPrettyStr(mapper.writeValueAsString(wcMsg));
        var map = mapper.readValue(json, Map.class);
        if(JSONUtil.isTypeJSON(json)) {
            log.info(json);
        }
        map.put("agentid", "1000002");

        JSONObject entries = new JSONObject();
        entries.putAll(map);
        json = entries.toString();
        log.info(JSONUtil.toJsonPrettyStr(json));
        String r = HttpUtil.post(url, json);
        log.info(r);
    }

}