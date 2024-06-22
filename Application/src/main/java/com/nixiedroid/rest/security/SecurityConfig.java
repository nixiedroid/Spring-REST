//package com.nixiedroid.rest.security;
//
//import com.nixiedroid.rest.interfaces.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
////@EnableWebSecurity
//@ComponentScan
//public class SecurityConfig /* extends WebSecurityConfigurerAdapter */ {
//    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
////    private final UserRepository userRepository;
////
////    //@Autowired
////    public SecurityConfig(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new UserService(userRepository);
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////      //  authProvider.setUserDetailsService(userDetailsService());
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////        return authenticationConfiguration.getAuthenticationManager();
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
//////                //Some magic stuff
//////                .csrf().disable()
//////                //require authorisation
////               .authorizeRequests()
////                //add required roles here
////                //Registration related
////                .antMatchers("/register").hasAnyAuthority(Roles.ANONYMOUS)
////                .antMatchers("/registerSuccess").hasAnyAuthority(Roles.ANONYMOUS)
////                .antMatchers("/registerProcess")
////                .hasAnyAuthority(Roles.ANONYMOUS)
////                //Product list related
////                .antMatchers("/productsList").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/productsListAdd20").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/productsListSave").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/productsListDelete").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/productsListFind").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/productsListSaveProcess").hasAnyAuthority(Roles.EDITOR)
////                //User Products list related
////                .antMatchers("/userProductsList").hasAnyAuthority(Roles.USER)
////                .antMatchers("/userProductsListFind").hasAnyAuthority(Roles.USER)
////
////                //User list related
////                .antMatchers("/usersList").hasAnyAuthority(Roles.EDITOR)
////                //.antMatchers("/usersListAdd").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/usersListDelete").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/usersListSaveProcess").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/usersListSave").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/usersListEdit").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/usersListFind").hasAnyAuthority(Roles.EDITOR)
////                //Admin page
////                .antMatchers("/admin").hasAnyAuthority(Roles.ADMIN)
////                .antMatchers("/adminEncrypt").hasAnyAuthority(Roles.ADMIN)
////                //Cart page
////                .antMatchers("/cart").hasAnyAuthority(Roles.USER)
////                .antMatchers("/cartOrder").hasAnyAuthority(Roles.USER)
////                .antMatchers("/cartDelete").hasAnyAuthority(Roles.USER)
////                .antMatchers("/cartEdit").hasAnyAuthority(Roles.USER)
////                .antMatchers("/buy").hasAnyAuthority(Roles.USER)
////                //User page
////                .antMatchers("/user").hasAnyAuthority(Roles.USER)
////                .antMatchers("/userDelete").hasAnyAuthority(Roles.USER)
////                .antMatchers("/userEdit").hasAnyAuthority(Roles.USER)
////                //Orders pages
////                .antMatchers("/orders").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/orderDelete").hasAnyAuthority(Roles.EDITOR)
////                .antMatchers("/myOrders").hasAnyAuthority(Roles.USER)
////                .antMatchers("/usersListSave").hasAnyAuthority(Roles.EDITOR)
////                //Public pages
////                .antMatchers("/").permitAll()
////                .antMatchers("/error").permitAll()
////                .antMatchers("/styles.css").permitAll()
////                //blacklist others
////                .anyRequest().hasAnyAuthority(Roles.ADMIN)
////                //and
////                .and().formLogin().defaultSuccessUrl("/").permitAll()
////                //and
////                .and().logout().permitAll().logoutSuccessUrl("/")
////                //and
////                .and().exceptionHandling().accessDeniedPage("/403")
////
////        ;
////        return http.build();
//        return null;
//    }
//}