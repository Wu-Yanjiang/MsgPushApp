package cn.wyj.msgpushapp.dto.in;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
public class SendMsgDTO {

    @NotBlank(message = "sendKey非空")
    String sendKey;

    @NotBlank(message = "标题非空")
    String head;

    String body;

}
