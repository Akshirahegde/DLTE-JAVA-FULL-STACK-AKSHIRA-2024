
package web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "AccountSoap", targetNamespace = "http://web/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
        ObjectFactory.class
})
public interface AccountSoap {


    /**
     * @param string
     * @param _long
     */
    @WebMethod
    @Action(input = "http://web/AccountSoap/transactionUpdateRequest", output = "http://web/AccountSoap/transactionUpdateResponse")
    public void transactionUpdate(
            @WebParam(name = "String", partName = "String")
                    String string,
            @WebParam(name = "Long", partName = "Long")
                    long _long);

    /**
     * @param string
     * @return returns web.GroupAccount
     */
    @WebMethod
    @WebResult(name = "findUsername", partName = "findUsername")
    @Action(input = "http://web/AccountSoap/findUserRequest", output = "http://web/AccountSoap/findUserResponse")
    public GroupAccount findUser(
            @WebParam(name = "String", partName = "String")
                    String string);

    /**
     * @param long1
     * @param string3
     * @param string4
     * @param string1
     * @param _long
     * @param string2
     */
    @WebMethod
    @Action(input = "http://web/AccountSoap/createAccountRequest", output = "http://web/AccountSoap/createAccountResponse")
    public void createAccount(
            @WebParam(name = "String1", partName = "String1")
                    String string1,
            @WebParam(name = "String2", partName = "String2")
                    String string2,
            @WebParam(name = "String3", partName = "String3")
                    String string3,
            @WebParam(name = "String4", partName = "String4")
                    String string4,
            @WebParam(name = "Long", partName = "Long")
                    long _long,
            @WebParam(name = "Long1", partName = "Long1")
                    long long1);

}
