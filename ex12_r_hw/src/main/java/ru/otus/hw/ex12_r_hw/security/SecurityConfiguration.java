package ru.otus.hw.ex12_r_hw.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration {

    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    public SecurityWebFilterChain springWebFilterChain( ServerHttpSecurity http ) {

        http
/*                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/api/user/login").permitAll()
                                .requestMatchers("/api/user/create").permitAll()
                                .anyRequest().authenticated()
                )*/
                .authorizeExchange((exchanges)->exchanges
                        .pathMatchers( HttpMethod.GET, "/api/user" ).authenticated()
                        //.pathMatchers( "/person" ).hasAnyRole( "USER" )
                        .anyExchange().authenticated()
                )

                //.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2
                        -> oauth2.jwt(Customizer.withDefaults()))
//                .sessionManagement((session)
//                        -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                ;

        return http.build();

/*
        return http
                .authorizeExchange((exchanges)->exchanges
                        .pathMatchers( HttpMethod.GET, "/authenticated.html" ).authenticated()
                        .pathMatchers( "/person" ).hasAnyRole( "USER" )
                        .anyExchange().permitAll()
                )
                .formLogin( Customizer.withDefaults())
                .build();

 */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername( "user1" )
                .password( "1" )
                .roles( "USER" )
                .build();
        return new MapReactiveUserDetailsService( user );
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withPublicKey(this.key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

/*
    @Bean
    public JwtEncoder jwtEncoder() {
        RSAKey rsaKey = new RSAKey.Builder(this.keyPair.getPublic())
                .privateKey(this.keyPair.getPrivate())
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new com.nimbusds.jose.jwk.JWKSet(rsaKey));
        return new NimbusReactiveJwtEncoder(jwks);
    }
*/
}
