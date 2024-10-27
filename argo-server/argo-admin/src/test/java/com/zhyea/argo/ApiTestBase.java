package com.zhyea.argo;

import com.zhyea.argo.constants.ResponseCode;
import org.chobit.commons.model.response.Result;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.util.Map;

import static org.chobit.commons.utils.JsonKit.fromJson;
import static org.chobit.commons.utils.JsonKit.toJson;

public abstract class ApiTestBase extends TestBase {


	@Autowired
	private TestRestTemplate restTemplate;


	protected abstract String parentPath();


	protected <T> Object testPost(String path, T param) {
		path = buildPath(path);
		System.out.println(toJson(param));
		ResponseEntity<Result> response = restTemplate.postForEntity(path, param, Result.class);
		Result w = response.getBody();
		return getResponse(w);
	}

	protected <T, R> R testPost(String path, T param, Class<R> tClass) {
		Object r = testPost(path, param);
		String json = toJson(r);
		return fromJson(json, tClass);
	}

	protected <T> Object testPut(String path, T param) {
		path = buildPath(path);
		System.out.println(toJson(param));
		ResponseEntity<Result> response = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(param), Result.class);
		Result w = response.getBody();
		return getResponse(w);
	}

	protected <T, R> R testPut(String path, T param, Class<R> tClass) {
		Object r = testPut(path, param);
		String json = toJson(r);
		return fromJson(json, tClass);
	}

	protected Object testGet(String path) {
		path = buildPath(path);
		ResponseEntity<Result> response = restTemplate.getForEntity(path, Result.class);
		Result w = response.getBody();
		return getResponse(w);
	}

	protected <T> T testGet(String path, Class<T> tClass) {
		Object r = testGet(path);
		String json = toJson(r);
		return fromJson(json, tClass);
	}


	protected Object testDelete(String path) {
		path = buildPath(path);
		ResponseEntity<Result> response = restTemplate.exchange(path, HttpMethod.DELETE, null, Result.class);
		Result w = response.getBody();
		return getResponse(w);
	}


	protected <T> T testDelete(String path, Class<T> tClass) {
		Object r = testDelete(path);
		String json = toJson(r);
		return fromJson(json, tClass);
	}


	private String buildPath(String path) {
		String p = parentPath();
		if (!p.endsWith("/") && !path.startsWith("/")) {
			return p + "/" + path;
		} else {
			return p + path;
		}
	}

	private Object getResponse(Result<?> wrapper) {
		System.out.println(toJson(wrapper));

		Assertions.assertNotNull(wrapper);
		Assertions.assertEquals(ResponseCode.SUCCESS.getCode(), wrapper.getCode());

		return null == wrapper.getData() ? "" : wrapper.getData();
	}


	private DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();

	/**
	 * 一个简单的工具方法，用来拼接get请求参数
	 *
	 * @param url    请求路径
	 * @param params 参数集合
	 * @return 含有请求参数的url
	 */
	protected String buildQueryString(String url, Map<String, Object> params) {
		UriBuilder builder = factory.uriString(url);
		for (Map.Entry<String, Object> e : params.entrySet()) {
			builder.queryParam(e.getKey(), e.getValue());
		}
		return builder.build().toString();
	}


}
