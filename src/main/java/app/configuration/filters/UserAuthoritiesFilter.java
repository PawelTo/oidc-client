package app.configuration.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class UserAuthoritiesFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Optional<BearerTokenAuthentication> authentication = authentication();
        if (authentication.isPresent() && authentication.get().isAuthenticated()) {

        }
    }

    private Optional<BearerTokenAuthentication> authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof BearerTokenAuthentication){
            return of((BearerTokenAuthentication) authentication);
        }
        return empty();
    }
}
