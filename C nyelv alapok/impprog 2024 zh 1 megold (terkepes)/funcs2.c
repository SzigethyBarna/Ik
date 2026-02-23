#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "funcs2.h"

#define ROWS 10
#define COLS 10
#define BUFFERSIZE 128


/* * * * * * * * * * * * * * * * * * * * * * * Segédfüggvény (A helper.c-ből áthozva) *
 * * * * * * * * * * * * * * * * * * * * * */
void strip(char *p){
    while ('\r' != *p && '\n' != *p && '\0' != *p){
        p++;
    }
    *p = '\0';
}


/* * * * * * * * * * * * * * * * * * * * * * * Fő vezérlés és menü                       *
 * * * * * * * * * * * * * * * * * * * * * */
void print_menu(void){
    printf("\n--- Buszmegallo Kezelo ---\n");
    printf("0) Help (Menü)\n");
    printf("1) Térkép – Megjelenítés térképes módban\n");
    printf("2) Lista – Megjelenítés szöveges módban\n");
    printf("3) Új megálló – Hozzáadás\n");
    printf("4) Megálló törlés – Törlés sorszám alapján\n");
    printf("5) Kilépés – Program befejezése\n");
}


void menu(void){
    char buffer[BUFFERSIZE];
    int command = 0;
    // Statikus inicializálás (nincs szükség malloc-ra)
    Map map = {.counter = 0}; 

    while (command != 5){ // Kilépés az 5-ös paranccsal
        print_menu();
        
        printf("Parancs: ");
        if (NULL == fgets(buffer, BUFFERSIZE, stdin)){
             // EOF esetén kilép
             break;
        }

        if (1 != sscanf(buffer, "%d", &command)){
            command = -1; // Érvénytelen bemenet
        }

        if (0 == command){
            /* print_menu() már hívódott */
        }else if (1 == command){
            show_map(map);
        }else if (2 == command){
            print_bus_stop(map);
        }else if (3 == command){
            create_bus_stop(&map);
        }else if (4 == command){
            delete_bus_stop(&map);
        }else if (5 == command){
            printf("Exiting\n");
        }else{
            printf("Kérjük, adjon meg érvényes parancsot (0-5)!\n");
        }
    }
}


/* * * * * * * * * * * * * * * * * * * * * * * Megállók kezelése                         *
 * * * * * * * * * * * * * * * * * * * * * */

void addStop(Map *map, char *bufferName, int row, int col){
    if (map->counter >= MAX_STOPS){
        printf("Hiba: Elérte a maximum megálló számot (%d).\n", MAX_STOPS);
        return;
    }

    // Nincs dinamikus memóriafoglalás (malloc)
    map->stops[map->counter].row = row - 1;
    map->stops[map->counter].col = col - 1;

    strip(bufferName);
    
    // String másolása a statikus tömbbe
    strncpy(map->stops[map->counter].name, bufferName, 99);
    map->stops[map->counter].name[99] = '\0'; // Biztos nullterminálás
    
    map->counter++;
}

void create_bus_stop(Map *map){
    char bufferName[BUFFERSIZE];
    char bufferCoords[BUFFERSIZE];
    int row = 0, col = 0;

    printf("Megálló neve: ");
    if (NULL == fgets(bufferName, BUFFERSIZE, stdin)) return;

    printf("Koordináták (sor oszlop, pl: 5 7): ");
    if (NULL == fgets(bufferCoords, BUFFERSIZE, stdin)) return;
    int return_sscanf = sscanf(bufferCoords, "%d %d", &row, &col);

    // Bemenet ellenőrzése
    while (
        2 != return_sscanf || row < 1 || row > ROWS || col < 1 || col > COLS
    ){
        printf("Hibás formátum vagy tartomány (1-10). Koordináták: ");
        if (NULL == fgets(bufferCoords, BUFFERSIZE, stdin)) return;
        return_sscanf = sscanf(bufferCoords, "%d %d", &row, &col);
    }

    addStop(map, bufferName, row, col);
}


void delete_bus_stop(Map *map){
    if (0 == map->counter){
        printf("Nincs törölhető megálló.\n");
        return;
    }
    
    char buffer[BUFFERSIZE];
    int index;
    printf("Törlendő megálló sorszáma: ");
    if (NULL == fgets(buffer, BUFFERSIZE, stdin)) return;
    
    sscanf(buffer, "%d", &index);
    index -= 1; // 0-alapú indexre váltás

    if (0 <= index && index < map->counter){
        // Nincs szükség a név felszabadítására (free)
        // Elemek eltolása:
        for (int i = index; i < (map->counter-1); i++){
            map->stops[i] = map->stops[i+1];
        }
        map->counter -= 1;
        printf("Megálló törölve.\n");
    }else{
        printf("Érvénytelen sorszám.\n");
    }
}


/* * * * * * * * * * * * * * * * * * * * * * * Megjelenítés                              *
 * * * * * * * * * * * * * * * * * * * * * */
void print_bus_stop(Map map){
    if (0 == map.counter){
        printf("Nincsenek megállók a listában.\n");
    }else{
        printf("\n--- Buszmegállók listája ---\n");
        for (int i = 0; i < map.counter; i++){
            // (i+1) sor, (map.stops[i].row+1) koordináta
            printf("%d. %s (%d, %d)\n", 
                   (i+1), 
                   map.stops[i].name, 
                   map.stops[i].row + 1, 
                   map.stops[i].col + 1
            );
        }
    }
}

void show_map(Map map){
    if (0 == map.counter){
        printf("Nincsenek megállók a térképen.\n");
        return;
    }
    
    char blocks[ROWS][COLS];

    // Térkép kitöltése szóközzel
    for (int i = 0; i < ROWS; i++){
        for (int j = 0; j < COLS; j++){
            blocks[i][j] = ' ';
        }
    }

    // Megállók elhelyezése
    for (int i = 0; i < map.counter; i++){
        blocks[map.stops[i].row][map.stops[i].col] = 'B';
    }

    printf("\n--- Térkép (10x10) ---\n");
    // Oszlopfejléc
    printf("  ");
    for (int j = 0; j < COLS; j++){
        printf("%d ", j + 1);
    }
    printf("\n");
    
    // Térkép rajzolása
    for (int i = 0; i < ROWS; i++){
        printf("%c ", 'A' + i); // Sorjelölő ('A'-tól 'J'-ig)
        for (int j = 0; j < COLS; j++){
            printf("%c ", blocks[i][j]);
        }
        printf("\n");
    }
}