import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("smarthaven.util.BaseModel", withInterface("smarthaven.util.IotFunction"))
       .that(hasUsualModifiers());
}

@Test
public void fieldPowerStatus() {
    it.hasField("powerStatus: boolean")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
      .thatHas(GETTER)
      .thatHasNo(SETTER);
}

@Test
public void fieldIdentifier() {
    it.hasField("identifier: String")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_SUBCLASSES)
      .thatHas(GETTER)
      .thatHasNo(SETTER);
}

@Test
public void fieldDeviceType() {
    it.hasField("deviceType: smarthaven.device.Category")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER)
      .thatHasNo(SETTER);
}

@Test
public void constructor() {
    it.hasConstructor(withArgsLikeFields("identifier", "deviceType"))
      .thatIs(VISIBLE_TO_ALL)
      .info("""
          Beállítja az adattagokat a paraméterek alapján.
          Kezdetben a `powerStatus` legyen hamis.
          Ha az azonosító `null`, váltson ki `IllegalArgumentException` kivételt.
      """)
      .testWith(testCase("testInitialization"), "Ellenőrizzük a fenti viselkedést.");
}

@Test
public void methodTurnOn() {
    it.hasMethod("turnOn", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("Igazra állítja: `powerStatus`.")
      .testWith(testCase("testTurnOnAndOff"), "Ellenőrzi a ki- és bekapcsolást.");
}

@Test
public void methodTurnOff() {
    it.hasMethod("turnOff", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("Hamisra állítja: a powerStatus`.")
      .testWith(testCase("testTurnOnAndOff"), "Ellenőrzi a ki- és bekapcsolást.");
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .info("""
          Az osztály szöveges reprezentációja a következő formátumot követi:
          `Device: <eszközfajta>, Type: <kategória>, Identifier: <azonosító>, PowerStatus: <állapot>`.
          Az eszközfajtát ez a hívás adja meg: ˙this.getClass().getSimpleName()˙.
      """)
      .testWith(testCase("testText"), """
          Ellenőrizzük az alábbiakat:
          Device: BaseModel, Type: FRIDGE, Identifier: FRIDGE1-IDx, PowerStatus: true
      """);
}

void main() {}


