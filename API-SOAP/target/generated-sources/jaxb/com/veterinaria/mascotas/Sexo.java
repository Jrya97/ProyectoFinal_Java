//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.08.12 a las 01:23:04 PM GMT-05:00 
//


package com.veterinaria.mascotas;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para sexo.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="sexo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Macho"/&gt;
 *     &lt;enumeration value="Hembra"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sexo")
@XmlEnum
public enum Sexo {

    @XmlEnumValue("Macho")
    MACHO("Macho"),
    @XmlEnumValue("Hembra")
    HEMBRA("Hembra");
    private final String value;

    Sexo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Sexo fromValue(String v) {
        for (Sexo c: Sexo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
