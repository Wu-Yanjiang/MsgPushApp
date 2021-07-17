package cn.wyj.msgpushapp.config;

import cn.wyj.msgpushapp.common.BusinessException;
import cn.wyj.msgpushapp.common.Result;
import cn.wyj.msgpushapp.common.ReturnResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 *
 */
@Slf4j
@SuppressWarnings({"rawtypes"})
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({BusinessException.class, BindException.class})
    public Result resolveSystemException(Exception ex) {
        log.warn("Exception Happened:", ex);
        if (ex instanceof BusinessException) {
            BusinessException e = (BusinessException) ex;
            return Result.fail(e.getReturnResultEnum());
        } else if (ex instanceof BindException) {
            BindException e = (BindException) ex;
            return Result.fail(ReturnResultEnum.WrongParams.getCode(),
                    Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        } else {
            return Result.fail();
        }
    }

    @ExceptionHandler(Exception.class)
    public Result resolveException(Exception ex) {
        log.warn("Exception Happened:", ex);
        return Result.fail(ReturnResultEnum.SystemError);
    }


}
