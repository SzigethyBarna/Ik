package rental;

public class Car {
    // Adattagok (INSTANCE_LEVEL, VISIBLE_TO_NONE -> private)
    private String brand;
    private String licensePlate;
    private double price;

    // Statikus konstansok
    private static final double MAX_PRICE = 500.0;
    private static final Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE);

    // Konstruktor (VISIBLE_TO_NONE -> private)
    private Car(String brand, String licensePlate, double price) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    // A price mezőhöz tartozik getter
    public double getPrice() {
        return price;
    }

    // Rendszám ellenőrzése
    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.length() != 7) {
            return false;
        }
        
        // 3 nagybetű ellenőrzése
        for (int i = 0; i < 3; i++) {
            if (!Character.isUpperCase(licensePlate.charAt(i))) {
                return false;
            }
        }
        
        // Szóköz ellenőrzése
        if (licensePlate.charAt(3) != ' ') {
            return false;
        }
        
        // 3 számjegy ellenőrzése
        for (int i = 4; i < 7; i++) {
            if (!Character.isDigit(licensePlate.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }

    // Objektum létrehozása validációkkal
    public static Car make(String brand, String licensePlate, double price) {
        // Márka validáció
        if (brand == null || brand.length() < 2) {
            return null;
        }
        for (int i = 0; i < brand.length(); i++) {
            char c = brand.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return null;
            }
        }

        // Rendszám validáció
        if (!isValidLicensePlate(licensePlate)) {
            return null;
        }

        // Ár validáció
        if (price <= 0 || price > MAX_PRICE) {
            return null;
        }

        return new Car(brand, licensePlate, price);
    }

    // Árcsökkentés
    public void decreasePrice() {
        if (this.price > 10.0 && this.price != MAX_PRICE) {
            this.price -= 10.0;
        }
    }

    // Árösszehasonlítás
    public boolean isCheaperThan(Car other) {
        if (other == null) {
            return false;
        }
        return this.price < other.price;
    }

    // Szöveges reprezentáció
    @Override
    public String toString() {
        // A %5.1f biztosítja az 1 tizedesjegyet és az 5 karakteres szélességet.
        // A magyar területi beállítások miatt (a tesztelőben) automatikusan vesszőt fog használni.
        return "%s (%s) %5.1f EUR".formatted(brand, licensePlate, price);
    }
}