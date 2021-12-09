package cat.tecnocampus.ofc.security;

import cat.tecnocampus.ofc.security.jwt.JwtConfig;
import cat.tecnocampus.ofc.security.jwt.JwtTokenVerifierFilter;
import cat.tecnocampus.ofc.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private DataSource dataSource;

    private static final String USERS_QUERY = "select email, password, enabled from consumer where email = ?";
    private static final String AUTHORITIES_QUERY = "select username, role from authorities where username = ?";

    private final JwtConfig jwtConfig;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()


                .antMatchers("/", "index", "/css/**", "/fonts/**", "/scss/*", "/js/*", "/images/*", "/*.html",
                        "/*.jpg", "/*.png","/h2-console/**","/products").permitAll()

                .antMatchers( "/prodSubscription","consumer/*/prodSubscription",
                        "/consumers/*/orders", "/orders/*/*",
                        "/consumers/{\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b}/prodSubscription").hasRole("USER")

                .antMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")


                .anyRequest()
                .authenticated()

                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    //Configure authentication manager
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(passwordEncoder);
    }
}

