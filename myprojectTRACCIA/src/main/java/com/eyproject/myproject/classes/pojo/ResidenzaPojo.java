package com.eyproject.myproject.classes.pojo;

import jakarta.persistence.*;


@Entity
@Table(name="residenza",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_anagrafica"}) })
public class ResidenzaPojo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false)
    private String indirizzo ;

    @Column(nullable = false)
    private String citta ;

    @Column(nullable = false)
    private Integer cap ;

    @OneToOne
    @JoinColumn(name="id_anagrafica", referencedColumnName = "id")
    private PersonaPojo persona;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public PersonaPojo getPersona() {
        return persona;
    }

    public void setPersona(PersonaPojo persona) {
        this.persona = persona;
    }
}
