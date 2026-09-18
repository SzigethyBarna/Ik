import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@Test
public void elements() {
    Use.theEnum("smarthaven.device.Category")
       .ofEnumElements("LIGHT", "LED_STRIP", "TV", "FRIDGE", "COFFEE_MACHINE")
       .that(hasUsualModifiers())
       .info("Az okosotthon elemeinek különböző kategóriái.");
}

void main() {}


