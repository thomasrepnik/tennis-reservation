package ch.repnik.tennis.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Reservation entity.
 */
public class ReservationDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate datum;
    
    @NotNull
    private FeldDTO feld;

    @NotNull
    private String start;

    @NotNull
    private String ende;

    private UserSlimDTO spieler1;

    private UserSlimDTO spieler2;

    private UserSlimDTO spieler3;

    private UserSlimDTO spieler4;

    private String gast1;

    private String gast2;

    private String gast3;

    private String gast4;


    private ReservationTypeDTO reservationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public FeldDTO getFeld() {
		return feld;
	}

	public void setFeld(FeldDTO feld) {
		this.feld = feld;
	}

	public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnde() {
        return ende;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    }

    
    
    public UserSlimDTO getSpieler1() {
		return spieler1;
	}

	public void setSpieler1(UserSlimDTO spieler1) {
		this.spieler1 = spieler1;
	}

	public UserSlimDTO getSpieler2() {
		return spieler2;
	}

	public void setSpieler2(UserSlimDTO spieler2) {
		this.spieler2 = spieler2;
	}

	public UserSlimDTO getSpieler3() {
		return spieler3;
	}

	public void setSpieler3(UserSlimDTO spieler3) {
		this.spieler3 = spieler3;
	}

	public UserSlimDTO getSpieler4() {
		return spieler4;
	}

	public void setSpieler4(UserSlimDTO spieler4) {
		this.spieler4 = spieler4;
	}

	public String getGast1() {
        return gast1;
    }

    public void setGast1(String gast1) {
        this.gast1 = gast1;
    }

    public String getGast2() {
        return gast2;
    }

    public void setGast2(String gast2) {
        this.gast2 = gast2;
    }

    public String getGast3() {
        return gast3;
    }

    public void setGast3(String gast3) {
        this.gast3 = gast3;
    }

    public String getGast4() {
        return gast4;
    }

    public void setGast4(String gast4) {
        this.gast4 = gast4;
    }

    public ReservationTypeDTO getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationTypeDTO reservationType) {
		this.reservationType = reservationType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationDTO reservationDTO = (ReservationDTO) o;
        if (reservationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
            "id=" + getId() +
            ", datum='" + getDatum() + "'" +
            ", start='" + getStart() + "'" +
            ", ende='" + getEnde() + "'" +
            ", spieler1=" + getSpieler1() +
            ", spieler2=" + getSpieler2() +
            ", spieler3=" + getSpieler3() +
            ", spieler4=" + getSpieler4() +
            ", gast1='" + getGast1() + "'" +
            ", gast2='" + getGast2() + "'" +
            ", gast3='" + getGast3() + "'" +
            ", gast4='" + getGast4() + "'" +
            ", reservationType=" + getReservationType() +
            "}";
    }
}
