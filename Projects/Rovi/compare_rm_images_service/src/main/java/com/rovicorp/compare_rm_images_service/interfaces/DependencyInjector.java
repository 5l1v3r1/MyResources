package com.rovicorp.compare_rm_images_service.interfaces;

/*
 * Obtains all required dependencies from ${ROVI_BASE}
 */

public interface DependencyInjector {
	//Application Server Params
	public String 	getPortNumber();
	
	//MSSQL Connector Params
    public String 	getUsername();
    public String 	getPassword();
	public String 	getProdServerName();
	public String 	getQAServerName();
	
	//Tulsa Query Params
	public String 	getProdRandomImageQuery();
	public String   getQASpecificImageQuery();
	public String	getProdSpecificImageQuery();
	public String 	getProdRecentImageQuery();
    public int 		getProdRecentNumberOfDays();
    
    //Server URL Params
    public String 	getAwsUatUrl();
    public String 	getAwsDevUrl();
    public String 	getAwsQaUrl();
    public String	getTulsaServerUrl();

}
