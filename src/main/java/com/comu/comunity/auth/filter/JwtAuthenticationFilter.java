package com.comu.comunity.auth.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.comu.comunity.auth.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String method = request.getMethod();
		if (requestURI.contains("/api")) {
			// heade 에서 토큰정보 추출
			String token = jwtTokenProvider.extractJwtToken(request.getHeader("Authorization"));
			if (jwtTokenProvider.validateToken(token)) {

				filterChain.doFilter(request, response);

			} else {
				extracted(response);
			}

		}

		if (requestURI.contains("/auth")) {
			filterChain.doFilter(request, response);
		}

	}

	private void extracted(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
		response.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		response.setCharacterEncoding("UTF-8");
		String jsonResponse = "{\"error\": \"Invalid or expired token\"}";
		response.getWriter().write(jsonResponse); // 응답 본문에 JSON 메시지 작성
	}

}
