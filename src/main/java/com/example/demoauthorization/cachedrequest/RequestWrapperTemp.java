package com.example.demoauthorization.cachedrequest;

import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestWrapperTemp extends ContentCachingRequestWrapper {

    private byte[] body;

    public RequestWrapperTemp(HttpServletRequest request) throws IOException {
        super(request);

        this.body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamWrapper(this.body);

    }
}
