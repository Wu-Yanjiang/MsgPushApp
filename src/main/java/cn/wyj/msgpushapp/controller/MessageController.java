package cn.wyj.msgpushapp.controller;

import cn.wyj.msgpushapp.common.Result;
import cn.wyj.msgpushapp.dto.in.SendMsgDTO;
import cn.wyj.msgpushapp.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 主要业务消息处理
 */
@Slf4j
@RestController
@RequestMapping("/message")
@SuppressWarnings({"rawtypes"})
public class MessageController {

    @Resource
    private MessageService messageService;


    /**
     * 发送消息给用户
     *
     * @return result
     */
    @PostMapping("/push")
    public Result sendMsg(@RequestBody @Valid SendMsgDTO dto) {
        log.info(dto.toString());
        messageService.sendMsgToUser(dto);
        return Result.success();
    }

}
