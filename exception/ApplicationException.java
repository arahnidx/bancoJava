package br.com.poo.sysfi.exception;

public class ApplicationException extends Exception{
    private static final long serialVersionUID = 1L;
	private String originClassName;
    private String originMethodName;
    private String message;
        
    public ApplicationException(String originClassName, String originMethodName, String message){
        this.originClassName = originClassName;
        this.originMethodName = originMethodName; 
        this.message = message; 
    }

    /**
     * @return the originClassName
     */
    public String getOriginClassName() {
        return originClassName;
    }

    /**
     * @return the originMethodName
     */
    public String getOriginMethodName() {
        return originMethodName;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
    
}

