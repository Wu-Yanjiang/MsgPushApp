package cn.yjlearn.msgpushapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeChatMsgBean {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("toparty")
    private String toParty;

    @JsonProperty("totag")
    private String toTag;

    @JsonProperty("msgtype")
    private String msgType = "text";


    @JsonProperty("agentid")
    private String agentTd;

    private Text text;

    private int safe = 0;

    @JsonProperty("enable_id_trans")
    private int enableIdTrans = 0;

    @JsonProperty("enable_duplicate_check")
    private int enableDuplicateCheck = 0;

    @JsonProperty("duplicate_check_interval")
    private int duplicateCheckInterval = 1800;

    @Data
    public static class Text {
        private String content;
    }
}
