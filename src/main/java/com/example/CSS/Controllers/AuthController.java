package com.example.CSS.Controllers;



import com.example.CSS.Model.User;
import com.example.CSS.Repository.UserRepository;
import com.example.CSS.Security.JwtUtil;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "Authorization"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws JOSEException {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), optionalUser.get().getUserType());

            // Prepare the response object
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", optionalUser.get().getUserType()); // Assuming userType is an int representing role

            return ResponseEntity.ok(response);
        } else {
            // Return a 401 Unauthorized response with a message
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}