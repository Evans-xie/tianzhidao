package runtheworld.exception;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/12 10:30
 */
public class UserLoginException extends RuntimeException{
    public UserLoginException(String message){
        super(message);
    }

    public UserLoginException(String message,Throwable cause){
        super(message,cause);
    }

}
