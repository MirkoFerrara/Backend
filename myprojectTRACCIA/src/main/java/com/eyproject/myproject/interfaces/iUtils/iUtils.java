package com.eyproject.myproject.interfaces.iUtils;

public interface iUtils {

    String CF_REGEX = "^[A-Z]{6}\\d{2}[ABCDEHLMPRST]\\d{2}[A-Z]\\d{3}[A-Z]$" ;
    String INDIRIZZO_REGEX = "^[0-9A-Za-z'\\s.,-]+\\s[0-9]+[A-Za-z]?$";
    String NOME_COGNOME_REGEX = "^[A-Za-z'\\s]+$"; // Regex per nome e cognome
    String CAP_REGEX = "^[0-9]{5}$";
    String CITY_NAME_REGEX = "^[A-Za-zÀ-ÖØ-öù-ÿ'\\s-]+$"; // Regex for valid city names
}
