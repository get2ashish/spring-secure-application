package github.com.get2ashish.springsecureapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable(name = "id") String publicId){
        return ResponseEntity.ok("Public API accessed with ID "+publicId+"!");
    }
}
