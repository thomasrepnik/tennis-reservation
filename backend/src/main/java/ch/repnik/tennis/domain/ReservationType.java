package ch.repnik.tennis.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A ReservationType.
 */
@Entity
@Table(name = "reservation_type")
public class ReservationType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 1)
    @Column(name = "anzahlspieler")
    private Integer anzahlspieler;

    @Column(name = "kontingent")
    private Integer kontingent;

    @ManyToMany(mappedBy="reservationTypes")
    private List<Feld> felder = new ArrayList<Feld>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ReservationType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnzahlspieler() {
        return anzahlspieler;
    }

    public ReservationType anzahlspieler(Integer anzahlspieler) {
        this.anzahlspieler = anzahlspieler;
        return this;
    }

    public void setAnzahlspieler(Integer anzahlspieler) {
        this.anzahlspieler = anzahlspieler;
    }

    public Integer getKontingent() {
        return kontingent;
    }

    public ReservationType kontingent(Integer kontingent) {
        this.kontingent = kontingent;
        return this;
    }

    public void setKontingent(Integer kontingent) {
        this.kontingent = kontingent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public List<Feld> getFelder() {
		return felder;
	}

	public void setFelder(List<Feld> felder) {
		this.felder = felder;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationType reservationType = (ReservationType) o;
        if (reservationType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservationType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservationType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", anzahlspieler=" + getAnzahlspieler() +
            ", kontingent=" + getKontingent() +
            "}";
    }
}
