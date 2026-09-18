import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("smarthaven.device.InteractiveDevice", withParent("smarthaven.util.BaseModel"), withInterface("smarthaven.util.IotFunction"))
       .that(hasUsualModifiers());
}

@Test
public void fieldConnectedDevices() {
    it.hasField("connectedDevices: ArrayList of smarthaven.util.BaseModel")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHas(GETTER, "Ügyel az enkapszulációra: a lista másolatát adja vissza.")
      .thatHasNo(SETTER)
      .info("""
          Az eszközzel összepárosított eszközök listája.
          Ez lehetővé teszi a lámpák szinkronizálását.
      """);
}

@Test
public void fieldLightColor() {
    it.hasField("lightColor: String")
      .that(hasUsualModifiers())
      .thatHas(GETTER, SETTER);
}

@Test
public void constructor() {
    it.hasConstructor(withParams("identifer: String", "deviceType: Category"))
      .thatIs(VISIBLE_TO_ALL)
      .testWith(testCase("testInitialization"), "Ellenőrizzük a kezdeti beállításokat.");
}

@Test
public void methodLink() {
    it.hasMethod("link", withParams("devices: array of smarthaven.util.BaseModel"))
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("""
          A paraméterben kapott eszközöket hozzáadja a hosszákapcsolt eszközökhöz.
          Ha egy eszköz már szerepel a listában, azt nem adja hozzá többször.
      """)
      .testWith(testCase("testLink"), "Új eszközhöz nem kapcsolódik másik.")
      .testWith(testCase("testLink"), """
          Ellenőrizzük, hogy az eszközhöz rendben hozzákapcsolhatók másik eszközök,
          és ha valamelyiket egynél többször adjuk őket hozzá, nem jelenik meg újra.
      """);
}

@Test
public void methodSyncLights() {
    it.hasMethod("syncLights", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("""
          Az eszközhöz kapcsolt FÉNYFORRÁSOK színét beállítja az eszköz saját színére,
          a többi eszközt nem módosítja.
      """)
      .testWith(testCase("testSyncLights"), """
          Készítsünk 4 eszközt:
          - device1 LIGHT, piros
          kössük ezeket hozzá:
          - device2 LED_STRIP, kék
          - device3 LIGHT, sárga
          - device4 TV
          
          Állítsuk device1 színét zöldre.
          Ellenőrizzük, hogy a többi eszköz színe nem változott.
          
          Hívjuk meg a syncLights-t device1-
      """);
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .testWith(testCase("testText"), """
          Ellenőrizzük, hogy az osztály szöveges reprezentációja így néz ki:
          Device: InteractiveDevice, Type: LED_STRIP, Identifier: led2-IDx, PowerStatus: false, Color: red
      """);
}

void main() {}


