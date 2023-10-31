package com.example.demoauthorization.cachedrequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestWrapper extends ContentCachingRequestWrapper {


	private String body;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		byte[] body = StreamUtils.copyToByteArray(request.getInputStream());
		ObjectMapper objectMapper = new ObjectMapper();

// use jackson ObjectMapper to convert the byte array to Map (represents JSON)
		Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);

		jsonRequest.put("areaId", "2");

		this.body = new ObjectMapper().writeValueAsString(jsonRequest);
	}

//	@Override
//	public ServletInputStream getInputStream() throws IOException {
//		return new ServletInputStreamWrapper(this.body);
//
//	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream() {
			public int read() {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener listener) {

			}
		};
		return servletInputStream;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}