package com.example.oauth2_0example.controller;

import com.example.oauth2_0example.helper.WebHelper;
import com.example.oauth2_0example.model.dto.NaverUserDto;
import com.example.oauth2_0example.model.dto.UserDTO;
import com.example.oauth2_0example.model.vo.UserVO;
import com.example.oauth2_0example.oauth.NaverOAuth;
import com.example.oauth2_0example.service.ServiceImpl.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;


@Controller
public class MainController {

    @Autowired
    WebHelper web;

    @Autowired
    UserService userService;
    @GetMapping("/login")
    public ModelAndView login(Model model) throws Exception {
        String clientId = "nMUNpsgNjmad0FDEnocF";//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode("http://localhost:8080/main", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;
        model.addAttribute("state", state);
        model.addAttribute("APIURL",apiURL);
        return new ModelAndView("login");

    }

    @GetMapping("/")
    public String GotoMain() { return "redirect:/login";}

    @GetMapping("/main")
    public ModelAndView Main(Model model, HttpServletResponse response, RedirectAttributes rttr) throws Exception{
        web.init(response);
        String clientId = "nMUNpsgNjmad0FDEnocF";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "b3Y4hXHA1I";//애플리케이션 클라이언트 시크릿값";
        String code = web.getString("code", null);
        String state = web.getString("state", null);
        String redirectURI = URLEncoder.encode("http://localhost:8080/NaverResult", "UTF-8");
        String apiURL;
        JSONObject jsonObject = null;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        System.out.println("apiURL=" + apiURL);
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                System.out.println(res.toString());
                jsonObject = new JSONObject(res.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        NaverUserDto naverUserDto = null;
        try {
            NaverOAuth naverOAuth = new NaverOAuth(jsonObject.getString("access_token"));
            naverUserDto = new NaverUserDto(naverOAuth.get());
        } catch (JSONException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:login");
        }

        rttr.addFlashAttribute("naverUserDto",naverUserDto);


        if(userService.findByUserId(naverUserDto.getEmail()).isPresent()) {
            return new ModelAndView("main");
        } else {
            return new ModelAndView("redirect:login/signup");
        }
    }


    @GetMapping("/login/signup")
    public ModelAndView signup(@ModelAttribute NaverUserDto naverUserDto, Model model, HttpServletResponse response) {
        web.init(response);
        model.addAttribute("NaverUserData",naverUserDto);
        return new ModelAndView("signup");
    }

    @PostMapping("login/signup/post")
    public void signupPost(@RequestBody UserDTO userDTO) {
        if(userDTO.getUser_type().isEmpty()) {
            userDTO.setUser_type("NONE");
        }
        try {
            userService.SignUp(userDTO);
        } catch (Exception e) {

        }
    }
}
