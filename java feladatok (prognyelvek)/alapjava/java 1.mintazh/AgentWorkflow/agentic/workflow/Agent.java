package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agent {
    private String name;
    private final List<WorkflowStep> steps = new ArrayList<>();

    public Agent(String name) {
        setName(name);
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException(" nem lehet null, üres vagy csak szóköz.");
        
        this.name = name;
    }

    public WorkflowStep findStepByName(String stepName) {
        if (stepName == null || stepName.trim().isEmpty()) {
            throw new IllegalArgumentException("A keresett lépés neve nem lehet üres.");
        }
        String searchName = stepName.trim();
        for (WorkflowStep step : steps) {
            if (step.getName().equals(searchName)) {
                return step;
            }
        }
        return null;
    }

    public List<WorkflowStep> getSteps() {
        return new ArrayList<>(steps);
    }

    public void addStep(WorkflowStep step) {
        if (step == null) throw new IllegalArgumentException(" nem lehet null.");
        
        if (findStepByName(step.getName()) != null) throw new IllegalArgumentException("Már létezik ilyen nevű lépés.");
        
        steps.add(step);
    }

    public int getStepCount() {
        return steps.size();
    }

    public void run() {
        for(WorkflowStep step : steps){
            System.out.println("Lépés: "+ step.getName() + "mintaválasz: "+ step.simulateResponse());
        }
    }
    
    public static Agent loadAgent(String filename) throws WorkflowFormatException,IOException{
        if (filename == null || filename.trim().isEmpty()) throw new IllegalArgumentException("nem lehet üres");
        

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String firstLine = reader.readLine();
            while(firstLine != null && firstLine.trim().isEmpty()){
                firstLine = reader.readLine();
            }
            if(firstLine == null || !firstLine.trim().startsWith("AGENT:")) throw new WorkflowFormatException("hianyos AGENT szo");

            String aName = firstLine.trim().substring("AGENT:".length()).trim();
            Agent agent = new Agent(aName);

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.equals("STEP")) {
                    WorkflowStep step = parseStep(reader);
                    try {
                        agent.addStep(step);
                    } catch (IllegalArgumentException e) {
                        throw new WorkflowFormatException("duplikált lépésnév" + e);
                    }
                } else {
                    throw new WorkflowFormatException("Váratlan sor" + line);
                }
            }
            return agent;
        }
    }

    private static WorkflowStep parseStep(BufferedReader reader) throws IOException, WorkflowFormatException {
        String name = null, prompt = null, systemPrompt = null, outputStr = null;
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.equals("ENDSTEP")) {
                if (name == null || prompt == null || systemPrompt == null || outputStr == null) {
                    throw new WorkflowFormatException("hiányzó tulajdonság a lépésben");
                }
                
                SchemaType type;
                try {
                    type = SchemaType.valueOf(outputStr);
                } catch (IllegalArgumentException e) {
                    throw new WorkflowFormatException("érvénytelen sématípus" +  e);
                }
                
                StructuredOutput output = new StructuredOutput(type);
                try {
                    return new WorkflowStep(name, prompt, systemPrompt, output);
                } catch (IllegalArgumentException e) {
                    throw new WorkflowFormatException("hibás adatok"+ e);
                }
            }
            if (line.startsWith("name=")) name = line.substring("name=".length()).trim();
            else if (line.startsWith("prompt=")) prompt = line.substring("prompt=".length()).trim();
            else if (line.startsWith("systemPrompt=")) systemPrompt = line.substring("systemPrompt=".length()).trim();
            else if (line.startsWith("output=")) outputStr = line.substring("output=".length()).trim();
            else throw new WorkflowFormatException("ismeretlen sor" );
        }
        throw new WorkflowFormatException("hiányzó ENDSTEP a fájl végén.");
    }
}