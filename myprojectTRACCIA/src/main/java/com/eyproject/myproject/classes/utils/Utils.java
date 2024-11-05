package com.eyproject.myproject.classes.utils;

import com.eyproject.myproject.classes.dto.PersonaDTO;
import com.eyproject.myproject.classes.dto.ResidenzaDTO;
import com.eyproject.myproject.classes.exception.MyException;
import com.eyproject.myproject.interfaces.iUtils.iUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils implements iUtils {

    private static void isValidCodiceFiscale(String cf) throws MyException {
        if (cf == null || cf.trim().isEmpty())
            throw new MyException("Il codice fiscale non può essere null o vuoto");

        if (!Pattern.matches(CF_REGEX, cf))
            throw new MyException("Formato codice fiscale non valido: " + cf);

    }

    private static void isValidCognome(String cognome) throws MyException {
        if (cognome == null || cognome.trim().isEmpty())
            throw new MyException("Il cognome non può essere null o vuoto");

        if (!cognome.matches(NOME_COGNOME_REGEX))
            throw new MyException("Formato cognome non valido: " + cognome);
    }

    private static void isValidNome(String nome) throws MyException {
        if (nome == null || nome.trim().isEmpty())
            throw new MyException("Il nome non può essere null o vuoto");

        if (!nome.matches(NOME_COGNOME_REGEX))
            throw new MyException("Formato nome non valido: " + nome);

    }

    private static void isValidDataNascita(String dataNascita) throws MyException {
        if (dataNascita == null || dataNascita.trim().isEmpty())
            throw new MyException("La data di nascita non può essere null o vuota");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALIAN);
        try {
            LocalDate.parse(dataNascita, formatter);
        } catch (DateTimeParseException e) {
            throw new MyException("Formato data non valido: " + dataNascita + ". Deve essere nel formato dd/MM/yyyy");
        }
    }

    private static void isValidIndirizzo(String indirizzo) throws MyException {
        if (indirizzo == null || indirizzo.trim().isEmpty())
            throw new MyException("L'indirizzo non può essere null o vuoto");
        if (!indirizzo.matches(INDIRIZZO_REGEX))
            throw new MyException("Formato indirizzo non valido: " + indirizzo);
    }

    private static void isValidCAP(String cap) throws MyException {
        if (cap == null || cap.trim().isEmpty())
            throw new MyException("Il CAP non può essere null o vuoto.");
        if (!Pattern.matches(CAP_REGEX, cap))
            throw new MyException("Formato CAP non valido: " + cap + ". Deve essere composto da 5 cifre.");
    }

    private static void isValidCity(String city) throws MyException {
        if (city == null || city.trim().isEmpty())
            throw new MyException("Il nome della città non può essere null o vuoto");
        if (!Pattern.matches(CITY_NAME_REGEX, city))
            throw new MyException("Formato nome città non valido: " + city);
    }
    public static void validationPersonaCheck(PersonaDTO personaDTO) throws MyException {
        if (personaDTO == null)
            throw new MyException("Persona non può essere null");

        String dataNascitaString = personaDTO.getDataNascita() != null
                ? personaDTO.getDataNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        isValidCodiceFiscale(personaDTO.getCodiceFiscale()) ;
        isValidCognome(personaDTO.getCognome()) ;
        isValidNome(personaDTO.getNome()) ;
        isValidDataNascita(dataNascitaString) ;
    }

    public static void validationResidenzaCheck(ResidenzaDTO residenzaDTO) throws MyException {
        if (residenzaDTO == null)
            throw new MyException("Residenza non può essere null");

        isValidCAP(residenzaDTO.getCap().toString());
        isValidIndirizzo(residenzaDTO.getIndirizzo());
        isValidCity(residenzaDTO.getCitta());
    }
}
