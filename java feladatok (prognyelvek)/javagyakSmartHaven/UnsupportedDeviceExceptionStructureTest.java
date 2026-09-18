import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theCheckedException("smarthaven.util.UnsupportedDeviceException")
       .that(hasUsualModifiers());
}

@Test
public void constructor() {
    it.hasConstructor(withParams("message: String"))
      .thatIs(VISIBLE_TO_ALL);
}

void main() {}


