package com.mai.projects.plm.security.jwt;

import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String header = httpServletRequest.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
			try {
				if (token != null && jwtTokenProvider.validateToken(token)) {
					log.info("valid Token");

					Authentication auth = jwtTokenProvider.getAuthentication(token);

					if (auth != null) {
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (JwtException e) {

			}
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);


		}
	}
}
