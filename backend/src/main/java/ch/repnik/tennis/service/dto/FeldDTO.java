package ch.repnik.tennis.service.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Feld entity.
 */
public class FeldDTO implements Serializable {

    private Long id;

    private String name;


    private Set<ReservationTypeDTO> reservationTypes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ReservationTypeDTO> getReservationTypes() {
        return reservationTypes;
    }

    public void setReservationTypes(Set<ReservationTypeDTO> reservationTypes) {
        this.reservationTypes = reservationTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeldDTO feldDTO = (FeldDTO) o;
        if (feldDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feldDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeldDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
