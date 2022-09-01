package com.htphuoc.bookstore.security;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.htphuoc.bookstore.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {

		ErrorDetails errorDetails = new ErrorDetails();

		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setStatus(HttpStatus.FORBIDDEN.value());
		errorDetails.setError("FORBIDDEN");
		errorDetails.setMessage("You don't have permission to access this resource");
		errorDetails.setPath(request.getRequestURI());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, errorDetails);

		out.close();
	}

}
