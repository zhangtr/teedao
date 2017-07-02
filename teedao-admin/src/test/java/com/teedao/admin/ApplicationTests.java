package com.teedao.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ApplicationTests {


    @Value("${local.server.port}")// 注入端口号
    private int port;
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testRole() {
        String url = "http://localhost:" + port + "/test";
        Object o = restTemplate.getForObject(url, Object.class);
        System.out.println(o.toString());
//        Assert.assertNotNull(result);
//        Assert.assertThat(result.getUsername(), Matchers.containsString("admin"));
    }
}
