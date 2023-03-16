package com.example.bank.dto.user;
import com.example.bank.model.Card;
import com.example.bank.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Size(min = 8,max = 20,message = "Username Invalid")
    @NotEmpty(message = "Username cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",message = "Username Is Invalid")
    private String userName;
    private String firstName;
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email Is Invalid")

    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}", message = "Password is Invalid")
    private String password;
    private Long cardId;
    private List<Role> roles;

}
