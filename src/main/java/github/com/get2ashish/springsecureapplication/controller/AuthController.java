package github.com.get2ashish.springsecureapplication.controller;

import github.com.get2ashish.springsecureapplication.util.JWTUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWTUtility jwtUtility;
    public AuthController(JWTUtility jwtUtility){
        this.jwtUtility = jwtUtility;
    }


    @PostMapping()
    public ResponseEntity<String> createJWT(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("name","Ashish JWT");
        claims.put("role","ADMIN");
        return ResponseEntity.ok(jwtUtility.createJWTToken(claims));
    }
}
