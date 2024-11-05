package com.eyproject.myproject.classes.dto;

public class ResidenzaDTO {

    private Integer id ;
    private String indirizzo ;
    private String citta ;
    private Integer cap ;
    private Integer id_anagrafica;

    public Integer getId_anagrafica() {
        return id_anagrafica;
    }

    public void setId_anagrafica(Integer id_anagrafica) {
        this.id_anagrafica = id_anagrafica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "ResidenzaDTO{" +
                "id=" + id +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", cap=" + cap +
                '}';
    }
}
