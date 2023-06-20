package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.LoginDto;
import tim2.cataloging.tim2.dto.RegisterDto;
import tim2.cataloging.tim2.dto.UserDto;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShelfService shelfService;

    // READ ALL
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> userList = userService.findAll();
        if(userList == null)
            return ResponseEntity.notFound().build();

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto dto = new UserDto(user);
            userDtoList.add(dto);
        }

        return ResponseEntity.ok(userDtoList);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Long id) {
        User user = userService.findOne(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        else {
            UserDto dto = new UserDto(user);
            return ResponseEntity.ok(dto);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") Long id, @RequestBody User user, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() == ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        User existingUser = userService.findOne(id);
        if (existingUser == null)
            return ResponseEntity.badRequest().body("User with id: " + id + " does not exist!");
        if (loggedUser.getId() != existingUser.getId())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if (user.getName() != null)
            existingUser.setName(user.getName());
        if (user.getSurname() != null)
            existingUser.setSurname(user.getSurname());
        if (user.getDate() != null)
            existingUser.setDate(user.getDate());
        if (user.getPhoto() != null)
            existingUser.setPhoto(user.getPhoto());
        if (user.getDescription() != null)
            existingUser.setDescription(user.getDescription());
        if (user.getPassword() != null)
            existingUser.setPassword(user.getPassword());
        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());
        if (user.getUsername() != null)
            existingUser.setUsername(user.getUsername());
        userService.save(existingUser);

        return ResponseEntity.ok("Successfully updated user with id: " + id);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto, HttpSession session) {
        System.out.println("Login request: " + loginDto.getEmail() + " " + loginDto.getPassword());
        System.out.printf("Session id: %s%n", session.getId());
        User user = (User) session.getAttribute("user");
        if (user != null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        if (loginDto.getEmail().isEmpty() || loginDto.getPassword().isEmpty())
            return ResponseEntity.badRequest().body(null);

        User loggedUser = userService.login(loginDto.getEmail(), loginDto.getPassword());
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        session.setAttribute("user", loggedUser);
        return ResponseEntity.ok(new UserDto(loggedUser));
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        System.out.printf("Session id: %s%n", session.getId());
        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in!");

        session.invalidate();
        return ResponseEntity.ok("Successfully logged out");
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDto userRequest,HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        try {
            User user = userService.findByEmail(userRequest.getEmail());
            if (user != null)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            user = userService.findByUsername(userRequest.getUsername());
            if (user != null)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

            user = new User();
            user.setName(userRequest.getName());
            user.setSurname(userRequest.getSurname());
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setRole(ROLE.READER);

            User newUser = userService.register(user);
            if(newUser == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


            Shelf wantToRead = new Shelf("Want to read", true);
            shelfService.save(wantToRead);
            Shelf currentlyReading = new Shelf("Currently reading", true);
            shelfService.save(currentlyReading);
            Shelf read = new Shelf("Read", true);
            shelfService.save(read);

            List<Shelf> shelves = new ArrayList<>();
            shelves.add(wantToRead);
            shelves.add(currentlyReading);
            shelves.add(read);

            newUser.setShelves(shelves);
            userService.save(newUser);

            session.setAttribute("user", newUser);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    //get shelves of a user
    @GetMapping("/{id}/shelves")
    public ResponseEntity<List<Shelf>> getShelves(@PathVariable(name = "id") Long id) {
        User user = userService.findOne(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.getShelves());
    }
}
