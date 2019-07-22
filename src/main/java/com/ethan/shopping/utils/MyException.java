package com.ethan.shopping.utils;

public class MyException extends RuntimeException {
    public MyException(){
        super();
    }
    public MyException(String msg){
        super(msg);
    }
    public MyException(Throwable cause){
        super(cause);
    }
    public MyException(String msg, Throwable cause){
        super(msg, cause);
    }
}
