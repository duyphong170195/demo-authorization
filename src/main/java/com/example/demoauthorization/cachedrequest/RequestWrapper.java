package com.example.demoauthorization.cachedrequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.example.demoauthorization.enums.ApplicationType;
import com.example.demoauthorization.utils.Const;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;


public class RequestWrapper extends ContentCachingRequestWrapper {


	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> List<T> convertJsonToListObjects(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<T>>() {});
		} catch (Exception e) {
			return null;
		}
	}
	public static <T> List<T> convertByteToListObjects(byte[] json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<T>>() {});
		} catch (Exception e) {
			return null;
		}
	}
	private String body;
	private ApplicationType applicationType;
	private List<Long> areaIds;
	private List<Long> blockIds;


	public RequestWrapper(HttpServletRequest request, List<Long> areaIds, List<Long> blockIds, ApplicationType applicationType) throws IOException {
		super(request);
		this.applicationType = applicationType;
		if (ApplicationType.MP.equals(this.applicationType)) {
			this.areaIds = areaIds;
			this.blockIds = blockIds;

			ObjectMapper objectMapper = new ObjectMapper();
			byte[] body = StreamUtils.copyToByteArray(request.getInputStream());
			String s = new String(body, StandardCharsets.UTF_8);
			if(body.length == 0) {
				this.body = "{}";
			} else if (body.length > 1 && body[0] == 123 && body[body.length - 1] == 125){
				Map<String, Object> jsonRequest = objectMapper.readValue(body, Map.class);
				this.body = objectMapper.writeValueAsString(jsonRequest);
			} else if(body.length > 1 && body[0] == 91 && body[body.length - 1] == 93) {
				List<Object> jsonRequest = convertByteToListObjects(body, Object.class);
				this.body = objectMapper.writeValueAsString(jsonRequest);
			} else {
				this.body = "{}";
			}
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(ApplicationType.MP.equals(this.applicationType)) {
			if(Const.FILTER_CHAIN.AREA_IDS.equals(name)) {
				if(values == null) {
					if(CollectionUtils.isEmpty(this.areaIds)) {
						return null;
					} else {
						return this.areaIds.stream()
								.map(String::valueOf)
								.toArray(String[]::new);
					}
				}
			} else if(Const.FILTER_CHAIN.BLOCK_IDS.equals(name)) {
				if(values == null) {
					if(CollectionUtils.isEmpty(this.blockIds)) {
						return null;
					} else {
						return this.blockIds.stream()
								.map(String::valueOf)
								.toArray(String[]::new);
					}
				}
			} else {
				return super.getParameterValues(name);
			}
			return values;
		} else {
			return values;
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(ApplicationType.MP.equals(this.applicationType)) {

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
		} else {
			return super.getInputStream();
		}
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}