//Remember, this entity calls a stored procedure in the database.
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ParameterMode;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQuery(
		name="GetSimpleData", 
		procedureName="simple_stored_procedure_name", 
		resultClasses=SimpleRestfulEntity.class,
		@StoredProcedureParameter(mode = ParameterMode.IN, name="ParameterName", type = Integer.class)
		@StoredProcedureParameter(mode = ParameterMode.IN, name="Date", type = Date.class)
	)
		
@Entity
public class SimpleRestfulEntity {
	@Id
	@Column(name="db_column_name")
	private long ColumnName;
	
	@Column(name="creation_date")
	private String CreationDate;
	
	public void setColumnName(long ColumnName){
		this.ColumnName = ColumnName;
	}
	
	public long getColumnName(){
		return this.ColumnName;
	}
	
	public void setCreationDate(String CreationDate){
		this.CreationDate = CreationDate;
	}
	
	public long getCreationDate(){
		return this.CreationDate;
	}
}