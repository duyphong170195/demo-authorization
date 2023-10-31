package com.example.demoauthorization.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class PhongRequestWrapper extends HttpServletRequestWrapper {

    String body;

    public PhongRequestWrapper(HttpServletRequest request) throws IOException {
      super(request);
      String body1 = new ContentCachingRequestWrapper(request).getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        HashMap<String, Object> map = new Gson().fromJson(body1, HashMap.class);
        map.put("areaId", "2");
        this.body = new ObjectMapper().writeValueAsString(map);

        //get your dto
//      Staff staff = objectMapper.readValue(body, Staff.class);
//
//      //edit your dto
//      long facilityOwnerId = ((Number) request.getAttribute("facilityOwnerId")).longValue();
//      FacilityOwner facilityOwner = FacilityOwner.builder()
//          .facilityOwnerId(facilityOwnerId)
//          .build();
//
//      Staff modifiedStaff = Staff.builder()
//          .facilityOwner(facilityOwner)
//          .username(staff.getUsername())
//          .password(new BCryptPasswordEncoder().encode(staff.getPassword()))
//          .build();
//
//      //save your changes to body
//      this.body = objectMapper.writeValueAsString(modifiedStaff);
    }


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