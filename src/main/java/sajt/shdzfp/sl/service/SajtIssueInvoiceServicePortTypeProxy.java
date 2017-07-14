package sajt.shdzfp.sl.service;

public class SajtIssueInvoiceServicePortTypeProxy implements sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType {
  private String _endpoint = null;
  private sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType sajtIssueInvoiceServicePortType = null;
  
  public SajtIssueInvoiceServicePortTypeProxy() {
    _initSajtIssueInvoiceServicePortTypeProxy();
  }
  
  public SajtIssueInvoiceServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSajtIssueInvoiceServicePortTypeProxy();
  }
  
  private void _initSajtIssueInvoiceServicePortTypeProxy() {
    try {
      sajtIssueInvoiceServicePortType = (new sajt.shdzfp.sl.service.SajtIssueInvoiceServiceLocator()).getSajtIssueInvoiceServiceHttpSoap11Endpoint();
      if (sajtIssueInvoiceServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sajtIssueInvoiceServicePortType != null)
      ((javax.xml.rpc.Stub)sajtIssueInvoiceServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortType getSajtIssueInvoiceServicePortType() {
    if (sajtIssueInvoiceServicePortType == null)
      _initSajtIssueInvoiceServicePortTypeProxy();
    return sajtIssueInvoiceServicePortType;
  }
  
  public java.lang.String eiInterface(java.lang.String in0) throws java.rmi.RemoteException{
    if (sajtIssueInvoiceServicePortType == null)
      _initSajtIssueInvoiceServicePortTypeProxy();
    return sajtIssueInvoiceServicePortType.eiInterface(in0);
  }
  
  
}