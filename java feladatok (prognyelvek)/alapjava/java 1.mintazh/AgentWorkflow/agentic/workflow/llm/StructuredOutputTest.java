package agentic.workflow.llm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StructuredOutputTest {

    @Test
    public void testContainsExistingType() {
        StructuredOutput out = new StructuredOutput(SchemaType.INT, SchemaType.STRING);
        assertTrue(out.contains(SchemaType.INT));
        assertTrue(out.contains(SchemaType.STRING));
    }

    @Test
    public void testContainsMissingType() {
        StructuredOutput out = new StructuredOutput(SchemaType.BOOLEAN);
        assertFalse(out.contains(SchemaType.INT));
    }

    @Test
    public void testSize() {
        StructuredOutput out = new StructuredOutput(SchemaType.INT, SchemaType.STRING, SchemaType.BOOLEAN);
        assertEquals(3, out.size());
    }
}