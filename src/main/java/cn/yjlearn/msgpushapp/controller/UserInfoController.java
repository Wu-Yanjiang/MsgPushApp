package cn.yjlearn.msgpushapp.controller;

import cn.yjlearn.msgpushapp.common.Result;
import cn.yjlearn.msgpushapp.common.ReturnResultEnum;
import cn.yjlearn.msgpushapp.dto.in.UserRegisterInfoDTO;
import cn.yjlearn.msgpushapp.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@SuppressWarnings({"rawtypes"})
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 邮箱注册
     *
     * @param dto 邮箱和企业配置信息
     *
     * @return 结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserRegisterInfoDTO dto) {
        userInfoService.emailVerify(dto.getEmail());
        return Result.success();
    }

    /**
     * @param code 随机注册码
     *
     * @return 结果
     */
    @GetMapping("/register/email/verify/{code}")
    public Result emailVerify(@PathVariable String code) {
        // 根据code从缓存中查询，如果过期或没找到就返回失败
        log.info("检查code:{}是否存在或过期", code);
        if (!"123456".equals(code)) {
            return Result.fail(ReturnResultEnum.VerifyCodeIsExpired);
        }
        return Result.success();
    }



}
