package com.mipagina.literAlura.model;

public enum Idioma {
    INGLÉS("en", "inglés"),
    ESPAÑOL("es", "español"),
    FRANCÉS("fr","francés"),
    PORTUGUÉS("pt","portugués"),
    OTRO(" ","otro");

    private String abreviacion;

    private String lenguaje;

    Idioma (String abreviacion, String lenguaje){
        this.abreviacion = abreviacion;
        this.lenguaje = lenguaje;
    }

    public static Idioma desdeAPI(String texto) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.abreviacion.equalsIgnoreCase(texto)) {
                return idioma;
            } else {
                return OTRO;
            }
        }
        throw new IllegalArgumentException("No se encontró el idioma: " + texto);
    }

    public static Idioma desdeConsola(String texto) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.lenguaje.equalsIgnoreCase(texto)) {
                return idioma;
            } else {
                return OTRO;
            }
        }
        throw new IllegalArgumentException("No se encontró el idioma: " + texto);
    }

    public static String toString(Idioma idioma) {
        return idioma.lenguaje;
    }



}
