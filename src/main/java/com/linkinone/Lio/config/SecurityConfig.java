package com.linkinone.Lio.config;

import com.linkinone.Lio.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/open-login-modal")
                .defaultSuccessUrl("/")
                .failureUrl("/")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied")
                .and()
                .csrf()
                .ignoringAntMatchers("/ChatBoxServlet")
                .ignoringAntMatchers("/ChatListServlet")
                .ignoringAntMatchers("/ChatSubmitServlet")
                .ignoringAntMatchers("/ChatUnreadServlet")
                .ignoringAntMatchers("/ResearchAddServlet")
                .ignoringAntMatchers("/ResearchListServlet")
                .ignoringAntMatchers("/fileupload")
                .ignoringAntMatchers("/login-modal-find")
                .ignoringAntMatchers("/confirm-email")
                .ignoringAntMatchers("/user-info-modify.html");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
