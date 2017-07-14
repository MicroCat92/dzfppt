/**
 * SajtIssueInvoiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sajt.shdzfp.sl.service;

public class SajtIssueInvoiceServiceLocator extends org.apache.axis.client.Service implements sajt.shdzfp.sl.service.SajtIssueInvoiceService {

    public SajtIssueInvoiceServiceLocator() {
    }


    public SajtIssueInvoiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SajtIssueInvoiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SajtIssueInvoiceServiceHttpSoap11Endpoint
    private java.lang.String SajtIssueInvoiceServiceHttpSoap11Endpoint_address = "http://fw1test.shdzfp.com:7500/axis2/services/SajtIssueInvoiceService.SajtIssueInvoiceServiceHttpSoap11Endpoint/";

    public java.lang.String getSajtIssueInvoiceServiceHttpSoap11EndpointAddress() {
        return SajtIssueInvoiceServiceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName = "SajtIssueInvoiceServiceHttpSoap11Endpoint";

    public java.lang.String getSajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName() {
        return SajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName;
    }

    public void setSajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        SajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName = name;
    }

    public sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType getSajtIssueInvoiceServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SajtIssueInvoiceServiceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSajtIssueInvoiceServiceHttpSoap11Endpoint(endpoint);
    }

    public sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType getSajtIssueInvoiceServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sajt.shdzfp.sl.service.SajtIssueInvoiceServiceSoap11BindingStub _stub = new sajt.shdzfp.sl.service.SajtIssueInvoiceServiceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getSajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSajtIssueInvoiceServiceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        SajtIssueInvoiceServiceHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                sajt.shdzfp.sl.service.SajtIssueInvoiceServiceSoap11BindingStub _stub = new sajt.shdzfp.sl.service.SajtIssueInvoiceServiceSoap11BindingStub(new java.net.URL(SajtIssueInvoiceServiceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getSajtIssueInvoiceServiceHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SajtIssueInvoiceServiceHttpSoap11Endpoint".equals(inputPortName)) {
            return getSajtIssueInvoiceServiceHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sl.shdzfp.sajt", "SajtIssueInvoiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sl.shdzfp.sajt", "SajtIssueInvoiceServiceHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SajtIssueInvoiceServiceHttpSoap11Endpoint".equals(portName)) {
            setSajtIssueInvoiceServiceHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
