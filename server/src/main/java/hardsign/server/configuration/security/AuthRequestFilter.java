package hardsign.server.configuration.security;

import hardsign.server.services.TokenProvider;
import hardsign.server.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthRequestFilter extends OncePerRequestFilter  {
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public AuthRequestFilter(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        String nickname = null;
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            nickname = tokenProvider.getNicknameFromToken(token);
        }

        if (nickname != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userService.loadUserByUsername(nickname);

            if (tokenProvider.validateToken(token, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
