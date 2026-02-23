#ifndef BUSZMEGALLO_H
#define BUSZMEGALLO_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Makrók a térkép méreteihez, ahogy az elvárásban szerepel.
#define MAP_SIZE 10
#define MAX_NAME_LENGTH 100
#define MAX_FILENAME_LENGTH 256

// Buszmegálló struktúra
typedef struct BusStop {
    // A megálló neve (dinamikusan foglalt)
    char *name; 
    // X koordináta (oszlop) 1-10 között
    int x;       
    // Y koordináta (sor) 1-10 között
    int y;       
} BusStop;

// A buszmegállók tároló struktúrája (dinamikus tömb)
typedef struct BusStopList {
    // A buszmegállók dinamikus tömbje
    BusStop *stops; 
    // Jelenlegi megállók száma
    int count;      
    // A tömb jelenlegi kapacitása
    int capacity;   
} BusStopList;

// -----------------------------------------------------------
// Inicializálás és memóriakezelés
// -----------------------------------------------------------

// Létrehozza és inicializálja az üres buszmegálló listát.
BusStopList *init_list();

// Felszabadítja a buszmegálló lista teljes memóriáját (neveket és a listát magát).
void free_list(BusStopList *list);

// -----------------------------------------------------------
// Fő funkciók (Megvalósítás a buszmegallo.c-ben)
// -----------------------------------------------------------

// 1) Térkép megjelenítése (beleértve az útvonalat is, ha van)
void show_map(BusStopList *list, const BusStop *start, const BusStop *end);

// 2) Lista megjelenítése
void print_bus_stop(BusStopList *list);

// 3) Új megálló hozzáadása
void create_bus_stop(BusStopList *list);

// 4) Megálló törlése (index alapján)
void delete_bus_stop(BusStopList *list);

// 5) Lista mentése fájlba
void save_list(BusStopList *list);

// 6) Lista betöltése fájlból
void load_list(BusStopList *list);

// 7) Útvonal megrajzolása (csak a főprogram hívja, ami a show_map-et hívja)
void fastest_road(BusStopList *list);

#endif // BUSZMEGALLO_H