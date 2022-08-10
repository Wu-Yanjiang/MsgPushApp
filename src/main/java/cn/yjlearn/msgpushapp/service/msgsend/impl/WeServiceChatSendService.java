package cn.yjlearn.msgpushapp.service.msgsend.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.yjlearn.msgpushapp.bean.AppConfig;
import cn.yjlearn.msgpushapp.bean.MsgBean;
import cn.yjlearn.msgpushapp.bean.WeChatMsgBean;
import cn.yjlearn.msgpushapp.common.BizException;
import cn.yjlearn.msgpushapp.common.ReturnResultEnum;
import cn.yjlearn.msgpushapp.service.msgsend.ISendMsgService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.collection.HashMap;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 微信消息发送服务
 */
@Slf4j
@Service
public class WeServiceChatSendService implements ISendMsgService {

    private static final String MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={ACCESS_TOKEN}";

    private static final String TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={ID}&corpsecret={SECRET}";

    private static final long EXPIRED_TIME_MS = 2 * 60 * 60 * 1000;

    private static final Cache<String, String> CACHE = CacheUtil.newTimedCache(EXPIRED_TIME_MS);

    /**
     * {
     * "errcode": 0,
     * "errmsg": "ok",
     * "access_token": "accesstoken000001",
     * "expires_in": 7200
     * }
     * <p>
     * <p>
     * 参数	            说明
     * errcode	        出错返回码，为0表示成功，非0表示调用失败
     * errmsg	        返回码提示语
     * access_token	获取到的凭证，最长为512字节
     * expires_in	    凭证的有效时间（秒）
     */
    @Data
    public static class Response implements Serializable {

        @JsonProperty(value = "errcode")
        private int errCode;

        @JsonProperty(value = "errmsg")
        private String errMsg;

        @JsonProperty(value = "access_token")
        private String accessToken;

        @JsonProperty(value = "expires_in")
        private long expiresIn;
    }


    /**
     * 获取访问token
     * <link>https://developer.work.weixin.qq.com/document/path/91039</link>
     *
     * @param corpid     企业id
     * @param corpsecret 应用的凭证密钥
     * @return token
     */
    protected String getAccessToken(String corpid, String corpsecret) {
        Assert.notBlank(corpid);
        Assert.notBlank(corpsecret);

        String key = getKey(corpid, corpsecret);
        String token = CACHE.get(key);
        if (StrUtil.isEmpty(token)) {
            String url = StrUtil.format(TOKEN_URL, HashMap.of("ID", corpid, "SECRET", corpsecret).toJavaMap());
            String r = HttpUtil.get(url);
            log.info(r);
            Response response = JSONUtil.toBean(r, Response.class);
            if (response.getErrCode() == 0) {
                CACHE.put(key, response.getAccessToken(), response.getExpiresIn() * 1000);
                return response.getAccessToken();
            } else {
                throw new BizException(ReturnResultEnum.GetTokenFailed);
            }
        } else {
            return token;
        }
    }

    private String getKey(String corpid, String corpsecret) {
        return corpid + "_" + corpsecret;
    }

    @Data
    @Builder
    public static class WechatCfg implements AppConfig {
        private String corpid;
        private String corpsecret;
    }

    @Override
    public void send(AppConfig cfg, MsgBean msgBean) {
        WechatCfg wCfg = (WechatCfg) cfg;
        WeChatMsgBean wcMsg = (WeChatMsgBean) msgBean;
        String accessToken = getAccessToken(wCfg.getCorpid(), wCfg.getCorpsecret());
        String url = StrUtil.format(MSG_URL, HashMap.of("ACCESS_TOKEN", accessToken).toJavaMap());

        String r = HttpUtil.post(url, HashMap.of("msgtype", wcMsg.getMsgType(), "agentid", wcMsg.getAgentId(), "text", wcMsg.getText()).toJavaMap());
        log.info(r);

    }
}
