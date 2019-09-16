package priv.linyu.sso.common.constant;

/**
 * @className: GlobalContant
 * @author: QiuShangLin
 * @description: 全局系统常量类
 * @date: 2019/09/16 17:38
 * @version: 1.0
 **/
public class GlobalContant {

    /**
     * 过期时间2小时
     */
    public final static Integer REDIS_EXPIRE_TWO = 7200;

    /**
     * 过期时间15分
     */
    public final static Integer REDIS_EXPIRE_EMAIL = 900;

    /**
     * 过期时间5分钟
     */
    public final static Integer REDIS_EXPIRE_KAPTCHA = 300;

    /**
     * 暂无过期时间
     */
    public final static Integer REDIS_EXPIRE_NULL = -1;
}
