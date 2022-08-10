package cn.yjlearn.msgpushapp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 参数                 	    是否必须	说明
 * touser	                    否	    指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为"@all"，则向该企业应用的全部成员发送
 * toparty	                    否	    指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为"@all"时忽略本参数
 * totag	                    否	    指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为"@all"时忽略本参数
 * msgtype	                    是	    消息类型，此时固定为：text
 * agentid	                    是	    企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
 * content	                    是	    消息内容，最长不超过2048个字节，超过将截断（支持id转译）
 * safe	                    否	    表示是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
 * enable_id_trans	            否	    表示是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
 * enable_duplicate_check	    否	    表示是否开启重复消息检查，0表示否，1表示是，默认0
 * duplicate_check_interval	否	    表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
 * <p>
 * <p>
 * {
 * "touser" : "UserID1|UserID2|UserID3",
 * "toparty" : "PartyID1|PartyID2",
 * "totag" : "TagID1 | TagID2",
 * "msgtype" : "text",
 * "agentid" : 1,
 * "text" : {
 * "content" : "你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。"
 * },
 * "safe":0,
 * "enable_id_trans": 0,
 * "enable_duplicate_check": 0,
 * "duplicate_check_interval": 1800
 * }
 */
@Data
@Builder
public class WeChatMsgBean implements MsgBean {

    @Builder.Default
    @JsonProperty(value = "touser")
    private String toUser = "@all";

    @JsonProperty("toparty")
    private String toParty;

    @JsonProperty("totag")
    private String toTag;

    @Builder.Default
    @JsonProperty(value = "msgtype")
    private String msgType = "text";

    @JsonProperty("agentid")
    private int agentId;

    @JsonProperty("text")
    private Text text;

    @JsonProperty("safe")
    private int safe;

    @JsonProperty("enable_id_trans")
    private int enableIdTrans;

    @JsonProperty("enable_duplicate_check")
    private int enableDuplicateCheck;

    @JsonProperty("duplicate_check_interval")
    private int duplicateCheckInterval;

    @Data
    public static class Text {

        @JsonProperty("content")
        private String content;

        public Text(String title, String content) {
            this.content = title + "\r\n" + content;
        }
    }
}
