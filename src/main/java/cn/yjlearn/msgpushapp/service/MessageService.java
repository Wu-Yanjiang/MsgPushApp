package cn.yjlearn.msgpushapp.service;

import cn.yjlearn.msgpushapp.bean.WeChatMsgBean;
import cn.yjlearn.msgpushapp.common.BizException;
import cn.yjlearn.msgpushapp.common.ReturnResultEnum;
import cn.yjlearn.msgpushapp.dao.MessageConfigMapper;
import cn.yjlearn.msgpushapp.dao.UserInfoMapper;
import cn.yjlearn.msgpushapp.dto.in.SendMsgDTO;
import cn.yjlearn.msgpushapp.entity.MessageConfig;
import cn.yjlearn.msgpushapp.entity.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class MessageService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private MessageConfigMapper messageConfigMapper;

    /**
     * 发送消息给用户
     *
     * @param dto dto
     */
    public void sendMsgToUser(SendMsgDTO dto) {
        log.info("查询该用户绑定的");
        UserInfo user = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("send_key", dto.getSendKey()));
        if (Objects.isNull(user)) {
            throw new BizException(ReturnResultEnum.NoSendKeyConfigured);
        }
        MessageConfig messageConfigured = messageConfigMapper.selectOne(new QueryWrapper<MessageConfig>().eq("user_id", user.getId()));
        if (Objects.isNull(messageConfigured)) {
            throw new BizException(ReturnResultEnum.NoMsgServiceConfigured);
        }

        WeChatMsgBean msg = WeChatMsgBean.builder().agentId(messageConfigured.getAgentId()).text(new WeChatMsgBean.Text(dto.getHead(), dto.getBody())).build();


    }

}
