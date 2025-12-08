package org.example.validateinforuser.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.validateinforuser.validation.Step1;
import org.example.validateinforuser.validation.Step2;
import org.example.validateinforuser.validation.ValidPhoneNumber;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "{firstname.notblank}", groups = Step1.class)
    @Size(min = 1, max = 45, message = "{firstname.size}", groups = Step2.class)
    private String firstName;

    @NotBlank(message = "{lastname.notblank}", groups = Step1.class)
    @Size(min = 1, max = 45, message = "{lastname.size}", groups = Step2.class)
    private String lastName;

    @NotNull(message = "{age.notblank}", groups = Step1.class)
    @Min(value = 18, message = "{age.min}", groups = Step2.class)
    private Integer age;

    @NotBlank(message = "{email.notblank}", groups = Step1.class)
    @Email(message = "{email.invalid}", groups = Step2.class)
    private String email;
    @NotBlank(message = "{phone.notblank}", groups = Step1.class)
    @ValidPhoneNumber(groups = Step2.class)
    private String phoneNumber;
}
