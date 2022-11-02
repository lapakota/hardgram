package hardsign.server.configuration;

import hardsign.server.configuration.security.AuthEntryPoint;
import hardsign.server.configuration.security.AuthRequestFilter;
import hardsign.server.services.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoderService passwordEncoderService;
    private final AuthEntryPoint authEntryPoint;
    private final UserDetailsService userDetailsService;
    private final AuthRequestFilter authRequestFilter;

    public WebSecurityConfiguration(
            PasswordEncoderService passwordEncoderService,
            AuthEntryPoint authEntryPoint,
            UserDetailsService userDetailsService,
            AuthRequestFilter authRequestFilter) {
        this.passwordEncoderService = passwordEncoderService;
        this.authEntryPoint = authEntryPoint;
        this.userDetailsService = userDetailsService;
        this.authRequestFilter = authRequestFilter;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderService.getEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        var permitAllUrls = new String[] {
                "/registration",
                "/auth/*",
        };

        httpSecurity
                .csrf().disable()
                .authorizeRequests().antMatchers(permitAllUrls).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
