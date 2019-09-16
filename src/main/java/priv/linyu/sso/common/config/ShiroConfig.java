package priv.linyu.sso.common.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.linyu.sso.common.shiro.ShiroRealm;
import priv.linyu.sso.common.shiro.ShiroSessionIdGenerator;
import priv.linyu.sso.common.shiro.ShiroSessionManager;
import priv.linyu.sso.common.util.SHA256Util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className: ShiroConfig
 * @author: QiuShangLin
 * @description:
 * @date: 2019/09/16 20:03
 * @version: 1.0
 **/
@Configuration
public class ShiroConfig {

    /**
     * IP地址
     */
    @Value("${spring.redis.host}")
    private String hostName;

    /**
     * 端口号
     */
    @Value("${spring.redis.port}")
    private int port;

    /**
     * 客户端超时时间单位是毫秒 默认是2000
     */
    @Value("${spring.redis.timeout}")
    private int timeout;


    /**
     * 配置shiro安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置session管路器
        securityManager.setSessionManager(sessionManager());
        // 设置缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        // 设置realm
        securityManager.setRealm(shiroRealm());

        return securityManager;
    }


    /**
     * shiro过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        // 创建 shiro 工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 shiro 安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/unauth");

        // 设置访问权限
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/*", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    /**
     * 自定义Realm身份验证器
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        // 设置密码匹配器
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }


    /**
     * 凭证匹配器
     * 将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        shaCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        shaCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return shaCredentialsMatcher;
    }



    /**
     * 配置session管理器
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        ShiroSessionManager manager = new ShiroSessionManager();
        manager.setSessionDAO(redisSessionDAO());
        return manager;
    }

    /**
     * 配置cache管理器
     * @return
     */
    @Bean
    public RedisCacheManager ehCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());

        // 缓存key
        String CACHE_KEY = "shiro:cache:";
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        // 配置缓存的话要求放在session里面的实体类必须有个id标识
        redisCacheManager.setPrincipalIdFieldName("userId");

        return redisCacheManager;
    }

    /**
     * 配置redis管理器
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(hostName);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * 配置RedisSessionDAO
     * @Attention 使用的是shiro-redis开源插件
     * @Author Sans
     * @CreateTime 2019/6/12 13:44
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());

        // session的key
        String SESSION_KEY = "shiro:session:";
        redisSessionDAO.setKeyPrefix(SESSION_KEY);

        // 存活时间
        int EXPIRE = 1800;
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }



    /**
     * sessionId生成器
     * @return
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator(){
        return new ShiroSessionIdGenerator();
    }



    /**
     * 开启注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor  authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
