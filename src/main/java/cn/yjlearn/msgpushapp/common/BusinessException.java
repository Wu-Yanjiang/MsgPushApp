package cn.yjlearn.msgpushapp.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2900384359524205406L;

    private ReturnResultEnum returnResultEnum;

    public BusinessException(ReturnResultEnum e) {
        this.returnResultEnum = e;
    }

}
