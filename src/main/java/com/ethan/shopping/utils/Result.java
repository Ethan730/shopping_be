package com.ethan.shopping.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private boolean success;
    private String msg;
    private T data;

    private Result(boolean success) {
        this.success = success;
    }

    private Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    private Result(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(true);
    }

    public static Result success(String msg) {
        return new Result(true, msg);
    }

    public static <T> Result success(T data) {
        return new Result(true, null, data);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    public static Result fail(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        for(ObjectError error:bindingResult.getAllErrors()){
            sb.append(error.getDefaultMessage());
        }
        return Result.fail(sb.toString());
    }

    public static Result fail(MyException e){
        return Result.fail(e.getMessage());
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}