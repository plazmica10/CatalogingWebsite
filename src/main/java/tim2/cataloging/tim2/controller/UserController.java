package tim2.cataloging.tim2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.UserDto;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // READ ALL
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> userList = userService.findAll();

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto dto = new UserDto(user);
            userDtoList.add(dto);
        }

        return ResponseEntity.ok(userDtoList);
    }

    // READ ONE
    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") Long id) {
        return userService.findOne(id);
    }

    // CREATE
    @PostMapping("")
    public User saveUser(@RequestBody User user) {
        return this.userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        User existingUser = userService.findOne(id);
        if (existingUser == null)
            return ResponseEntity.badRequest().body("User with id: " + id + " does not exist!");
        else {
            existingUser.setName(user.getName());
            userService.save(existingUser);
            return ResponseEntity.ok("Successfully updated user with id: " + id);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        if (userService.findOne(id) == null)
            return ResponseEntity.badRequest().body("User with id: " + id + " does not exist!");
        else {
            userService.delete(id);
            return ResponseEntity.ok("Successfully deleted user with id: " + id);
        }
    }
}
