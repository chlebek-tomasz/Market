package com.chlebek.project.dto.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailForm {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Email
    private String emailConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }
}
