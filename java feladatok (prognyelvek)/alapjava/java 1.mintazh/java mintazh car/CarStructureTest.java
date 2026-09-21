import check.*;
import static check.Use.*;

import module org.junit.jupiter;

@BeforeAll
public static void init() {
    Use.theClass("rental.Car")
       .that(hasUsualModifiers());
}

@Test
public void fieldBrand() {
    it.hasField("brand: String")
      .that(hasUsualModifiers())
      .thatHasNo(GETTER, SETTER)
      .info("Az autó márkaneve.");
}

@Test
public void fieldLicensePlate() {
    it.hasField("licensePlate: String")
      .that(hasUsualModifiers())
      .thatHasNo(GETTER, SETTER)
      .info("Az autó rendszámtáblája.");
}

@Test
public void fieldPrice() {
    it.hasField("price: double")
      .that(hasUsualModifiers())
      .thatHas(GETTER)
      .thatHasNo(SETTER)
      .info("Az autó kölcsönzési díja.");
}

@Test
public void fieldMAX_PRICE() {
    it.hasField("MAX_PRICE: double")
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, NOT_MODIFIABLE, VISIBLE_TO_NONE)
      .thatHasNo(GETTER, SETTER)
      .info("A maximálisan megengedett kölcsönzési díj egy autó esetében.");
}

@Test
public void constructor() {
    it.hasConstructor(withArgsLikeAllFields())
      .thatIs(INSTANCE_LEVEL, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_NONE)
      .info("Beállítja a megfelelő adattagokat.");
}

@Test
public void methodMake() {
    it.hasMethod("make", withArgsLikeAllFields())
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_ALL)
      .thatReturns("Car")
      .info("Ellenőrzi, hogy a paraméterek megfelelőek, ha igen, akkor létrehozza és visszaadja a paramétereknek megfelelő Car típusú objektumot.")
      .info("Ha a paraméterek nem megfelelőek, akkor null értékkel tér vissza.");
}

@Test
public void methodIsValidCar() {
    it.hasMethod("isValidCar", withArgsLikeAllFields())
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_NONE)
      .thatReturns("boolean")
      .info("A márkanév csak betűkből vagy szóközből állhat és legalább 2 karakter hosszú kell legyen.")
      .info("A rendszámtáblát az isValidLicensePlate() segítségével vizsgálja meg.")
      .info("A kölcsönzés díja pozitív és nem haladhatja meg a MAX_PRICE értékét.")
      .info("A metódusban használható a Character osztály isLetter() metódusa.");
}

@Test
public void methodIsValidLicensePlate() {
    it.hasMethod("isValidLicensePlate", withArgsLikeFields("licensePlate"))
      .thatIs(USABLE_WITHOUT_INSTANCE, FULLY_IMPLEMENTED, MODIFIABLE, VISIBLE_TO_NONE)
      .thatReturns("boolean")
      .info("Megvizsgálja, hogy megfelelő-e egy rendszámtábla.")
      .info("Minden rendszámtábla 7 karakter hosszú: 3 nagybetűből és 3 számjegyből áll, amelyeket egy szóköz választ el egymástól.")
      .info("A metódusban használható a Character osztály isUpperCase() és isDigit() metódusa.");
}

@Test
public void methodDecreasePrice() {
    it.hasMethod("decreasePrice", withArgsLikeFields())
      .that(hasUsualModifiers())
      .thatReturnsNothing()
      .info("Az aktuális autó kölcsönzési díját 10-zel csökkenti, ha az eredeti kölcsönzési díj 10-nél nagyobb.")
      .info("Ha az autó kölcsönzési díja megegyezik a MAX_PRICE értékével, akkor nem csökkenti azt.");
}

@Test
public void methodIsCheaperThan() {
    it.hasMethod("isCheaperThan", withParams("other: Car"))
      .that(hasUsualModifiers())
      .thatReturns("boolean")
      .info("Eldönti, hogy az aktuális autó kölcsönzési díja alacsonyabb-e a paraméterben kapott autó kölcsönzési díjánál.");
}

@Test
public void text() {
    it.has(TEXTUAL_REPRESENTATION)
      .info("A szöveges reprezentáció az árat egy tizedesjegy pontossággal jelenítse meg és az egyes helyiértékek egymás alá kerüljenek.")
      .info("""
          Példa reprezentációk: 
          Volvo (JSD 856) 500,0 EUR
          BMW (ABC 123) 40,0 EUR
          Alfa Romeo (DEF 234) 9,0 EUR
      """);
}

void main() {}

