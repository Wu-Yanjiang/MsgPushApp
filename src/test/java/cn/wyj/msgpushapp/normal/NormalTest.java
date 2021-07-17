package cn.wyj.msgpushapp.normal;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.wyj.msgpushapp.bean.WeChatMsgBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class NormalTest {

    @Test
    public void httpTest() throws JsonProcessingException {
        String token = "";
        String corpid = "wwa06a728cfda07d19";
        String corpsecret = "2E2NiAFWuPDU0lZ4Iq8g1GBiaqVxjc119iRSnNm0UjM";
        String getTokenURL = String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s",
                corpid, corpsecret);

        String result = HttpUtil.get(getTokenURL);
        log.info(result);
        JSONObject ret = JSONUtil.parseObj(result);
        token = ret.get("access_token").toString();



        String url = String.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", token);
        WeChatMsgBean data = new WeChatMsgBean();
        data.setToUser("@all");
        data.setAgentTd("1000002");
        WeChatMsgBean.Text text = new WeChatMsgBean.Text();
        text.setContent("我爱你，你爱我，蜜雪冰城甜蜜蜜");
        data.setText(text);


        String json = new ObjectMapper().writeValueAsString(data);
        log.info(url);
        log.info(json);
        String post = HttpUtil.post(url, json);
        log.info(post);
    }
}
