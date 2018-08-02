package com.vnpay.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.auth.exception.JwtTokenMissingException;
import com.vnpay.auth.util.JwtAuthenticationToken;
import com.vnpay.beans.RspObj;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

/**
 * Filter that orchestrates authentication by using supplied JWT token
 *
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    /**
     * Attempt to authenticate request - basically just pass over to another
     * method to authenticate request headers
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(this.tokenHeader);

        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Make sure the rest of the filterchain is satisfied
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        RspObj rspObj = new RspObj();
        rspObj.setCode("401");
        rspObj.setMessage("Unauthorized");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(rspObj));
    }

}
