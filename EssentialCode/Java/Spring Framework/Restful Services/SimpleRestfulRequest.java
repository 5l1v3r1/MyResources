import java.util.Date;

@JsonIgnoreProperties
public class SimpleRestfulRequest{
	
	private String SimpleJsonParam;
	private String SimplerJsonParam;
	private String SimpleJsonDate;
	
	public void setSimpleJsonParam( String SimpleJsonParam){
		this.SimpleJsonParam = SimpleJsonParam
	}
	
	public String getSimpleJsonParam(){
		return this.SimpleJsonParam;
	}
	
	public void setSimplerJsonParam( String SimplerJsonParam){
		this.SimplerJsonParam = SimplerJsonParam;
	}
	
	public String getSimplerJsonParam( String SimplerJsonParam){
		return this.SimplerJsonParam;
	}
	
	public void setSimpleJsonDate( String SimpleJsonDate ){
		this.SimpleJsonDate = SimpleJsonDate;
	}
	
	public Date getSimpleJsonDate(){
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:aaa");
		return (Date) dateformat.parse(this.SimpleJsonDate);
	}	
}