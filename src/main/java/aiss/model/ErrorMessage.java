package aiss.model;

public class ErrorMessage {

	private String errorMessage;
	private Integer code;
	private String documentation;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String errorMessage, Integer code, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.code = code;
		this.documentation = documentation;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getDocumentation() {
		return documentation;
	}


	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}
