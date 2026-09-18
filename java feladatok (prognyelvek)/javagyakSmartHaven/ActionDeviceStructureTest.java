import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("smarthaven.device.ActionDevice", withParent("smarthaven.util.BaseModel"))
       .that(hasUsualModifiers());
}

@Test
public void fieldActionToSteps() {
    it.hasField("actionToSteps: HashMap of String to array of String")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER)
      .info("Műveletek típusai és azok lépéseinek leírása. Kezdetben üres.");
}

@Test
public void fieldActionIdx() {
    it.hasField("actionIdx: HashMap of String to Integer")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER);
}

@Test
public void constructor() {
    it.hasConstructor(withParams("identifer: String", "deviceType: Category"))
      .thatIs(VISIBLE_TO_ALL)
      .thatThrows("smarthaven.util.UnsupportedDeviceException")
      .info("""
          Az `ActionDevice` **nem** tartozhat `LED_STRIP` vagy `LIGHT` kategóriába,
          ilyen bemenetre `UnsupportedDeviceException` vált ki.
      """);
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .testWith(testCase("testText"), """
          Ellenőrizzük, hogy az osztály szöveges reprezentációja így néz ki:
          Device: ActionDevice, Type: LIGHT, Identifier: led1-IDx, PowerStatus: false, 2 actions with 3 steps
      """);
}

@Test
public void methodTotalStepCount() {
    it.hasMethod("totalStepCount", withNoParams())
      .that(hasUsualModifiers())
      .thatReturns("int")
      .info("Az összes akció lépésszáma együttvéve.")
      .testWith(testCase("testText"), "A szöveges reprezentáció felhasználja.");
}

@Test
public void methodSetAction() {
    it.hasMethod("setAction", withParams("actionType: String", "details: String"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .thatThrows("IllegalArgumentException", "Ha a leírás üres.")
      .info("Hozzárendeli egy akciófajtához a részleteket, amelyek pontosvesszővel vannak elválasztva.");
}

@Test
public void methodPerformAction() {
    it.hasMethod("performAction", withParams("actionType: String"))
      .that(hasUsualModifiers())
      .thatReturns("String")
      .info("""
          Ha `actionType` ismerelen akciót nevez meg, az eredmény "Unknown action".
          Különben "Performed: <akció neve>", és az akció `actionIdx` számlálója lép egyet;
          az utolsó akció után újra az első jön.
      """)
      .testWith(testCase("testPerformAction"), """
          Ellenőrzendő, hogy két- és egy háromlépéses akció többszöri végrehajtása rendben megy-e,
          ismeretlen akciónevet is használva.
      """);
}

void main() {}


