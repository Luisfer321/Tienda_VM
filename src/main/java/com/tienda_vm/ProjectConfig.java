
package com.tienda_vm;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
   
    @Bean
    public LocaleResolver localeResolver(){
    
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }
    
    @Bean 
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
       registro.addInterceptor(localeChangeInterceptor());
        
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
    registro.addViewController("/").setViewName("index");
    registro.addViewController("/index").setViewName("index");
    registro.addViewController("/login").setViewName("login");
    registro.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }
    
   @Bean 
   
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
   
       http.authorizeHttpRequests((request) -> request
       .requestMatchers("/", "/index", "/js/**", "webjars/**" )
               .permitAll()      
               .requestMatchers("/producto/nuevo" , "/producto/modificar/**" , "/producto/eliminar" , "producto/guardar/**",
                       "/categoria/nuevo" , "/categoria/modificar/**" , "/categoria/eliminar" , "categoria/guardar/**",
                       "/pruebas/**")
                       .hasRole("ADMIN")
         .requestMatchers("/producto/listado"  , "/categoria/listado") 
                       .hasRole("VENDERDOR")
               
               .requestMatchers("/facturar/carrito") 
                       .hasRole("USER")
               
       ).formLogin((form) -> form
       .loginPage("login").permitAll()) 
       .logout((logout) -> logout.permitAll() );
       
       return http.build();
   }
    
}

