package sajt.shdzfp.sl.service;


import org.apache.axis2.client.ServiceClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class ECInterfaceSl
{

//	private static final Logger log = LoggerFactory.getLogger(ECInterfaceSl.class);
	private String targetEndpoint;
	private long timeoutInMilliSeconds;

	public ECInterfaceSl(String targetEndpoint)
	{
		timeoutInMilliSeconds = 0x493e0L;
		this.targetEndpoint = targetEndpoint;
	}

	public ECInterfaceSl(String targetEndpoint, long timeoutInSeconds)
	{
		timeoutInMilliSeconds = 0x493e0L;
		this.targetEndpoint = targetEndpoint;
		if (timeoutInSeconds < 5L)
			timeoutInSeconds = 5L;
		timeoutInMilliSeconds = timeoutInSeconds * 1000L;
	}

	public String getDSInfo(String con)
	{
		return invokeDSInfo(con);
	}

	private String invokeDSInfo(String con)
	{
		String result = "";
		SajtIssueInvoiceServiceStub.EiInterface intface = new SajtIssueInvoiceServiceStub.EiInterface();
		intface.setIn0(con);
		System.out.println("XML:"+con);
		try
		{
			SajtIssueInvoiceServiceStub ws = new SajtIssueInvoiceServiceStub(targetEndpoint);
			ServiceClient client = ws._getServiceClient();
			client.getOptions().setTimeOutInMilliSeconds(timeoutInMilliSeconds);
			SajtIssueInvoiceServiceStub.EiInterfaceResponse pr = ws.eiInterface(intface);
			result = pr.get_return();
		}
		catch (Exception e)
		{
//			log.error((new StringBuilder("调用接口失败：")).append(e.getMessage()).toString());
			System.out.println((new StringBuilder("eee:")).append(e.getMessage()).toString());
		}
		return result;
	}

}
