//package com.cos.mediAPI.login2;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final UserRepository userRepository;
//
//    private final JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("attributes" + super.loadUser(userRequest).getAttributes());
//        OAuth2User user = super.loadUser(userRequest);
//		//여기서 attriutes를 찍어보면 모두 각기 다른 이름으로 데이터가 들어오는 것을 확인할 수 있다. 
//        try{
//            return process(userRequest, user);
//        } catch (AuthenticationException ex){
//            throw new OAuth2AuthenticationException(ex.getMessage());
//        } catch (Exception ex){
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//    }
//
//	//인증을 요청하는 사용자에 따라서 없는 회원이면 회원가입, 이미 존재하는 회원이면 업데이트를 실행한다. 
//    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
//        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
//		//provider타입에 따라서 각각 다르게 userInfo가져온다. (가져온 필요한 정보는 OAuth2UserInfo로 동일하다)
//        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
//
//        User savedUser = userRepository.findByEmail(userInfo.getEmail());
//
//        if (savedUser != null) {
//            if (providerType != savedUser.getProviderType()) {
//                throw new OAuthProviderMissMatchException(
//                        "Looks like you're signed up with " + providerType +
//                                " account. Please use your " + savedUser.getProviderType() + " account to login."
//                );
//            }
//            updateUser(savedUser, userInfo);
//        } else {
//            savedUser = createUser(userInfo, providerType);
//        }
//        System.out.println(jwtTokenUtil.generateToken(userInfo.getEmail()));
//
//        return new JwtUserDetails(savedUser, user.getAttributes());
//    }
//
//	//넘어온 사용자 정보를 통해서 회원가입을 실행한다. 
//    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
//        User user = new User();
//        user.setRole(Role.USER);
//        user.setLevel(Level.Starter);
//        user.setPassword("");
//        user.setEmail(userInfo.getEmail());
//        user.setImageUrl(userInfo.getImageUrl());
//        user.setProviderType(providerType);
//
//        return userRepository.save(user);
//    }
//
//	//사용자정보에 변경이 있다면 사용자 정보를 업데이트 해준다. 
//    private User updateUser(User user, OAuth2UserInfo userInfo) {
//        if (userInfo.getFirstName() != null && !user.getFullname().equals(userInfo.getName())) {
//            user.setFirstname(userInfo.getFirstName());
//        }
//
//        if (userInfo.getLastName() != null && !user.getLastname().equals(userInfo.getLastName())){
//            user.setLastname(userInfo.getLastName());
//        }
//
//        if (userInfo.getImageUrl() != null && !user.getImageUrl().equals(userInfo.getImageUrl())) {
//            user.setImageUrl(userInfo.getImageUrl());
//        }
//
//        return user;
//    }
//}