package tim2.cataloging.tim2.dto;

import tim2.cataloging.tim2.model.ActivationRequest;

public class ActivationDto {

    private String email;

    private String message;

    private String phone;

    private ActivationDto() {}

    public ActivationDto(String email, String message, String phone) {
        this.email = email;
        this.message = message;
        this.phone = phone;
    }
    public ActivationDto(ActivationRequest activationRequest) {
        this.email = activationRequest.getEmail();
        this.message = activationRequest.getMessage();
        this.phone = activationRequest.getPhone();
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
}
