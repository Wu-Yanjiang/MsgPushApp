package cn.wyj.msgpushapp.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@SuppressWarnings({"rawtypes"})
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5430719244722923815L;

    private int code;
    private String msg;
    private T data;

    public static Result success() {
        return resultBuild(ReturnResultEnum.Success);
    }

    public static <T> Result success(T data) {
        return Result.builder()
                .code(ReturnResultEnum.Success.getCode())
                .msg(ReturnResultEnum.Success.getMsg())
                .data(data)
                .build();
    }

    public static Result fail() {
        return resultBuild(ReturnResultEnum.Fail);
    }

    public static Result fail(ReturnResultEnum status) {
        return resultBuild(status);
    }

    public static Result fail(int code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

    protected static Result resultBuild(ReturnResultEnum status) {
        return Result.builder().code(status.getCode()).msg(status.getMsg()).build();
    }


}


