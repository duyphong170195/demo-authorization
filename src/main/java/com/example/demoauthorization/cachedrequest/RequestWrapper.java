package com.example.demoauthorization.cachedrequest;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestWrapper extends ContentCachingRequestWrapper {

	private byte[] body;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		this.body = StreamUtils.copyToByteArray(request.getInputStream());
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStreamWrapper(this.body);

	}

}