package cn.wyj.msgpushapp.service;

import cn.wyj.msgpushapp.common.BusinessException;
import cn.wyj.msgpushapp.common.ReturnResultEnum;
import cn.wyj.msgpushapp.dao.MessageConfigMapper;
import cn.wyj.msgpushapp.dao.UserInfoMapper;
import cn.wyj.msgpushapp.dto.in.SendMsgDTO;
import cn.wyj.msgpushapp.entity.MessageConfig;
import cn.wyj.msgpushapp.entity.UserInfo;
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
            throw new BusinessException(ReturnResultEnum.NoSendKeyConfigured);
        }
        MessageConfig messageConfigured = messageConfigMapper.selectOne(new QueryWrapper<MessageConfig>().eq("user_id", user.getId()));
        if (Objects.isNull(messageConfigured)) {
            throw new BusinessException(ReturnResultEnum.NoMsgServiceConfigured);
        }

    }

}
