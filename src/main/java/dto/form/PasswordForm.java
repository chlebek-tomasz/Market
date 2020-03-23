package dto.form;

import com.chlebek.project.validation.PasswordMatches;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordForm {
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    @NotNull
    @Size(min = 8, max = 32)
    private String passwordMatches;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMatches() {
        return passwordMatches;
    }

    public void setPasswordMatches(String passwordMatches) {
        this.passwordMatches = passwordMatches;
    }
}
