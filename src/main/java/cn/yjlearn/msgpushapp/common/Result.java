package cn.yjlearn.msgpushapp.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5430719244722923815L;

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return resultBuild(ReturnResultEnum.Success);
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(ReturnResultEnum.Success.getCode())
                .msg(ReturnResultEnum.Success.getMsg())
                .data(data)
                .build();
    }

    public static <T> Result<T> fail() {
        return resultBuild(ReturnResultEnum.Fail);
    }

    public static <T> Result<T> fail(ReturnResultEnum status) {
        return resultBuild(status);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return Result.<T>builder().code(code).msg(msg).build();
    }

    protected static <T> Result<T> resultBuild(ReturnResultEnum status) {
        return Result.<T>builder().code(status.getCode()).msg(status.getMsg()).build();
    }


}


