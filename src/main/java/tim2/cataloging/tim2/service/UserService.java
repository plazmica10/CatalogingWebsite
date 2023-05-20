package tim2.cataloging.tim2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User findByEmail(String email) {
        return userRepository.getByEmail(email);
    }
    public User findByUsername(String username) {
        return userRepository.getByUsername(username);
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User login(String username, String password) {
        User user = userRepository.getByUsername(username);
        if(user == null || !user.getPassword().equals(password))
            return null;
        return  user;
    }
    public User register(User user) {
        return userRepository.save(user);
    }

}
