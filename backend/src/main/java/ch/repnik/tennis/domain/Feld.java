package ch.repnik.tennis.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Feld.
 */
@Entity
@Table(name = "feld")
public class Feld implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "feld_reservation_type",
               joinColumns = @JoinColumn(name = "feld_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "reservation_type_id", referencedColumnName = "id"))
    private Set<ReservationType> reservationTypes = new HashSet<>();

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

    public Feld name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ReservationType> getReservationTypes() {
        return reservationTypes;
    }

    public Feld reservationTypes(Set<ReservationType> reservationTypes) {
        this.reservationTypes = reservationTypes;
        return this;
    }

    public Feld addReservationType(ReservationType reservationType) {
        this.reservationTypes.add(reservationType);
        reservationType.getFelder().add(this);
        return this;
    }

    public Feld removeReservationType(ReservationType reservationType) {
        this.reservationTypes.remove(reservationType);
        reservationType.getFelder().remove(this);
        return this;
    }

    public void setReservationTypes(Set<ReservationType> reservationTypes) {
        this.reservationTypes = reservationTypes;
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
        Feld feld = (Feld) o;
        if (feld.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feld.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Feld{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
