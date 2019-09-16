package priv.linyu.sso.common.shiro;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @className: ShiroSessionIdGenerator
 * @author: QiuShangLin
 * @description: 自定义sessionId生成器
 * @date: 2019/09/16 19:49
 * @version: 1.0
 **/
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * 生成sessionId
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("%s",sessionId.toString().replace("-","") + RandomStringUtils.random(100,true,true));
    }
}
