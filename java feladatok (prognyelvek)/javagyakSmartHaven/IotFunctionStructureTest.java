import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theInterface("smarthaven.util.IotFunction")
       .thatIs(INSTANCE_LEVEL, NOT_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL);
}

@Test
public void methodTurnOn() {
    it.hasMethod("turnOn", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing();
}

@Test
public void methodTurnOff() {
    it.hasMethod("turnOff", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing();
}

void main() {}


