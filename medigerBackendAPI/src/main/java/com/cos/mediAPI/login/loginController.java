package com.cos.mediAPI.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins="*")
@RestController
public class loginController {
	@Autowired
	@GetMapping("/login")
	public Map<String, String> login() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("kakao", "http://localhost:8080/oauth2/authorization/kakao");
        map.put("google", "http://localhost:8080/oauth2/authorization/google");
		
        return map;
    }
}
