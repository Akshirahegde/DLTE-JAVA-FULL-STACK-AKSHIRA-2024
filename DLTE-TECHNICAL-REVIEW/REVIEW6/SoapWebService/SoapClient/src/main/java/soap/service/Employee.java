
package soap.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeDetails" type="{http://service.soap/}employeeDetails" minOccurs="0"/>
 *         &lt;element name="employeePermanentAddress" type="{http://service.soap/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://service.soap/}employeeAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "employeeDetails",
    "employeePermanentAddress",
    "employeeTemporaryAddress"
})
public class Employee {

    protected EmployeeDetails employeeDetails;
    protected EmployeeAddress employeePermanentAddress;
    protected EmployeeAddress employeeTemporaryAddress;

    /**
     * Gets the value of the employeeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeDetails }
     *     
     */
    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    /**
     * Sets the value of the employeeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeDetails }
     *     
     */
    public void setEmployeeDetails(EmployeeDetails value) {
        this.employeeDetails = value;
    }

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeePermanentAddress(EmployeeAddress value) {
        this.employeePermanentAddress = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeeTemporaryAddress(EmployeeAddress value) {
        this.employeeTemporaryAddress = value;
    }

}
