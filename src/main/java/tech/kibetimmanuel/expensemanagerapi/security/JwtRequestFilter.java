package tech.kibetimmanuel.expensemanagerapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.kibetimmanuel.expensemanagerapi.utils.JwtTokenUtil;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private  CustomUserDetailsService customUserDetailsService;

    @Autowired
    private  JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = "Authorization";
        final String authHeader = request.getHeader(authenticationHeader);
        String jwtToken;
        String username;
        String authenticationSchema = "Bearer ";
        if (authHeader == null || !authHeader.startsWith(authenticationSchema)) {
             filterChain.doFilter(request, response);
             return;
        }

        jwtToken = authHeader.substring(7);

        try {
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Unable to get JWT token");
        }
        catch (ExpiredJwtException e){
            throw new RuntimeException("JWT token has expired");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
    }
}
