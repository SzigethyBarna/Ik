import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    // usedLang = Lang.EN; // uncomment to enforce the message language
    Use.theClass("smarthaven.SmartHaven")
       .that(hasUsualModifiers());
}

@Test
public void fieldDevices() {
    it.hasField("devices: ArrayList of smarthaven.util.BaseModel")
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER)
      .info("A kezelt eszközök.");
}

@Test
public void constructor() {
    it.hasConstructor(withParams("devices: array of smarthaven.util.BaseModel"))
      .thatIs(VISIBLE_TO_ALL)
      .info("Feltölti a kezelt eszközöket.");
}

@Test
public void methodRun() {
    it.hasMethod("run", withNoParams())
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("""
          Addig olvas soronként a sztenderd bemenetről, amíg `done` szöveget nem kap.
          A beolvasott utasításokat a `doStep` segítségével hajtja végre, és kiírja annak eredményét.
          Minden lépés végrehajtása után meghívja a `status` metódust, és kiírja az eredményét.
      """)
      .info("Tipp: egy `StringBuilder` kiüríthető így: `sb.setLength(0)`");
}

@Test
public void methodRunCommands() {
    it.hasMethod("runCommands", withParams("commands: array of String"))
      .that(hasUsualModifiers())
      .thatReturns("String")
      .info("""
          A `runCommand` segítségével végrehajtja a parancsokat, és összegyűjti a kimenetüket egy `StringBuilder` felhasználásával.
          Minden parancs végrehajtása után meghívja a `printStatus` metódust.
      """)
      .testWith(testCase("testDefault"), "Kipróbálja a `makeDefault` konfigurációt különféle parancsokkal.");
}

@Test
public void methodRunFile() {
    it.hasMethod("runFile", withParams("filename: String"))
      .that(hasUsualModifiers())
      .thatReturns("String")
      .info("""
          A `runCommand` segítségével végrehajtja a fájl minden egyes sorában kapott parancsot.
          Ha a fájl nem elérhető, a kimenet a "File not found." szöveg.
      """)
      .testWith(testCase("testDefaultWithFile"), "Kipróbálja a `makeDefault` konfigurációt az `input.txt` bemenetével.")
      .testWith(testCase("testMissingFile"), "Kipróbálja metódust egy nem létező fájlnévvel.");
}

@Test
public void methodRunCommand() {
    it.hasMethod("runCommand", withParams("step: String"))
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_NONE)
      .thatReturns("String")
      .info("""
          A következő alakú sorok érkezhetnek parancsként.
          Feltételezhető, hogy az eszközök sorszáma érvényes.
          Ha a megnevezett sorszámú eszköz nem képes végrehajtani a parancsot, nincsen hatása.
          A kimenet mutassa a parancs hatását.
          - 3 action: A 3-as számú eszköz végrehajt egy műveletet. A kimenet ennek a leírása.
          - 2 light <szín>: Megadott színűre kapcsolja az eszközt.
          - 2 <on/off>: Fel- vagy lekapcsolja az eszközt.
      """);
}

@Test
public void methodPrintStatus() {
    it.hasMethod("printStatus", withParams("sb: StringBuilder"))
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_NONE)
      .thatReturnsNothing()
      .info("Mindegyik eszköz szöveges alakját adja vissza, soronként.");
}

@Test
public void methodMakeDefault() {
    it.hasMethod("makeDefault", withNoParams())
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("SmartHaven")
      .thatThrows("smarthaven.util.UnsupportedDeviceException")
      .info("Létrehoz egy konfigurációt különböző eszközökből.");
}

@Test
public void methodMain() {
    it.hasMethod("main", withParams("args: array of String"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturnsNothing()
      .thatThrows("smarthaven.util.UnsupportedDeviceException")
      .info("A `makeDefault()` konfigurációt használja fel.");
}

void main() {}


