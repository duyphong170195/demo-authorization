
package com.example.demoauthorization.cachedrequest;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class CustomCachingRequest extends HttpServletRequestWrapper {

	private byte[] body;

	public CustomCachingRequest(HttpServletRequest request) throws IOException {
		super(request);

		this.body = StreamUtils.copyToByteArray(request.getInputStream());
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStreamWrapper(this.body);

	}

}