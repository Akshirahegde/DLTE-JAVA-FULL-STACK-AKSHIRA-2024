//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.01 at 08:12:56 AM IST 
//


package services.transaction;

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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reciever" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reciever"
})
@XmlRootElement(name = "findByReceiverRequest")
public class FindByReceiverRequest {

    @XmlElement(required = true)
    protected String reciever;

    /**
     * Gets the value of the reciever property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReciever() {
        return reciever;
    }

    /**
     * Sets the value of the reciever property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReciever(String value) {
        this.reciever = value;
    }

}
