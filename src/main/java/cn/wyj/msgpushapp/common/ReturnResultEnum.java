package cn.wyj.msgpushapp.common;

public enum ReturnResultEnum {

    Success(0, "成功"),
    Fail(1001, "失败"),
    WrongParams(1002, "错误的参数"),
    SystemError(1003, "系统错误"),
    NoSendKeyConfigured(1004, "没有配置的sendKey"),
    NoMsgServiceConfigured(1005, "没有配置的发送服务信息"),
    ;

    private int code;
    private String msg;

    ReturnResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
