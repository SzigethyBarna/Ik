package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;

public class WorkflowStep {
    private String name;
    private String prompt;
    private String systemPrompt;
    private StructuredOutput structuredOutput;

    public WorkflowStep(String name, String prompt, String systemPrompt, StructuredOutput structuredOutput) 
    {
        setName(name);
        setPrompt(prompt);
        setSystemPrompt(systemPrompt);
        setStructuredOutput(structuredOutput);
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("nem lehet üres.");
        this.name = name;
    }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) throw new IllegalArgumentException("nem lehet üres.");
        this.prompt = prompt;
    }

    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) {
        if (systemPrompt == null || systemPrompt.trim().isEmpty()) throw new IllegalArgumentException("nem lehet üres.");
        this.systemPrompt = systemPrompt;
    }

    public StructuredOutput getStructuredOutput() { return structuredOutput; }
    public void setStructuredOutput(StructuredOutput structuredOutput) {
        if (structuredOutput == null) throw new IllegalArgumentException("nem lehet null.");
        this.structuredOutput = structuredOutput;
    }

    public boolean expectsStructuredOutput(){
        return structuredOutput != null && structuredOutput.size() > 0;
    }

    public String simulateResponse(){
        SchemaType primary = structuredOutput.getSchemaTypes()[0];

        return switch (primary) {
            case INT -> "0";
            case STRING -> "sample";
            case BOOLEAN -> "true";
            case LIST_INT -> "[1,2,3]";
            case LIST_STRING -> "[\"a\",\"b\"]";
            case MAP_STRING_STRING -> "{\"kulcs\":\"érték\"}";
        };
    }
}