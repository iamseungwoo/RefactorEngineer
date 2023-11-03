package write.clean.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import write.clean.domain.User.User;
import write.clean.dtos.user.AddUserRequest;
import write.clean.service.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody AddUserRequest userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }
    /*
     * @GetMapping("/user")
     * 
     * @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
     * public ResponseEntity<User> getMyUserInfo() {
     * return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
     * }
     * 
     * @GetMapping("/user/{username}")
     * 
     * @PreAuthorize("hasAnyRole('ADMIN')")
     * public ResponseEntity<User> getUserInfo(@PathVariable String username) {
     * return ResponseEntity.ok(userService.getUserWithAuthorites(username).get());
     * }
     */
}
