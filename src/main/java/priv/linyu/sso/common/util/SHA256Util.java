package priv.linyu.sso.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @className: SHA256Util
 * @author: QiuShangLin
 * @description: 密码加密工具类
 * @date: 2019/09/16 17:54
 * @version: 1.0
 **/
public class SHA256Util {

    /**
     * 私有构造器
     */
    private SHA256Util() {

    }

    /**
     * 加密的算法
     */
    public final static String HASH_ALGORITHM_NAME = "SHA-256";

    /**
     * 加密的次数
     */
    public final static int HASH_ITERATIONS = 15;

    /**
     * 执行加密
     * @param password
     * @param salt
     * @return
     */
    public static String sha256(String password,String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME,password,salt,HASH_ITERATIONS).toString();
    }

    public static void main(String[] args) {

        String password = sha256("test","OVlrD37bDUKNcFRB10qG");
        System.out.println(password);
    }


}
