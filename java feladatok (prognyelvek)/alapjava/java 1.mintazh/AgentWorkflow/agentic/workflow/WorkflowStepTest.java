package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkflowStepTest {

    @Test
    public void testExpectsStructuredOutput() {
        WorkflowStep step = new WorkflowStep("Step1", "Csinálj x-et", "Rendszer", new StructuredOutput(SchemaType.INT));
        assertTrue(step.expectsStructuredOutput());
    }

    @Test
    public void testSimulateResponseByPrimaryType() {
        WorkflowStep step1 = new WorkflowStep("1", "p", "sp", new StructuredOutput(SchemaType.INT));
        assertEquals("0", step1.simulateResponse());

        WorkflowStep step2 = new WorkflowStep("2", "p", "sp", new StructuredOutput(SchemaType.MAP_STRING_STRING));
        assertEquals("{\"kulcs\":\"érték\"}", step2.simulateResponse());
        
        WorkflowStep step3 = new WorkflowStep("3", "p", "sp", new StructuredOutput(SchemaType.STRING, SchemaType.INT));
        assertEquals("sample", step3.simulateResponse());
    }
}