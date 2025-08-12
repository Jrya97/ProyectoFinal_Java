//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.08.12 a las 01:23:04 PM GMT-05:00 
//


package com.veterinaria.consultas;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consulta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idConsulta" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idMascota" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fechaConsulta" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="diagnostico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tratamiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consulta", propOrder = {
    "idConsulta",
    "idMascota",
    "fechaConsulta",
    "motivo",
    "diagnostico",
    "tratamiento",
    "observaciones",
    "costo"
})
public class Consulta {

    protected int idConsulta;
    protected int idMascota;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaConsulta;
    @XmlElement(required = true)
    protected String motivo;
    protected String diagnostico;
    protected String tratamiento;
    protected String observaciones;
    protected BigDecimal costo;

    /**
     * Obtiene el valor de la propiedad idConsulta.
     * 
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * Define el valor de la propiedad idConsulta.
     * 
     */
    public void setIdConsulta(int value) {
        this.idConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad idMascota.
     * 
     */
    public int getIdMascota() {
        return idMascota;
    }

    /**
     * Define el valor de la propiedad idMascota.
     * 
     */
    public void setIdMascota(int value) {
        this.idMascota = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaConsulta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * Define el valor de la propiedad fechaConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaConsulta(XMLGregorianCalendar value) {
        this.fechaConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad motivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Define el valor de la propiedad motivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Obtiene el valor de la propiedad diagnostico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Define el valor de la propiedad diagnostico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnostico(String value) {
        this.diagnostico = value;
    }

    /**
     * Obtiene el valor de la propiedad tratamiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Define el valor de la propiedad tratamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTratamiento(String value) {
        this.tratamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCosto(BigDecimal value) {
        this.costo = value;
    }

}
