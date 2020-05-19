package com.test.exception;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.exception
 * @ClassName: LoginFailedExecption
 * @Author: ZhangJunjie
 * @Description: 自定义登陆失败异常
 * @Date: 2020/5/8 22:05
 * @Version: 1.0
 */
public class LoginFailedException extends RuntimeException {
    /**
     * description:序列化操作
     * create time: 22:08 2020/5/8
     */
    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
