package agentic.workflow.llm;

import java.util.Arrays;
public class StructuredOutput {

    private final SchemaType[] schemaTypes;

    public StructuredOutput(SchemaType... schemaTypes) {
            if (schemaTypes == null || schemaTypes.length == 0) {
                throw new IllegalArgumentException("Legalább egy sématípust meg kell adni.");
            }
            for (SchemaType st : schemaTypes) {
                if (st == null) {
                    throw new NullPointerException("A megadott sématípusok között nem lehet null.");
                }
            }
            this.schemaTypes = Arrays.copyOf(schemaTypes, schemaTypes.length);
        }
        
    public SchemaType[] getSchemaTypes() {
        return Arrays.copyOf(schemaTypes, schemaTypes.length);
    }

    public boolean contains(SchemaType schemaType) {
        for (SchemaType st : schemaTypes) {
            if (st == schemaType) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return schemaTypes.length;
    }

}