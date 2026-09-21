package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {

    @Test
    public void testStepCount() {
        Agent agent = new Agent("Teszt");
        assertEquals(0, agent.getStepCount());
        agent.addStep(new WorkflowStep("1", "p", "sp", new StructuredOutput(SchemaType.INT)));
        assertEquals(1, agent.getStepCount());
    }

    @Test
    public void testAddDuplicateStepRejected() {
        Agent agent = new Agent("Teszt");
        agent.addStep(new WorkflowStep("Step1", "p", "sp", new StructuredOutput(SchemaType.INT)));
        
        assertThrows(IllegalArgumentException.class, () -> {
            agent.addStep(new WorkflowStep("Step1", "p2", "sp2", new StructuredOutput(SchemaType.STRING)));
        });
    }

    @Test
    public void findStepByName() {
        Agent agent = new Agent("Teszt");
        WorkflowStep step = new WorkflowStep("Foglalas", "p", "sp", new StructuredOutput(SchemaType.INT));
        agent.addStep(step);
        
        assertEquals(step, agent.findStepByName("Foglalas"));
        assertEquals(step, agent.findStepByName("  Foglalas  ")); // szóközzel
    }

    @Test
    public void findStepByNameMissing() {
        Agent agent = new Agent("Teszt");
        agent.addStep(new WorkflowStep("Foglalas", "p", "sp", new StructuredOutput(SchemaType.INT)));
        assertNull(agent.findStepByName("Lemosas"));
    }

    @Test
    public void testLoadAgentSuccess() throws Exception {
        Agent agent = Agent.loadAgent("agent_HotelBooker.txt");
        assertEquals("HotelBooker", agent.getName());
        assertEquals(2, agent.getStepCount());
        assertNotNull(agent.findStepByName("Kereses"));
    }

    @Test
    public void testLoadAgentRejectsMissingHeader() {
        assertThrows(WorkflowFormatException.class, () -> {
            Agent.loadAgent("agent_BadHeader.txt");
        });
    }

    @Test
    public void testLoadAgentRejectsDuplicateStepNames() {
        assertThrows(WorkflowFormatException.class, () -> {
            Agent.loadAgent("agent_DuplicateSteps.txt");
        });
    }
}