package ru.itis.pdfgenerator.authorization.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.*;
import ru.itis.pdfgenerator.authorization.service.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtBlackListService jwtBlackListService;

	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String token = request.getHeader("X-TOKEN");

		if (token != null && !jwtBlackListService.exists(token)) {
			TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
		}

		filterChain.doFilter(request, response);
	}
}

