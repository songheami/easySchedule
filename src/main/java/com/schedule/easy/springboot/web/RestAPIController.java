package com.schedule.easy.springboot.web;

import com.schedule.easy.springboot.config.auth.LoginUser;
import com.schedule.easy.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RestAPIController {

    @GetMapping("/rest/api/v1")
    @ResponseBody
    public Map<String, Object> rest(Model model) throws Exception {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        String result = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("https://api.odsay.com/v1/api/searchPubTransPath?SX=126.9027279&SY=37.5349277&EX=126.9145430&EY=37.5499421&apiKey={API_KEY}")).GET().build(),  //GET방식 요청
                HttpResponse.BodyHandlers.ofString()  //응답은 문자형태
        ).thenApply(HttpResponse::body)  //thenApply메소드로 응답body값만 받기
                .get();  //get메소드로 body값의 문자를 확인
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        return map;
    }
}
