package com.example.mockito.unirest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class Request {

    @Autowired
    RequestUtil requestUtil = new RequestUtil();

    @Test
    public void test() throws UnirestException, JsonProcessingException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://app.fitoneapp.com/appV3/4146/mg/place/addOrder")
                .header("Host", " app.fitoneapp.com")
                .header("Connection", " keep-alive")
                .header("Accept", " application/json, text/plain, */*")
                .header("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat")
                .header("Content-Type", " application/x-www-form-urlencoded")
                .header("Sec-Fetch-Site", " same-origin")
                .header("Sec-Fetch-Mode", " cors")
                .header("Sec-Fetch-Dest", " empty")
                .header("Referer", " https://app.fitoneapp.com/app/v3/place/placeSubRandList/2")
                .header("Accept-Encoding", " gzip, deflate, br")
                .header("Accept-Language", " en-us,en")
                .header("Cookie", "clubIdV3=4146; plat=wxMiniProgram; clubId=4146; userPhone=13396429387; PHPSESSID=3a100667ec39dbec1a2b005f71335025; fitone_sess_v3=eeb9Vq7pBo8dNmBikWaFJaroidJUuMYQY1eShoYQT4hrJ%2B98v6%2B1aMPAXt8r; clubIdV3=4146; fitone_sess_v3=1fd7UCUQR8S64hEq9ai5xzjgh2oDjKwPqGVc7pBcNRdsqG8DrWEjmaSGn1Mm; userPhone=13396429387")
                .field("placeId", "1639988691662689")
                .field("type", "2")
                .field("subDate", "2022-04-01")
                .field("subTime", "19:00")
                .field("endTime", "21:00")
                .field("subHours", "1")
                .field("title", "1?????????")
                .asString();
        requestUtil.printMessage(response,"message");
    }
}
