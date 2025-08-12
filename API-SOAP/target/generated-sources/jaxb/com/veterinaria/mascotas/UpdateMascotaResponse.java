//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.08.12 a las 01:23:04 PM GMT-05:00 
//


package com.veterinaria.mascotas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mascota" type="{http://veterinaria.com/mascotas}mascota"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mascota"
})
@XmlRootElement(name = "updateMascotaResponse")
public class UpdateMascotaResponse {

    @XmlElement(required = true)
    protected Mascota mascota;

    /**
     * Obtiene el valor de la propiedad mascota.
     * 
     * @return
     *     possible object is
     *     {@link Mascota }
     *     
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Define el valor de la propiedad mascota.
     * 
     * @param value
     *     allowed object is
     *     {@link Mascota }
     *     
     */
    public void setMascota(Mascota value) {
        this.mascota = value;
    }

}
