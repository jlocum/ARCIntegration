//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 01:34:13 PM CST 
//


package com.splitsecnd.integration.ima.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stateCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="stateLabel" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="uidIMA" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "stateCode",
    "stateLabel",
    "uidIMA"
})
@XmlRootElement(name = "callResponse")
public class CallResponse {

    protected int stateCode;
    @XmlElement(required = true)
    protected String stateLabel;
    @XmlElement(required = true)
    protected String uidIMA;

    /**
     * Gets the value of the stateCode property.
     * 
     * @return
     * 		stateCode
     */
    public int getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     * 
     * @param value
     * 		{@link int}
     */
    public void setStateCode(int value) {
        this.stateCode = value;
    }

    /**
     * Gets the value of the stateLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateLabel() {
        return stateLabel;
    }

    /**
     * Sets the value of the stateLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateLabel(String value) {
        this.stateLabel = value;
    }

    /**
     * Gets the value of the uidIMA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUidIMA() {
        return uidIMA;
    }

    /**
     * Sets the value of the uidIMA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUidIMA(String value) {
        this.uidIMA = value;
    }

}
