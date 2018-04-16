package com.z.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * created by zjw
 * 2018/4/15
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyLogoutHandler myLogoutHandler;
    
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
//    @Autowired
//    private FilterSecurityInterceptor filterSecurityInterceptor;
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // javaconfig 配置是这样 set 进去的.
//        web.securityInterceptor(filterSecurityInterceptor);
//        web.privilegeEvaluator(new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor));
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**","/index","/").permitAll()
                    .antMatchers("/user/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                .logout()
//                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/index")
//                    .logoutSuccessHandler(myLogoutSuccessHandler)
                    .invalidateHttpSession(true)
//                    .addLogoutHandler(myLogoutHandler)
//                    .deleteCookies(cookieNamesToClear)
                .and()
                .formLogin();
//                .loginPage("/login")
//                .permitAll();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
    }
}
