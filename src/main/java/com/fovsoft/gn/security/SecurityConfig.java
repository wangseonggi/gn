package com.fovsoft.gn.security;


import com.fovsoft.gn.security.component.CustomAccessDecisionManager;
import com.fovsoft.gn.security.component.CustomMetadataSource;
import com.fovsoft.gn.security.filter.CustomSecurityInterceptor;
import com.fovsoft.gn.security.handler.CustomAccessDeniedHandler;
import com.fovsoft.gn.security.handler.CustomAuthenticationEntryPoint;
import com.fovsoft.gn.security.handler.CustomFailureHandler;
import com.fovsoft.gn.security.handler.CustomSuccessHandler;
import com.fovsoft.gn.security.session.CustomSessionInformationExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@EnableWebSecurity
//@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService customUserDetailsService;

    @Autowired
    CustomMetadataSource customMetadataSource;
    /**
     * Http安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 关闭csrf
         */
        http
                .csrf().disable();

        /**
         * 配置自定义权限过滤器
         */
        http
                .addFilterAt(getCustomSecurityInterceptor() , FilterSecurityInterceptor.class);

        /**
         * 表单登录：使用默认的表单登录页面和登录端点/login进行登录
         * 登出：使用默认的退出登录端点/logout退出登录
         * 权限：除了/toHome和/toUser之外的其它请求都要求用户已登录
         */
        http
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(getCustomSuccessHandler())


//                    .failureHandler(getCustomFailureHandler())
//                    .permitAll()
//                    .successForwardUrl("/index")
//                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/toHome", "/toUser")
                    .permitAll()

                    .antMatchers("/test/foo")
                    .permitAll()

                    .antMatchers("/xzqhdm/get")
                    .permitAll()

                    .antMatchers("/query/**")
                    .permitAll()


                    .antMatchers("/fun1/**","/family")
                    .hasAnyRole("admin")

                    .antMatchers("/income")
                    .hasAnyRole("user11")

                    .anyRequest()
                    .authenticated()
                .and()
                .sessionManagement()
                //设置session失效的跳转页面
                .invalidSessionUrl("/login")
                //设置最大session数为1
                .maximumSessions(1)
                //设置过期策略
                .expiredSessionStrategy(getCustomSessionInformationExpiredStrategy());


        /**
         * 自定义认证及授权异常处理
         */
        http
                .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint()); // 配置EntryPoint会禁用默认登录页

        /**
         * iframe页面的地址只能为同源域名下的页面
         */
        http
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 已完成历史使命
        /*auth
                .inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin22").password("123456").roles("admin")
                .and()
                .withUser("user11").password("123456").roles("user");*/

        // 加入自定义的认证提供者
        auth
                .authenticationProvider(daoAuthenticationProvider());
    }

    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**");

        // 使 sec:authorize-url 生效
        web.privilegeEvaluator(new DefaultWebInvocationPrivilegeEvaluator(getCustomSecurityInterceptor()));
    }

    /**
     * 使用security自带的provider
     */
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);

        return daoAuthenticationProvider;
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public CustomSecurityInterceptor getCustomSecurityInterceptor() {
        CustomSecurityInterceptor interceptor = new CustomSecurityInterceptor();
        interceptor.setAccessDecisionManager(new CustomAccessDecisionManager());
        interceptor.setSecurityMetadataSource(customMetadataSource);
        try {
            interceptor.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interceptor;
    }

    /**
     * 登录成功处理器
     */
    @Bean
    public CustomSuccessHandler getCustomSuccessHandler() {
        return new CustomSuccessHandler();
    }

    /**
     * 登录失败处理器
     */
    @Bean
    public CustomFailureHandler getCustomFailureHandler() {
        return new CustomFailureHandler();
    }


    @Bean
    public CustomSessionInformationExpiredStrategy getCustomSessionInformationExpiredStrategy(){
        return new CustomSessionInformationExpiredStrategy();
    }


    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return  sessionRegistry;
    }
}
