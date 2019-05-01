package ch.repnik.tennis.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ReservationType entity.
 */
public class ReservationTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @Min(value = 1)
    private Integer anzahlspieler;

    private Integer kontingent;


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

    public Integer getAnzahlspieler() {
        return anzahlspieler;
    }

    public void setAnzahlspieler(Integer anzahlspieler) {
        this.anzahlspieler = anzahlspieler;
    }

    public Integer getKontingent() {
        return kontingent;
    }

    public void setKontingent(Integer kontingent) {
        this.kontingent = kontingent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationTypeDTO reservationTypeDTO = (ReservationTypeDTO) o;
        if (reservationTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservationTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservationTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", anzahlspieler=" + getAnzahlspieler() +
            ", kontingent=" + getKontingent() +
            "}";
    }
}
