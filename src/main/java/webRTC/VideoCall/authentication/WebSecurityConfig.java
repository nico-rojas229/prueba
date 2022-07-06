package webRTC.VideoCall.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configuracion de seguridad para permitir acceso total
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new FilterCors(), ChannelProcessingFilter.class);
        http.authorizeRequests().antMatchers("/").permitAll().anyRequest().permitAll().and().httpBasic().and()
                .csrf().disable();
    }

    @Configuration
    @Order(1)
    public static class RegisterApiKeyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${security.http.auth.apiKeyName}")
        private String principalRequestHeader;

        @Value("${security.http.auth.apiKeyValue}")
        private String principalRequestValue;

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            APIKeyAuthFilter apiKeyFilter = new APIKeyAuthFilter(principalRequestHeader);
            validateApiKey(apiKeyFilter, principalRequestValue);

            http
                    .csrf().disable().requestMatchers()
                    .antMatchers("/api/upload").and()
                    .addFilter(apiKeyFilter).authorizeRequests().anyRequest().authenticated().and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

            http.headers().cacheControl().disable();

        }

        private void validateApiKey(APIKeyAuthFilter apiKeyFilter, String principalRequestValue) {
            apiKeyFilter.setAuthenticationManager(new AuthenticationManager() {

                @Override
                public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                    String principal = (String) authentication.getPrincipal();
                    if (!principalRequestValue.equals(principal)) {
                        throw new BadCredentialsException("The API key was not found or not the expected value.");
                    }
                    authentication.setAuthenticated(true);
                    return authentication;
                }
            });
        }
    }

}
