package github.com.get2ashish.springsecureapplication.security;

import github.com.get2ashish.springsecureapplication.util.JWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility;

    public JWTRequestFilter(JWTUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Once per request invoked");
        var jwtToken  = request.getHeader("Authorization");
        if(Objects.nonNull(jwtToken)) {
            try {
                if (jwtUtility.isTokenValid(jwtToken)) {
                    String userAuthority = jwtUtility.getUserRole(jwtToken);
                    String userName = jwtUtility.getUserName(jwtToken);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userName, null, AuthorityUtils.createAuthorityList(userAuthority));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }catch (Exception e){
                log.error("Exception details ",e.getMessage(),e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
