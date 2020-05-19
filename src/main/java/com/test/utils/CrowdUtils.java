package com.test.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.utils
 * @ClassName: CrowdUtils
 * @Author: ZhangJunjie
 * @Description: 工具方法类
 * @Date: 2020/5/7 16:43
 * @Version: 1.0
 */
public class CrowdUtils {
    /**
     * description: 进行MD5加密
     * create time: 16:51 2020/5/8
     *
     * @param source 加密内容
     * @return 加密结果
     */
    public static String toMd5(String source) {
        // 判断source是否有效
        if (source == null || "".equals(source)) {
            // 抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        } else {
            // 设置算法规则
            String algorithm = "md5";
            try {
                // 获取MessageDigest对象
                MessageDigest digest = MessageDigest.getInstance(algorithm);
                // 获取明文字符串对应的字节数组
                byte[] input = source.getBytes();
                // 执行加密
                byte[] output = digest.digest(input);
                // 设置正负数
                int sigNum = 1;
                // 创建BigInteger对象
                BigInteger bigInteger = new BigInteger(sigNum, output);
                // 设置进制
                int radix = 16;
                // 按照16进制把bigInteger转换为字符串
                return bigInteger.toString(radix);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * description: 判断是否为AJAX请求
     * create time: 16:54 2020/5/7
     */
    public static boolean isJson(HttpServletRequest request) {
        //获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        return (acceptHeader != null && acceptHeader.contains("application/json")) ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }
}
