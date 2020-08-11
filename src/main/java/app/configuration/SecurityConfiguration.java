package app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Profile("oidc-client")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilterAfter()
                .csrf().disable()
                //.formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                //.requestMatchers(unsecuredRequestMatcher()).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .oauth2Login()
        ;
    }
}
