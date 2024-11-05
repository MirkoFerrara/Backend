package com.eyproject.myproject.classes.pojo;

import com.eyproject.myproject.classes.exception.MyException;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Entity
@Table(name= "persona")
public class PersonaPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "codice_fiscale", nullable = false, unique = true)
    private String codiceFiscale;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @OneToOne(mappedBy="persona", fetch = FetchType.EAGER)
    private ResidenzaPojo residenza ;

    public ResidenzaPojo getResidenza() {
        return residenza;
    }

    public void setResidenza(ResidenzaPojo residenza) {
        this.residenza = residenza;
        ResponseEntity<Integer> response ;
    }

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) throws MyException {
        this.codiceFiscale = codiceFiscale;
    }
    public LocalDate  getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(LocalDate  dataNascita) {
        this.dataNascita = dataNascita;
    }
}
