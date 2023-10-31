package com.example.demoauthorization.config;

import com.example.demoauthorization.cachedrequest.ModifyRequestBodyWrapper;
import com.example.demoauthorization.cachedrequest.RequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// tham khảo https://fullstackdeveloper.guru/2021/09/21/how-to-read-a-json-request-inside-a-spring-boot-filter/
// modify request: https://stackoverflow.com/questions/50932518/how-to-modify-request-body-before-reaching-controller-in-spring-boot
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final List<MediaType> VISIBLE_TYPES =
            Arrays.asList(
                    MediaType.valueOf("text/*"),
                    MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.valueOf("application/*+json"),
                    MediaType.valueOf("application/*+xml"));
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath().trim();
        String httpMethod = request.getMethod();
        System.out.println("servlet path = " + servletPath);
        System.out.println("http method = " + httpMethod);
        System.out.println(request.getRequestURI());
        System.out.println(request.getQueryString());
        System.out.println("Thế huynh");
        String requestUriPath  = request.getRequestURI() + "/";
        if (requestUriPath.endsWith("/")) {
            requestUriPath = requestUriPath.substring(0, requestUriPath.lastIndexOf("/"));
        }
//        if(true) {
//            filterChain.doFilter(request, response);
//            return;
//        }


//        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
        PhongRequestWrapper wrapper2 = new PhongRequestWrapper(request);

        filterChain.doFilter(wrapper2, response);
        //Wrap the request
        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) request);

//        wrapper.se

//        ContentCachingRequestWrapper contentCachingRequestWrapper = wrapRequest(request);
//Get the input stream from the wrapper and convert it into byte array
//        byte[] body = StreamUtils.copyToByteArray(wrapper.getInputStream());
//        ObjectMapper objectMapper = new ObjectMapper();

// use jackson ObjectMapper to convert the byte array to Map (represents JSON)
//        Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);
//        jsonRequest.containsKey("areaId");
//        jsonRequest.put("areaId", 3);


        // Create a custom HttpServletRequestWrapper with the modified request body
//        ModifyRequestBodyWrapper modifiedRequest = new ModifyRequestBodyWrapper(wrapper, objectMapper.writeValueAsString(jsonRequest));

//        System.out.println(jsonRequest);
//        filterChain.doFilter(wrapper, response);

//        ContentCachingRequestWrapper contentCachingRequestWrapper = wrapRequest(request);
//        this.getRequest(contentCachingRequestWrapper);
//        filterChain.doFilter(wrapper, response);
        doFilterWrapped(wrapper, wrapResponse(response), filterChain, "duyphong170195");
    }

    protected void doFilterWrapped(
            ContentCachingRequestWrapper contentCachingRequestWrapper,
            ContentCachingResponseWrapper contentCachingResponseWrapper,
            FilterChain filterChain,
            String username)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            MDC.put("duration", duration + " ms");
            MDC.put("username", username);
            MDC.put("status", String.valueOf(contentCachingResponseWrapper.getStatus()));
            String queryString = contentCachingRequestWrapper.getQueryString();
            String requestContent;
            String responseContent = "";
            MDC.put("method", contentCachingRequestWrapper.getMethod());
            if (queryString == null) {
                requestContent =
                        String.format(
                                "METHOD=%s, URI=%s",
                                contentCachingRequestWrapper.getMethod(),
                                contentCachingRequestWrapper.getRequestURI());
                MDC.put("uri", contentCachingRequestWrapper.getRequestURI());
            } else {
                requestContent =
                        String.format(
                                "METHOD=%s, URI=%s?%s",
                                contentCachingRequestWrapper.getMethod(),
                                contentCachingRequestWrapper.getRequestURI(),
                                queryString);
                MDC.put("uri", contentCachingRequestWrapper.getRequestURI() + "?" + queryString);
            }
            int contentLength = contentCachingRequestWrapper.getContentLength();
            if (contentLength > 0 && contentLength < (10 * 1024)) {
                byte[] content = contentCachingRequestWrapper.getContentAsByteArray();
                MediaType mediaType =
                        MediaType.valueOf(contentCachingRequestWrapper.getContentType());
                boolean visible =
                        VISIBLE_TYPES
                                .stream()
                                .anyMatch(visibleType -> visibleType.includes(mediaType));
                if (visible) {
                    String contentString =
                            new String(
                                    content, contentCachingRequestWrapper.getCharacterEncoding());
                    requestContent += contentString;
                    MDC.put("params", contentString);
                }
            }

            if (contentLength > 0 && contentLength < (10 * 1024)) {
                byte[] content = contentCachingResponseWrapper.getContentAsByteArray();
                String contentString =
                        new String(content, contentCachingRequestWrapper.getCharacterEncoding());
                responseContent += contentString;
                MDC.put("responseResult", contentString);
            }
            String message =
                    String.format("REQUEST=[%s], RESPONSE=[%s]", requestContent, responseContent);
//            TypeLogger.info(getClass(), LogType.TRACE, message);
            System.out.println("======================= REQUEST =======================" +  requestContent);
            contentCachingResponseWrapper.copyBodyToResponse();
            MDC.remove("duration")  ;
            MDC.remove("username");
            MDC.remove("status");
            MDC.remove("method");
            MDC.remove("uri");
            MDC.remove("params");
            MDC.remove("responseResult");
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest httpServletRequest) {
        if (httpServletRequest instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) httpServletRequest;
        } else {
            return new ContentCachingRequestWrapper(httpServletRequest);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(
            HttpServletResponse httpServletResponse) {
        if (httpServletResponse instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) httpServletResponse;
        } else {
            return new ContentCachingResponseWrapper(httpServletResponse);
        }
    }

    private void handleAuthorizationData(HttpServletRequest httpServletRequest) {
        // TODO handle GET request
        if(HttpMethod.GET.equals(httpServletRequest.getMethod())) {
            String query = httpServletRequest.getQueryString();

        }

        // TODO handle POST AND PUT AND DELETE request
    }

}