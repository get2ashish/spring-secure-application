package github.com.get2ashish.springsecureapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable(name = "id") String securedId) {
        return ResponseEntity.ok("User API accessed with ID " + securedId + "!");
    }
}
