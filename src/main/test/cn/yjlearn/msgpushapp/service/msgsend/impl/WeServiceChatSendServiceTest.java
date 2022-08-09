package cn.yjlearn.msgpushapp.service.msgsend.impl;

import cn.yjlearn.msgpushapp.bean.WeChatMsgBean;
import org.junit.Test;


public class WeServiceChatSendServiceTest {

    @Test
    public void getAccessToken() {
    }

    @Test
    public void send() {
        WeServiceChatSendService s = new WeServiceChatSendService();
        WeChatMsgBean msg = WeChatMsgBean.builder().agentTd("1000002").text(new WeChatMsgBean.Text("这是测试消息标题", "测试消息内容")).build();
        WeServiceChatSendService.WechatCfg cfg = WeServiceChatSendService.WechatCfg.builder().corpid("").corpsecret("").build();
        s.send(cfg, msg);
    }
}