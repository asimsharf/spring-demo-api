//package com.sudagoarth.demo.security;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DemoSecurityConfig {

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configure ->
//                configure
//                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//        );
//
//        http.formLogin();
//        http.httpBasic();
//        http.csrf().disable();
//        return http.build();
//    }
//}
