package cn.wyj.msgpushapp.dto.in;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterInfoDTO {

    @Email(message = "邮箱格式错误")
    private String email;

    private String name;

    private String wechatId;

    @NotBlank(message = "企业微信Id不为空")
    private String companyId;

    @NotBlank(message = "企业微信应用Id")
    private String agentId;

    @NotBlank(message = "企业微信应用Secret不为空")
    private String appSecret;

    private String pushUid = "@all";

}
