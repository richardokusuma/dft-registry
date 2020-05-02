package id.co.cimb.eureka.responses;

public class CommonResponse {
    private String message;
    private Object body;
    public CommonResponse(String message, Object body){
        this.message = message;
        this.body = body;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void setBody(Object body){
        this.body = body;
    }
    public Object getBody(){
        return body;
    }
}
