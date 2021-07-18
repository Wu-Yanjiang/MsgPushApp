package cn.wyj.msgpushapp.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    /**
     * 邮箱验证,给注册的邮箱发送注册链接
     */
    public void emailVerify(String email) {
        String code = IdUtil.objectId();

        MailUtil.send(email, "推送服务注册链接", "点击以下链接完成注册。", false);
    }

}
