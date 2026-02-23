#include "buszmegallo.h"

// Inicializálja a listát
BusStopList *init_list() {
    // Foglalás a BusStopList struktúrának
    BusStopList *list = (BusStopList *)malloc(sizeof(BusStopList));
    if (list == NULL) {
        perror("Hiba: Sikertelen memóriafoglalás a listához");
        exit(EXIT_FAILURE);
    }
    // Kezdeti állapot beállítása
    list->count = 0;
    list->capacity = 5; // Kezdeti kapacitás, amit majd bővítünk
    // Foglalás a megállókat tároló tömbnek
    list->stops = (BusStop *)malloc(list->capacity * sizeof(BusStop));
    if (list->stops == NULL) {
        perror("Hiba: Sikertelen memóriafoglalás a megállók tömbjéhez");
        free(list); // Ne felejtsük el felszabadítani a listát, ha a stops foglálása sikertelen
        exit(EXIT_FAILURE);
    }
    return list;
}

// Felszabadítja a teljes memóriát
void free_list(BusStopList *list) {
    if (list == NULL) return;

    // Végigmegyünk a listán és felszabadítjuk MINDEN megálló NEVÉT
    for (int i = 0; i < list->count; i++) {
        free(list->stops[i].name);
    }

    // Felszabadítjuk a megállókat tároló TÖMBÖT
    free(list->stops);

    // Felszabadítjuk a BusStopList struktúrát
    free(list);
}

// Segédfüggvény: Ellenőrzi, hogy a koordináta 1 és MAP_SIZE (10) között van-e.
static int is_valid_coord(int coord) {
    return coord >= 1 && coord <= MAP_SIZE;
}

// Segédfüggvény: Dinamikus tömb bővítése (ha megtelik)
static void expand_list(BusStopList *list) {
    if (list->count == list->capacity) {
        // Kapacitás duplázása
        list->capacity *= 2; 
        // Újrafoglalás (realloc)
        BusStop *new_stops = (BusStop *)realloc(list->stops, list->capacity * sizeof(BusStop));
        if (new_stops == NULL) {
            perror("Hiba: Sikertelen memóriabővítés (realloc)");
            // Itt kritikus hiba van, jobb kilépni.
            exit(EXIT_FAILURE); 
        }
        list->stops = new_stops;
    }
}

// -----------------------------------------------------------
// 1) Térkép megjelenítése (show_map)
// -----------------------------------------------------------

void show_map(BusStopList *list, const BusStop *start, const BusStop *end) {
    if (list->count == 0 && (start == NULL || end == NULL)) {
        printf("\n=> Nincs megjeleníthető buszmegálló a térképen!\n");
        return;
    }

    // 1. Térkép inicializálása (10x10-es mátrix)
    char map[MAP_SIZE][MAP_SIZE];
    for (int i = 0; i < MAP_SIZE; i++) {
        for (int j = 0; j < MAP_SIZE; j++) {
            map[i][j] = ' '; // Minden mező üres (space)
        }
    }

    // 2. Buszmegállók elhelyezése
    for (int i = 0; i < list->count; i++) {
        // Koordináták 1-től indulnak, a C tömb index 0-tól. 
        // Tehát a térképen az (y-1, x-1) pozícióra tesszük a 'B'-t.
        if (is_valid_coord(list->stops[i].x) && is_valid_coord(list->stops[i].y)) {
            map[list->stops[i].y - 1][list->stops[i].x - 1] = 'B';
        }
    }

    // 3. Útvonal rajzolása (ha mindkét pointer érvényes)
    if (start != NULL && end != NULL) {
        // X és Y koordináták a 0-9 tartományban
        int x1 = start->x - 1;
        int y1 = start->y - 1;
        int x2 = end->x - 1;
        int y2 = end->y - 1;

        // Sarokpont: Az (x1, y2) sarokpontot választjuk.
        int corner_x = x1;
        int corner_y = y2;

        // Vízszintes rész rajzolása ('-'): y1 sorban x1 és corner_x között
        for (int x = (x1 < corner_x ? x1 : corner_x); x <= (x1 > corner_x ? x1 : corner_x); x++) {
            // Csak akkor rajzolunk, ha nincs ott már 'B'
            if (map[y1][x] != 'B') {
                map[y1][x] = '-';
            }
        }

        // Függőleges rész rajzolása ('|'): x2 oszlopban y2 és corner_y (azaz y1) között
        for (int y = (y2 < corner_y ? y2 : corner_y); y <= (y2 > corner_y ? y2 : corner_y); y++) {
            // Csak akkor rajzolunk, ha nincs ott már 'B'
            if (map[y][x2] != 'B') {
                map[y][x2] = '|';
            }
        }
        
        // Sarok ('x') rajzolása:
        if (map[corner_y][corner_x] != 'B') {
             map[corner_y][corner_x] = 'x';
        }

        // A start és end megálló biztosan 'B' marad (felülírja az esetleges útelemeket)
        map[y1][x1] = 'B'; 
        map[y2][x2] = 'B'; 
    }

    // 4. Térkép kiírása (keret + tartalom)

    // Oszlop számozás (1 2 3 ... 10)
    printf("\n   ");
    for (int j = 1; j <= MAP_SIZE; j++) {
        printf(" %d", j % 10); // A 10-es oszlopnál csak '0'-t ír, de belefér
    }
    printf("\n  +");
    for (int j = 0; j < MAP_SIZE; j++) {
        printf("--");
    }
    printf("+\n");

    // Sorok (betű + tartalom)
    for (int i = 0; i < MAP_SIZE; i++) {
        // Sor betűje (a, b, c, ...)
        printf(" %c|", 'a' + i); 
        for (int j = 0; j < MAP_SIZE; j++) {
            printf(" %c", map[i][j]);
        }
        printf(" |\n");
    }

    // Alsó keret
    printf("  +");
    for (int j = 0; j < MAP_SIZE; j++) {
        printf("--");
    }
    printf("+\n\n");
}

// -----------------------------------------------------------
// 2) Lista megjelenítése (print_bus_stop)
// -----------------------------------------------------------

void print_bus_stop(BusStopList *list) {
    if (list->count == 0) {
        printf("\n=> Nincs buszmegálló a listában.\n");
        return;
    }

    printf("\n*** Aktuális buszmegállók listája (%d db) ***\n", list->count);
    for (int i = 0; i < list->count; i++) {
        // Felsorolás: sorszám (i+1), név, koordináták
        printf("%d. %s (%d, %d)\n", i + 1, list->stops[i].name, 
                                     list->stops[i].x, list->stops[i].y);
    }
    printf("--------------------------------------------\n");
}

// -----------------------------------------------------------
// 3) Új megálló létrehozása (create_bus_stop)
// -----------------------------------------------------------

void create_bus_stop(BusStopList *list) {
    char temp_name[MAX_NAME_LENGTH];
    int x, y;

    printf("\n*** Új buszmegálló létrehozása ***\n");

    // Név beolvasása (ideiglenes pufferbe)
    printf("Kérem adja meg a megálló nevét (max %d karakter): ", MAX_NAME_LENGTH - 1);
    // fgets használata a biztonságos beolvasáshoz
    if (fgets(temp_name, MAX_NAME_LENGTH, stdin) == NULL) {
        printf("Hiba: Sikertelen névbeolvasás.\n");
        return;
    }
    // Újsor karakter eltávolítása
    temp_name[strcspn(temp_name, "\n")] = 0; 

    // Koordináták beolvasása és validálása (ciklussal ismételjük, ha hibás)
    do {
        printf("Kérem adja meg az X koordinátát (1-%d): ", MAP_SIZE);
        if (scanf("%d", &x) != 1 || !is_valid_coord(x)) {
            printf("Hibás X koordináta! Kérjük, adjon meg 1 és 10 közötti számot.\n");
            // Tisztítás a scanf pufferből
            while (getchar() != '\n'); 
        }
    } while (!is_valid_coord(x));

    // Tisztítás a scanf pufferből a sikeres x beolvasás után
    while (getchar() != '\n'); 

    do {
        printf("Kérem adja meg az Y koordinátát (1-%d): ", MAP_SIZE);
        if (scanf("%d", &y) != 1 || !is_valid_coord(y)) {
            printf("Hibás Y koordináta! Kérjük, adjon meg 1 és 10 közötti számot.\n");
            // Tisztítás a scanf pufferből
            while (getchar() != '\n'); 
        }
    } while (!is_valid_coord(y));

    // Tisztítás a scanf pufferből
    while (getchar() != '\n');

    // Ellenőrizzük, kell-e bővíteni a listát
    expand_list(list); 

    // 1. Foglalás a NÉVNEK (Pontos memóriafoglalás!)
    // name_len + 1 (a lezáró null karakternek)
    size_t name_len = strlen(temp_name);
    BusStop *new_stop = &list->stops[list->count]; // Mutató az új elem helyére
    
    new_stop->name = (char *)malloc(name_len + 1);
    if (new_stop->name == NULL) {
        perror("Hiba: Sikertelen memóriafoglalás a névnek");
        return;
    }

    // 2. Adatok másolása
    strcpy(new_stop->name, temp_name);
    new_stop->x = x;
    new_stop->y = y;

    // 3. Elem hozzáadása és számláló növelése
    list->count++;

    printf("=> Megálló hozzáadva: %s (%d, %d)\n", new_stop->name, new_stop->x, new_stop->y);
}

// -----------------------------------------------------------
// 4) Megálló törlése (delete_bus_stop)
// -----------------------------------------------------------

void delete_bus_stop(BusStopList *list) {
    if (list->count == 0) {
        printf("\n=> Nincs törölhető megálló a listában.\n");
        return;
    }

    print_bus_stop(list); // Kiírjuk a listát a sorszámokkal együtt

    int index_to_delete;
    printf("Kérem adja meg a törlendő megálló sorszámát (1-%d), vagy 0 a visszalépéshez: ", list->count);
    if (scanf("%d", &index_to_delete) != 1) {
        // Tisztítás a scanf pufferből
        while (getchar() != '\n'); 
        printf("\n=> Érvénytelen bemenet. Visszatérés a menübe.\n");
        return;
    }
    // Tisztítás a scanf pufferből
    while (getchar() != '\n'); 

    if (index_to_delete == 0) {
        printf("=> Visszatérés a menübe.\n");
        return;
    }

    // Ellenőrizzük az indexet (1-től indul, list->count-ig)
    if (index_to_delete < 1 || index_to_delete > list->count) {
        printf("\n=> Hibás sorszám (%d)! Visszatérés a menübe.\n", index_to_delete);
        return;
    }

    int actual_index = index_to_delete - 1;
    BusStop *to_delete = &list->stops[actual_index];
    printf("=> Törölve: %s (%d, %d)\n", to_delete->name, to_delete->x, to_delete->y);

    // 1. Felszabadítjuk a megálló NEVÉNEK memóriáját
    free(to_delete->name); 

    // 2. A keletkező "lyuk" kezelése: az elemek eltolása
    // Eltoljuk az összes későbbi elemet egy pozícióval előre
    for (int i = actual_index; i < list->count - 1; i++) {
        list->stops[i] = list->stops[i + 1];
    }

    // 3. Csökkentjük a számlálót
    list->count--;

    // 4. Kapacitás csökkentése (opcionális, de jó gyakorlat)
    // Ha a lista kevesebb mint 1/4-e van tele, csökkentjük a kapacitást
    if (list->count > 0 && list->count < list->capacity / 4) {
        list->capacity /= 2;
        BusStop *new_stops = (BusStop *)realloc(list->stops, list->capacity * sizeof(BusStop));
        if (new_stops == NULL) {
            // Nem kell kilépni, de jelezzük a hibát, és marad a régi tömb
            printf("Figyelmeztetés: realloc hiba a kapacitás csökkentésekor.\n"); 
        } else {
            list->stops = new_stops;
            printf("=> Lista kapacitása csökkentve %d-re.\n", list->capacity);
        }
    } else if (list->count == 0) {
        // Ha a lista üres, szabadítsuk fel a stops tömböt, ha van még foglalt kapacitás
        free(list->stops);
        list->stops = NULL;
        list->capacity = 0;
        printf("=> A lista teljesen kiürült, memória felszabadítva.\n");
    }
}

// -----------------------------------------------------------
// 5) Mentés fájlba (save_list)
// -----------------------------------------------------------

void save_list(BusStopList *list) {
    if (list->count == 0) {
        printf("\n=> Nincs menthető buszmegálló. Visszatérés a menübe.\n");
        return;
    }

    char filename[MAX_FILENAME_LENGTH];
    printf("Kérem adja meg a menteni kívánt fájl nevét (pl. stops.txt): ");
    if (scanf("%s", filename) != 1) {
        while (getchar() != '\n'); 
        printf("Hiba a fájlnév beolvasásakor.\n");
        return;
    }
    while (getchar() != '\n'); 

    // Fájl megnyitása írásra (w - write)
    FILE *file = fopen(filename, "w");
    if (file == NULL) {
        perror("Hiba a fájl megnyitásakor írásra");
        return;
    }

    // Először írjuk ki az elemek számát, hogy betöltéskor tudjuk, hányat kell olvasni
    fprintf(file, "%d\n", list->count);

    for (int i = 0; i < list->count; i++) {
        // 1. sor: név
        fprintf(file, "%s\n", list->stops[i].name);
        // 2. sor: X Y koordináták szóközzel elválasztva
        fprintf(file, "%d %d\n", list->stops[i].x, list->stops[i].y);
    }

    fclose(file);
    printf("=> Sikeresen elmentve %d megálló a '%s' fájlba.\n", list->count, filename);
}

// -----------------------------------------------------------
// 6) Betöltés fájlból (load_list)
// -----------------------------------------------------------

void load_list(BusStopList *list) {
    char filename[MAX_FILENAME_LENGTH];
    printf("Kérem adja meg a betölteni kívánt fájl nevét (pl. stops.txt): ");
    if (scanf("%s", filename) != 1) {
        while (getchar() != '\n'); 
        printf("Hiba a fájlnév beolvasásakor.\n");
        return;
    }
    while (getchar() != '\n'); 

    // Fájl megnyitása olvasásra (r - read)
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Hiba a fájl megnyitásakor olvasásra");
        return;
    }

    // 0. Felszabadítjuk az EREDETI lista tartalmát (ha volt benne valami)
    // Megjegyzés: A main-ben a teljes listát újra kell inicializálni a free_list-tel.
    // Itt csak az egyszerűsített hibakezelés kedvéért fogadjuk el, hogy a main felel a tisztításért.
    // De az elvárásoknak megfelelően, ha betöltünk, a régit el kell felejteni.

    int total_count;
    if (fscanf(file, "%d\n", &total_count) != 1) {
        printf("Hiba: A fájl üres vagy rossz formátumú (hiányzik az elemszám).\n");
        fclose(file);
        return;
    }

    int loaded_count = 0;
    char temp_name[MAX_NAME_LENGTH];
    int x, y;

    printf("\n*** Betöltés indul: %d megálló várható ***\n", total_count);

    for (int i = 0; i < total_count; i++) {
        // 1. Olvassuk be a nevet (az egész sort)
        if (fgets(temp_name, MAX_NAME_LENGTH, file) == NULL) {
             printf("Figyelmeztetés: Váratlan fájl vége a név olvasásakor (%d. elem).\n", i + 1);
             break;
        }
        temp_name[strcspn(temp_name, "\n")] = 0; // Újsor eltávolítása

        // 2. Olvassuk be a koordinátákat
        if (fscanf(file, "%d %d\n", &x, &y) != 2) {
             printf("Figyelmeztetés: Koordináta hiba (%s), kihagyva.\n", temp_name);
             break;
        }

        // 3. Koordináta validáció (fontos elvárás!)
        if (!is_valid_coord(x) || !is_valid_coord(y)) {
            printf("Figyelmeztetés: Hibás koordináták a megállónál (%s: %d, %d), kihagyva.\n", 
                   temp_name, x, y);
            continue; // Ugrás a következő elemre
        }

        // 4. Helyes elem, hozzáadás a listához

        // Bővítés ellenőrzése
        expand_list(list);

        // Pontos memóriafoglalás a névnek
        size_t name_len = strlen(temp_name);
        BusStop *new_stop = &list->stops[list->count];
        
        new_stop->name = (char *)malloc(name_len + 1);
        if (new_stop->name == NULL) {
            perror("Hiba: Memóriafoglalás hiba betöltéskor.");
            // Lezárjuk a fájlt, de a már betöltött elemek megmaradnak
            fclose(file); 
            return;
        }
        
        // Adatok másolása
        strcpy(new_stop->name, temp_name);
        new_stop->x = x;
        new_stop->y = y;

        // Számlálók növelése
        list->count++;
        loaded_count++;
    }

    fclose(file);
    printf("=> Betöltés befejezve. %d érvényes megálló betöltve.\n", loaded_count);
}


// -----------------------------------------------------------
// 7) Útvonal megrajzolása (fastest_road)
// -----------------------------------------------------------

void fastest_road(BusStopList *list) {
    if (list->count < 2) {
        printf("\n=> Az útvonal megrajzolásához legalább két megálló szükséges.\n");
        return;
    }

    print_bus_stop(list); // Segít a választásban

    int start_index, end_index;
    int max_index = list->count;

    // Kezdőállomás kiválasztása
    printf("Kérem adja meg a KIINDULÓ megálló sorszámát (1-%d): ", max_index);
    if (scanf("%d", &start_index) != 1 || start_index < 1 || start_index > max_index) {
        while (getchar() != '\n'); 
        printf("Hibás sorszám. Visszatérés a menübe.\n");
        return;
    }
    while (getchar() != '\n'); 

    // Célállomás kiválasztása
    printf("Kérem adja meg a CÉL megálló sorszámát (1-%d): ", max_index);
    if (scanf("%d", &end_index) != 1 || end_index < 1 || end_index > max_index) {
        while (getchar() != '\n'); 
        printf("Hibás sorszám. Visszatérés a menübe.\n");
        return;
    }
    while (getchar() != '\n'); 

    // Ellenőrzés: nem lehet ugyanaz a megálló
    if (start_index == end_index) {
        printf("A kiinduló és célállomás nem lehet ugyanaz.\n");
        return;
    }

    // A listából kiszedjük a pointereket
    const BusStop *start_stop = &list->stops[start_index - 1];
    const BusStop *end_stop = &list->stops[end_index - 1];

    printf("\n*** Útvonal rajzolása: %s -> %s ***\n", start_stop->name, end_stop->name);
    // Átadjuk a show_map-nek a két megálló pointerét. A show_map majd kezeli a rajzolást.
    show_map(list, start_stop, end_stop);
}