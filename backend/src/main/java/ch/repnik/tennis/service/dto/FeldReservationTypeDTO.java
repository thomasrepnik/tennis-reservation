package ch.repnik.tennis.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FeldReservationType entity.
 */
public class FeldReservationTypeDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeldReservationTypeDTO feldReservationTypeDTO = (FeldReservationTypeDTO) o;
        if (feldReservationTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feldReservationTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeldReservationTypeDTO{" +
            "id=" + getId() +
            "}";
    }
}
