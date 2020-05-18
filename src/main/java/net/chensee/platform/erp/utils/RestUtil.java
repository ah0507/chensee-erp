package net.chensee.platform.erp.utils;

import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    public static String sendPostRequestWithMap(String url, Map params) {
        HttpHeaders headers = getHttpHeaders();
        String json = JsonUtil.toStr(params);
        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(url, formEntity, String.class);
    }

    public static String sendPostRequestWithString(String url, String data) {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        return restTemplate.postForObject(url, formEntity, String.class);
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.APPLICATION_JSON_UTF8;
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }

}
