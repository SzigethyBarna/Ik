package agentic.workflow;

public class WorkflowFormatException extends Exception{
    public WorkflowFormatException(String msg){
        super(msg);
    }

    public WorkflowFormatException(String msg, Throwable c){
        super(msg, c);
    }
}