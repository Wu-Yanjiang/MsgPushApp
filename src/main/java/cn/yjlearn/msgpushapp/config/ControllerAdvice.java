package cn.yjlearn.msgpushapp.config;

import cn.yjlearn.msgpushapp.common.BizException;
import cn.yjlearn.msgpushapp.common.Result;
import cn.yjlearn.msgpushapp.common.ReturnResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 *
 */
@Slf4j
@SuppressWarnings({"rawtypes"})
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * 捕获业务层抛出的异常信息
     *
     * @param ex 异常
     *
     * @return Controller的返回
     */
    @ExceptionHandler({BizException.class, BindException.class, NoHandlerFoundException.class})
    public Result resolveSystemException(Exception ex) {
        if (ex instanceof BizException) {
            BizException e = (BizException) ex;
            return Result.fail(e.getReturnResultEnum());
        } else if (ex instanceof BindException) {
            BindException e = (BindException) ex;
            return Result.fail(ReturnResultEnum.WrongParams.getCode(),
                    Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        } else if (ex instanceof NoHandlerFoundException) {
            return Result.fail(ReturnResultEnum.PathCannotFind);
        } else {
            return Result.fail();
        }
    }

    /**
     * 捕获系统内部异常信息
     *
     * @param ex 异常
     *
     * @return 错误信息
     */
    @ExceptionHandler(Exception.class)
    public Result resolveException(Exception ex) {
        log.warn("Exception Happened:", ex);
        return Result.fail(ReturnResultEnum.SystemError);
    }


}
