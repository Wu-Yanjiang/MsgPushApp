package cn.yjlearn.msgpushapp.dto.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SendMsgDTO {

    @NotBlank(message = "sendKey非空")
    String sendKey;

    @NotBlank(message = "标题非空")
    String head;

    @NotBlank(message = "正文非空")
    String body;

}
