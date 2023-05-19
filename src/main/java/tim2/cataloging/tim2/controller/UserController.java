package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.LoginDto;
import tim2.cataloging.tim2.dto.UserDto;
import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.service.AuthorService;
import tim2.cataloging.tim2.service.BookService;
import tim2.cataloging.tim2.service.ReviewService;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return ResponseEntity.badRequest().body("Invalid login data");

        User loggedUser = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (loggedUser == null)
            return ResponseEntity.notFound().build();

        session.setAttribute("user", loggedUser);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null)
            return ResponseEntity.badRequest().body("Forbidden");

        session.invalidate();
        return ResponseEntity.ok("Successfully logged out");
    }
}
