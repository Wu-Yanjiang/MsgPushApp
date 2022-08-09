package cn.yjlearn.msgpushapp.service.msgsend;

import cn.yjlearn.msgpushapp.bean.AppConfig;
import cn.yjlearn.msgpushapp.bean.MsgBean;

public interface ISendMsgService {

    /**
     * 发送消息
     *
     * @param cfg     应用配置
     * @param msgBean 消息
     */
    void send(AppConfig cfg, MsgBean msgBean);

}
