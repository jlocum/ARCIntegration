//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 01:34:13 PM CST 
//


package com.splitsecnd.integration.ima.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Request"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contextCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="otherInformations" type="{http://not.ima.tm.fr/telematic}KeyValuePair" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "contextCode",
    "otherInformations"
})
@XmlSeeAlso({
    AssistanceRequest.class,
    ServiceRequest.class
})
public class Request {

    @XmlElement(required = true)
    protected String contextCode;
    protected List<KeyValuePair> otherInformations;

    /**
     * Gets the value of the contextCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextCode() {
        return contextCode;
    }

    /**
     * Sets the value of the contextCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextCode(String value) {
        this.contextCode = value;
    }

    /**
     * Gets the value of the otherInformations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherInformations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherInformations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValuePair }
     * 
     * 
     * @return
     *     possible object is
     *     {@link List }
     */
    public List<KeyValuePair> getOtherInformations() {
        if (otherInformations == null) {
            otherInformations = new ArrayList<KeyValuePair>();
        }
        return this.otherInformations;
    }

}
