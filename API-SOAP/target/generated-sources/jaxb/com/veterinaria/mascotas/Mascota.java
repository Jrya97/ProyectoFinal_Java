//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.08.12 a las 01:23:04 PM GMT-05:00 
//


package com.veterinaria.mascotas;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mascota complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mascota"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idMascota" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idPropietario" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="especie" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="raza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="peso" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="sexo" type="{http://veterinaria.com/mascotas}sexo" minOccurs="0"/&gt;
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mascota", propOrder = {
    "idMascota",
    "idPropietario",
    "nombre",
    "especie",
    "raza",
    "fechaNacimiento",
    "peso",
    "sexo",
    "fechaRegistro"
})
public class Mascota {

    protected int idMascota;
    protected int idPropietario;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String especie;
    protected String raza;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaNacimiento;
    protected BigDecimal peso;
    @XmlSchemaType(name = "string")
    protected Sexo sexo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;

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
     * Obtiene el valor de la propiedad idPropietario.
     * 
     */
    public int getIdPropietario() {
        return idPropietario;
    }

    /**
     * Define el valor de la propiedad idPropietario.
     * 
     */
    public void setIdPropietario(int value) {
        this.idPropietario = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad especie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Define el valor de la propiedad especie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEspecie(String value) {
        this.especie = value;
    }

    /**
     * Obtiene el valor de la propiedad raza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Define el valor de la propiedad raza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaza(String value) {
        this.raza = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Define el valor de la propiedad fechaNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaNacimiento(XMLGregorianCalendar value) {
        this.fechaNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad peso.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeso() {
        return peso;
    }

    /**
     * Define el valor de la propiedad peso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeso(BigDecimal value) {
        this.peso = value;
    }

    /**
     * Obtiene el valor de la propiedad sexo.
     * 
     * @return
     *     possible object is
     *     {@link Sexo }
     *     
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Define el valor de la propiedad sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link Sexo }
     *     
     */
    public void setSexo(Sexo value) {
        this.sexo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

}
