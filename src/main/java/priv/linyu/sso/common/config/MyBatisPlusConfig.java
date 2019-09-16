package priv.linyu.sso.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * 
 * @className: MyBatisPlusConfig
 * @description: MyBatisPlus配置类  
 * @author QiuShangLin
 * @date: 2019/09/16 17:46
 * @version V1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "priv.linyu.sso.core.mapper")
public class MyBatisPlusConfig {

	/**
     * 物理分页 插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
    	PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        
        return page;
    }

    /**
     * 乐观锁 插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    
    
}
