package tim2.cataloging.tim2.dto;

import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.Shelf;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AuthorDto extends UserDto{

    private boolean isActivated;


    public AuthorDto(long id, String username, String name, String surname, String email, LocalDate date, String photo, String description, ROLE role, List<Shelf> shelves, boolean isActivated) {
        super(id, username, name, surname, email, date, photo, description, role, shelves);
        this.isActivated = isActivated;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
