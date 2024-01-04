package com.blog.Blogger.controller;

import com.blog.Blogger.entity.Role;
import com.blog.Blogger.entity.User;
import com.blog.Blogger.payload.JWTAuthResponse;
import com.blog.Blogger.payload.LoginDto;
import com.blog.Blogger.payload.SignUpDto;
import com.blog.Blogger.repository.RoleRepository;
import com.blog.Blogger.repository.UserRepository;
import com.blog.Blogger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private UserRepository userRepository;

 @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
    private RoleRepository roleRepository;
@Autowired
private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if (userRepository.existsByEmail(signUpDto.getEmail())){
return new ResponseEntity<>("Email id exists "+signUpDto.getEmail(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username id exists "+signUpDto.getUsername(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
User user =new User();
user.setName(signUpDto.getName());
user.setEmail(signUpDto.getEmail());
user.setUsername(signUpDto.getUsername());
user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByRolename("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));//Collections.singleton() will convert role object to set

        User savedUser = userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);


    }
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
    public void test(){}

    // sign in       User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()).get();
//        if(loginDto.getUsernameOrEmail().equals(user.getEmail())&& loginDto.getPassword().equals(user.getPassword())){
//
//        }else{
//            traditional way
//        }
//authenticate method is used to compare expected value and original value

}
/*
first process starts from signin feature,after verifing username and password ,it will call
generateToken(authenticate) for current user.this method is present inside jwtToeknProvider
 class,during generating token it will
pick secretkey and expiration time from properties file and return back to AuthContoller class


 */
/*
what are the classes involved in jwt token?

jwt token is alternate way to erform login. i implimented in my project in signin feature ,
once user signed in ,it eill genrate token
i developed a jwtTokenProvider class ,in that class i have generateToken() method.
once user signed in and username password validated,i will supply these details to the
 generateToken() method
and that method will generate a token basedon the secret key and expiration time  and
give back to controller class
for the subsequent request that user sending along with request i need to send jwt token.
then we have jwtAuthenticationFilter class and in that class we have method doFilterInternal()
then doFilterInternal  method will extract the token from the request and
validate the token by apping secretkey
if ti is valid token it eill then set the details in the securityCOntext and
 let that request processed.
but in casse if then toekn is invalid then i would get and exception
which is emplements from AuthenticationEnryPoint that is unauthorized exception
 */
