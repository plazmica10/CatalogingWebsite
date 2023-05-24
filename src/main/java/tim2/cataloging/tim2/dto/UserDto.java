package tim2.cataloging.tim2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.model.User;

import java.util.Date;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDto {

    private long id;

    private String username;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String password2;

    private Date date;

    private String photo;   //link to photo

    private String description;

    private ROLE role;

    private List<Shelf> shelves;

    public UserDto(long id, String username, String name, String surname, String email, Date date, String photo, String description, ROLE role, List<Shelf> shelves) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.date = date;
        this.photo = photo;
        this.description = description;
        this.role = role;
        this.shelves = shelves;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.date = user.getDate();
        this.photo = user.getPhoto();
        this.description = user.getDescription();
        this.role = user.getRole();
        this.shelves = user.getShelves();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public Date getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public ROLE getRole() {
        return role;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }
}
