package DevJang.blogbackend.web;

import DevJang.blogbackend.web.argumentResolver.LoginMemberArgumentResolver;
import DevJang.blogbackend.web.interceptor.LogInterceptor;
import DevJang.blogbackend.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1). addPathPatterns("/**").excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2).addPathPatterns("/**").
                excludePathPatterns("/", "/members/new", "/login","/logout","/error-page/*", "/css/**", "/*.ico", "/error", "/api/**");
    }
}
