package com.test.exception;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.exception
 * @ClassName: RegisterException
 * @Author: ZhangJunjie
 * @Description: 注册异常
 * @Date: 2020/5/10 0:12
 * @Version: 1.0
 */
public class RegisterFailedException extends RuntimeException {
    /**
     * description:序列化操作
     * create time: 22:08 2020/5/8
     */
    private static final long serialVersionUID = 1L;

    public RegisterFailedException() {
        super();
    }

    public RegisterFailedException(String message) {
        super(message);
    }

    public RegisterFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterFailedException(Throwable cause) {
        super(cause);
    }

    protected RegisterFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
