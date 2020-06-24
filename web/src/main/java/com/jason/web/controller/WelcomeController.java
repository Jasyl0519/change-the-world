package com.jason.web.controller;

import com.jason.web.config.AuthorSettings;
import com.jason.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 欢迎接口
 * @author jasonz
 */
@RequestMapping("/welcome")
@RestController
public class WelcomeController {

    @Autowired
    private AuthorSettings authorSettings;

    /**
     * hello
     *
     * @param name
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/hello")
    public String index(@RequestParam String name) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "Hello start");
        Thread.sleep(10000);
        System.out.println(System.currentTimeMillis() + "Hello end");
        return "Hello Spring Boot1 " + authorSettings.getName() + "_" + authorSettings.getAge();
    }

    /**
     * detail
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/detail")
    public Response<UserVo> getDetail(@RequestParam String id) {

        UserVo userVo = new UserVo();
        Response<UserVo> resp = new Response<>();
        resp.setData(userVo);
        return resp;
    }

    /**
     * 新增
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/add")
    public Response<UserVo> add(@RequestParam String id) {

        UserVo userVo = new UserVo();
        Response<UserVo> resp = new Response<>();
        resp.setData(userVo);
        return resp;
    }


}
