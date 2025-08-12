//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.08.12 a las 03:26:00 PM GMT-05:00 
//


package com.veterinaria.rest.soap.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para consultaInput complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
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
@XmlType(name = "consultaInput", namespace = "http://veterinaria.com/consultas", propOrder = {
    "idMascota",
    "fechaConsulta",
    "motivo",
    "diagnostico",
    "tratamiento",
    "observaciones",
    "costo"
})
public class ConsultaInput {

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
