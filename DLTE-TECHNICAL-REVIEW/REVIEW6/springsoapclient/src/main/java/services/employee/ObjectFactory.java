
package services.employee;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.employee package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateEmployeeResponse }
     * 
     */
    public CreateEmployeeResponse createCreateEmployeeResponse() {
        return new CreateEmployeeResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link FilterByIdResponse }
     * 
     */
    public FilterByIdResponse createFilterByIdResponse() {
        return new FilterByIdResponse();
    }

    /**
     * Create an instance of {@link CreateEmployeeRequest }
     * 
     */
    public CreateEmployeeRequest createCreateEmployeeRequest() {
        return new CreateEmployeeRequest();
    }

    /**
     * Create an instance of {@link FilterByPinRequest }
     * 
     */
    public FilterByPinRequest createFilterByPinRequest() {
        return new FilterByPinRequest();
    }

    /**
     * Create an instance of {@link FilterByIdRequest }
     * 
     */
    public FilterByIdRequest createFilterByIdRequest() {
        return new FilterByIdRequest();
    }

    /**
     * Create an instance of {@link FindAllEmployeeRequest }
     * 
     */
    public FindAllEmployeeRequest createFindAllEmployeeRequest() {
        return new FindAllEmployeeRequest();
    }

    /**
     * Create an instance of {@link FilterByPinResponse }
     * 
     */
    public FilterByPinResponse createFilterByPinResponse() {
        return new FilterByPinResponse();
    }

    /**
     * Create an instance of {@link FindAllEmployeeResponse }
     * 
     */
    public FindAllEmployeeResponse createFindAllEmployeeResponse() {
        return new FindAllEmployeeResponse();
    }

    /**
     * Create an instance of {@link EmployeeDetails }
     * 
     */
    public EmployeeDetails createEmployeeDetails() {
        return new EmployeeDetails();
    }

    /**
     * Create an instance of {@link EmployeeAddress }
     * 
     */
    public EmployeeAddress createEmployeeAddress() {
        return new EmployeeAddress();
    }

}
