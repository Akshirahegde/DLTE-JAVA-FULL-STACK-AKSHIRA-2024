//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.24 at 09:28:23 AM IST 
//


package services.deposit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deposit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deposit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depositId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="depositName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="depositRoi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="depositType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="depositDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deposit", propOrder = {
    "depositId",
    "depositName",
    "depositRoi",
    "depositType",
    "depositDescription"
})
public class Deposit {

    protected long depositId;
    @XmlElement(required = true)
    protected String depositName;
    protected double depositRoi;
    @XmlElement(required = true)
    protected String depositType;
    @XmlElement(required = true)
    protected String depositDescription;

    /**
     * Gets the value of the depositId property.
     * 
     */
    public long getDepositId() {
        return depositId;
    }

    /**
     * Sets the value of the depositId property.
     * 
     */
    public void setDepositId(long value) {
        this.depositId = value;
    }

    /**
     * Gets the value of the depositName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositName() {
        return depositName;
    }

    /**
     * Sets the value of the depositName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositName(String value) {
        this.depositName = value;
    }

    /**
     * Gets the value of the depositRoi property.
     * 
     */
    public double getDepositRoi() {
        return depositRoi;
    }

    /**
     * Sets the value of the depositRoi property.
     * 
     */
    public void setDepositRoi(double value) {
        this.depositRoi = value;
    }

    /**
     * Gets the value of the depositType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositType() {
        return depositType;
    }

    /**
     * Sets the value of the depositType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositType(String value) {
        this.depositType = value;
    }

    /**
     * Gets the value of the depositDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositDescription() {
        return depositDescription;
    }

    /**
     * Sets the value of the depositDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositDescription(String value) {
        this.depositDescription = value;
    }

}
