package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.domain.VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/quick23")
    @ResponseBody
    public void save23(String username, MultipartFile[] uploadFile) throws IOException {
        System.out.println(username);
        for(MultipartFile multipartFile : uploadFile){
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("G:\\IdeaProjects\\spring-code\\itheima_spring_mvc\\upload\\"+originalFilename));
        }
    }

    @RequestMapping(value = "/quick22")
    @ResponseBody
    public void save22(String username, MultipartFile uploadFile, MultipartFile uploadFile2) throws IOException {
        System.out.println(username);
        //获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("G:\\IdeaProjects\\spring-code\\itheima_spring_mvc\\upload\\"+originalFilename));
        String originalFilename2 = uploadFile2.getOriginalFilename();
        uploadFile.transferTo(new File("G:\\IdeaProjects\\spring-code\\itheima_spring_mvc\\upload\\"+originalFilename2));

    }

    @RequestMapping(value = "/quick21")
    @ResponseBody
    public void save21(@CookieValue(value = "JSESSIONID") String jsessionId) throws IOException {
        System.out.println(jsessionId);
    }

    @RequestMapping(value = "/quick20")
    @ResponseBody
    public void save20(@RequestHeader(value = "User-Agent",required = false) String user_agent) throws IOException {
        System.out.println(user_agent);
    }

    @RequestMapping(value = "/quick19")
    @ResponseBody
    public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }

    @RequestMapping(value = "/quick18")
    @ResponseBody
    public void save18(Date date) throws IOException {
        System.out.println(date);
    }

    // localhost:8080/user/quick17/zhangsan（get方式请求）
    @RequestMapping(value = "/quick17/{username}")
    @ResponseBody
    public void save17(@PathVariable(value = "username") String username) throws IOException {
        System.out.println(username);
    }

    @RequestMapping(value = "/quick16")
    @ResponseBody
    public void save16(@RequestParam(value="name", required = false, defaultValue = "itcast") String username) throws IOException {
        System.out.println(username);
    }

    @RequestMapping(value = "/quick15")
    @ResponseBody
    public void save15(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }

    @RequestMapping(value = "/quick14")
    @ResponseBody
    public void save14(VO vo) throws IOException {
        System.out.println(vo);
    }

    @RequestMapping(value = "/quick13")
    @ResponseBody
    public void save13(String[] strs) throws IOException {
        System.out.println(Arrays.asList(strs));
    }

    @RequestMapping(value = "/quick12")
    @ResponseBody
    public void save12(User user) throws IOException {
        //控制台打印
        System.out.println(user);
    }

    @RequestMapping(value = "/quick11")
    @ResponseBody
    // 客户端请求任何参数都是字符串形式，SpringMVC会自动将int age 转换成String age（数据类型转换）
    // void 表示不回写任何数据
    public void save11(String username, int age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping(value = "/quick10")
    @ResponseBody
    //期望SpringMVC自动将User转换成json格式的字符串
    public User save10() throws IOException {
        User user = new User();
        user.setUsername("lisi2");
        user.setAge(32);
        return user;
    }

    @RequestMapping(value = "/quick9")
    @ResponseBody
    public String save9() throws IOException {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json的转换工具将对象转换成json格式字符串再返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    @RequestMapping(value = "/quick8")
    @ResponseBody
    public String save8() throws IOException {
        return "{\"username\":\"zhangsan\",\"age\":18}";//{}内部的字符串要进行相应的转义
    }

    @RequestMapping(value = "/quick7")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应（数据以http响应体的方式回写）
    public String save7() throws IOException {
        return "hello itheima";
    }

    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello itcast");
    }

    @RequestMapping(value = "/quick5")
    //javaWeb（Tomcat）产生，不常用
    //调用者（SpringMVC）负责传入实参
    public String save5(HttpServletRequest request) {
        request.setAttribute("username", "酷丁鱼");
        return "success";//视图跳转，返回一个逻辑视图名称
    }

    @RequestMapping(value = "/quick4")
    //SpringMVC封装，与javaWeb阶段的servelet相关api解耦合
    public String save4(Model model) {
        model.addAttribute("username", "博学谷");
        return "success";//视图跳转，返回一个逻辑视图名称
    }

    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView) {
        //设置模型数据
        modelAndView.addObject("username", "itheima");
        //设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick2")
    public ModelAndView save2() {
        /*
            Model:模型 作用封装数据
            View: 视图 作用展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username", "itcast");
        //设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // 请求地址  http://localhost:8080/user/quick
    @RequestMapping(value = "/quick", method = RequestMethod.GET, params = {"username"})
    public String save() {
        System.out.println("Controller save running....");
        return "success";
    }

}
