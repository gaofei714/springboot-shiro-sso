package priv.linyu.sso.common.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className: SwaggerConfig
 * @author: QiuShangLin
 * @description: Swagger配置类,访问：localhost:8001/swagger-ui.html
 * @date: 2019/09/16 22:34
 * @version: 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建restful风格
     * @return
     */
    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                protocols(Sets.newHashSet("http", "https")).
                select().
                apis(RequestHandlerSelectors.basePackage("priv.linyu.sso.controller")).
                paths(PathSelectors.any()).
                build();
    }

    /**
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("SSO单点登录,使用swagger2构建api文档").
                description("简单优雅的restfun风格").
                version("1.0").
                build();
    }

}
