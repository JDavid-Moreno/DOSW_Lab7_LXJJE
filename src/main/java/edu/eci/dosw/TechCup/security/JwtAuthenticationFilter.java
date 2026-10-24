package edu.eci.dosw.TechCup.security;
import edu.eci.dosw.TechCup.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /*
    Preguntas

    a) Quees http.csrf() y porque se desabilita?
    csrf (Cross Site Request Forgery) es una proteccion que evita que alguien haga peticiones
    en nombre de un usuario autenticado sin que el se de cuenta, este se desabilita porque el 
    se esta usando JWT, por lo que no usamos sesiones ni cookies para autenticacion, por lo que
    no hay riesgo de CSRF.
    
    b) para que sirve la siguiente linea de codigo?
    http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/auth/**").permitAll()
    .anyRequest().authenticated()
    )
    Esta linea de codigo permite que todos los usuarios puedan acceder a la ruta /auth/** 
    y se exige autenticacion para cualquier otro endpoint.

    c) para que sirve la siguiente linea de codigo?
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    se agrega el filtro JWT antes de que el filtro de autenticacion de spring security,
    esto es para validar el token en cada request antes de que el framework procese la seguridad.
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response,
                                    FilterChain filterChain) 
                                    throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        //1. validar si existe el header de autenticacion
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //2. extraer el token
        String token = authHeader.substring(7);

        //3. extraer el username
        String username = jwtService.extractUsername(token);

        //4. validar si no esta autenticado aun
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //validacion basica
            if (jwtService.extractUsername(token).equals(userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //5. registrar usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }           
}
