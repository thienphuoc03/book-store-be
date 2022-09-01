package com.htphuoc.bookstore.security;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.htphuoc.bookstore.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		ErrorDetails errorDetails = new ErrorDetails();

		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
		errorDetails.setError("UNAUTHORIZED");
		errorDetails.setMessage("Please login to see this content !!!");
		errorDetails.setPath(request.getRequestURI());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, errorDetails);

		out.close();
	}
}