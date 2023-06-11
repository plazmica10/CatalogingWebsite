package tim2.cataloging.tim2.dto;

import tim2.cataloging.tim2.model.ActivationRequest;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.model.STATUS;
import tim2.cataloging.tim2.model.User;

import java.time.LocalDate;

public class ActivationDto {

    private Long id;

    private String email;

    private String message;

    private String phone;

    private User user;

    private STATUS status;

    private LocalDate date;

    private ActivationDto() {}

    public ActivationDto(Long id, String email, String message, String phone, User user, STATUS status, LocalDate date) {
        this.id = id;
        this.email = email;
        this.message = message;
        this.phone = phone;
        this.user = user;
        this.status = status;
        this.date = date;
    }
    public ActivationDto(ActivationRequest activationRequest) {
        this.id = activationRequest.getId();
        this.email = activationRequest.getEmail();
        this.message = activationRequest.getMessage();
        this.phone = activationRequest.getPhone();
        this.user = activationRequest.getUser();
        this.status = activationRequest.getStatus();
        this.date = activationRequest.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
