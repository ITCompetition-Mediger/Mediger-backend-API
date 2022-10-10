//package com.cos.mediAPI.login2;
//
//import java.util.Map;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//public class JwtUserDetails implements UserDetails, OAuth2User {
//
//    private User user;
//
//    private Map<String, Object> attributes;
//
//    public JwtUserDetails(User user) {
//        super();
//        this.user = user;
//    }
//
//    public JwtUserDetails(User user, Map<String, Object> attributes) {
//        this.user = user;
//        this.attributes = attributes;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//}
