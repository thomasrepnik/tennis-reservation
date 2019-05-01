package ch.repnik.tennis.service.dto;

import ch.repnik.tennis.config.Constants;

import ch.repnik.tennis.domain.Authority;
import ch.repnik.tennis.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserSlimDTO {

    private Long id;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
    public UserSlimDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserSlimDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            "}";
    }
}
