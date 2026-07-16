package br.com.capoeiramundial.config;
import br.com.capoeiramundial.repository.AdminUserRepository; import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(AdminUserRepository repo){return username->repo.findByUsername(username).map(u->User.withUsername(u.getUsername()).password(u.getPassword()).roles("ADMIN").disabled(!u.isEnabled()).build()).orElseThrow(()->new UsernameNotFoundException("Usuario nao encontrado"));}
 @Bean SecurityFilterChain security(HttpSecurity http)throws Exception{return http.authorizeHttpRequests(a->a.requestMatchers("/admin/login","/css/**","/js/**","/images/**","/uploads/**","/error").permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()).formLogin(f->f.loginPage("/admin/login").defaultSuccessUrl("/admin",true).permitAll()).logout(l->l.logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login?logout")).build();}
}
