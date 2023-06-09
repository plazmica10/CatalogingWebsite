package tim2.cataloging.tim2.dto;

import tim2.cataloging.tim2.model.ActivationRequest;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.model.User;

public class ActivationDto {

    private String email;

    private String message;

    private String phone;

    private User user;

    private ActivationDto() {}

    public ActivationDto(String email, String message, String phone, User user) {
        this.email = email;
        this.message = message;
        this.phone = phone;
        this.user = user;
    }
    public ActivationDto(ActivationRequest activationRequest) {
        this.email = activationRequest.getEmail();
        this.message = activationRequest.getMessage();
        this.phone = activationRequest.getPhone();
        this.user = activationRequest.getUser();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
