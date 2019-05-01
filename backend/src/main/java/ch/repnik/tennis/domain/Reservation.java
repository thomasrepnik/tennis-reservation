package ch.repnik.tennis.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Reservation.
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @NotNull
    @Column(name = "datum", nullable = false)
    private LocalDate datum;
    
    @ManyToOne()
    @JoinColumn(name = "feld_id")
    private Feld feld;

    @NotNull
    @Column(name = "jhi_start", nullable = false)
    private String start;

    @NotNull
    @Column(name = "ende", nullable = false)
    private String ende;

    @ManyToOne()
    @JoinColumn(name="spieler_1")
    private User spieler1;

    @ManyToOne
    @JoinColumn(name="spieler_2")
    private User spieler2;

    @ManyToOne
    @JoinColumn(name="spieler_3")
    private User spieler3;

    @ManyToOne
    @JoinColumn(name="spieler_4")
    private User spieler4;

    @Column(name = "gast_1")
    private String gast1;

    @Column(name = "gast_2")
    private String gast2;

    @Column(name = "gast_3")
    private String gast3;

    @Column(name = "gast_4")
    private String gast4;

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private ReservationType reservationType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Reservation datum(LocalDate datum) {
        this.datum = datum;
        return this;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Feld getFeld() {
		return feld;
	}

	public void setFeld(Feld feld) {
		this.feld = feld;
	}

	public String getStart() {
        return start;
    }

    public Reservation start(String start) {
        this.start = start;
        return this;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnde() {
        return ende;
    }

    public Reservation ende(String ende) {
        this.ende = ende;
        return this;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    }

    public User getSpieler1() {
		return spieler1;
	}

	public void setSpieler1(User spieler1) {
		this.spieler1 = spieler1;
	}

	public User getSpieler2() {
		return spieler2;
	}

	public void setSpieler2(User spieler2) {
		this.spieler2 = spieler2;
	}

	public User getSpieler3() {
		return spieler3;
	}

	public void setSpieler3(User spieler3) {
		this.spieler3 = spieler3;
	}

	public User getSpieler4() {
		return spieler4;
	}

	public void setSpieler4(User spieler4) {
		this.spieler4 = spieler4;
	}

	public String getGast1() {
        return gast1;
    }

    public Reservation gast1(String gast1) {
        this.gast1 = gast1;
        return this;
    }

    public void setGast1(String gast1) {
        this.gast1 = gast1;
    }

    public String getGast2() {
        return gast2;
    }

    public Reservation gast2(String gast2) {
        this.gast2 = gast2;
        return this;
    }

    public void setGast2(String gast2) {
        this.gast2 = gast2;
    }

    public String getGast3() {
        return gast3;
    }

    public Reservation gast3(String gast3) {
        this.gast3 = gast3;
        return this;
    }

    public void setGast3(String gast3) {
        this.gast3 = gast3;
    }

    public String getGast4() {
        return gast4;
    }

    public Reservation gast4(String gast4) {
        this.gast4 = gast4;
        return this;
    }

    public void setGast4(String gast4) {
        this.gast4 = gast4;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public Reservation reservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
        return this;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        if (reservation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reservation{" +
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
            "}";
    }
}
