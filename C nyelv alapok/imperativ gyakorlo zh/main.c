#include "buszmegallo.h"

// Segédfüggvény a menü megjelenítéséhez
void display_menu() {
    printf("\n*** FŐMENÜ ***\n");
    printf("1) Térkép      - Az aktuális város buszmegállóinak megjelenítése térképen\n");
    printf("2) Lista       - Az aktuális város buszmegállóinak felsorolása\n");
    printf("3) Új megálló  - Új buszmegálló létrehozása és hozzáadása\n");
    printf("4) Megálló törlés - Megadott sorszámú buszmegálló törlése\n");
    printf("5) Mentés      - Az aktuális lista fájlba mentése\n");
    printf("6) Betöltés    - Korábban mentett lista betöltése\n");
    printf("7) Útvonal     - Útvonal rajzolása két kiválasztott állomás között\n");
    printf("8) Kilépés     - A program befejezése\n");
    printf("-----------------\n");
}

int main() {
    // A buszmegállók listáját tároló struktúra pointere
    BusStopList *my_list = init_list();
    int choice = 0;
    
    // Köszöntés és ismertetés
    printf("=========================================================\n");
    printf("👋 Üdvözlünk a Buszmegálló Menedzser Programban!\n");
    printf("Ez a program a kisvárosi buszmegállókat tartja számon.\n");
    printf("Képes vagy megállókat hozzáadni, törölni, a listát menteni/betölteni,\n");
    printf("valamint egy 10x10-es térképen megjeleníteni azokat, útvonallal együtt.\n");
    printf("=========================================================\n");

    do {
        display_menu();
        printf("Kérem válasszon egy menüpontot (1-8): ");
        
        // Bemenet olvasása
        if (scanf("%d", &choice) != 1) {
            printf("\n⚠️ Hiba: Érvénytelen bemenet. Kérem adjon meg egy számot (1-8).\n");
            // Puffer tisztítása, hogy ne legyen végtelen ciklus
            while (getchar() != '\n'); 
            choice = 0; // Újra beolvasás
            continue;
        }
        // Tisztítás a scanf után
        while (getchar() != '\n'); 

        // Menüpontok kezelése
        switch (choice) {
            case 1: // Térkép
                // show_map hívása NULL pointerekkel, ha nincs útvonal (alap funkció)
                show_map(my_list, NULL, NULL); 
                break;
            case 2: // Lista
                print_bus_stop(my_list);
                break;
            case 3: // Új megálló
                create_bus_stop(my_list);
                break;
            case 4: // Megálló törlés
                delete_bus_stop(my_list);
                break;
            case 5: // Mentés
                save_list(my_list);
                break;
            case 6: { // Betöltés (külön blokk a lokális változók miatt)
                // Betöltés előtt a régi listát felszabadítjuk, ahogy a betöltés új adatkészletet jelent.
                free_list(my_list);
                my_list = init_list(); // Új, üres lista inicializálása
                load_list(my_list);
                break;
            }
            case 7: // Útvonal
                fastest_road(my_list);
                break;
            case 8: // Kilépés
                printf("\n👋 A program befejeződik. Viszlát!\n");
                break;
            default:
                printf("\n⚠️ Hiba: Nem létező menüpont (%d). Kérem válasszon újra (1-8).\n", choice);
                break;
        }

    } while (choice != 8);

    // Memória felszabadítása a program befejezése előtt (megelőzzük a memóriaszivárgást)
    free_list(my_list);

    return 0;
}