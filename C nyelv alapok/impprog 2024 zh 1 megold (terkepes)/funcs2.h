#ifndef FUNCS_H_INCLUDED
#define FUNCS_H_INCLUDED

#define MAX_STOPS 50 // Maximum 50 megálló

struct Stop{
    char name[100]; // Statikus nevtárolás
    int row, col;
};
typedef struct Stop Stop;

struct Map{
    Stop stops[MAX_STOPS]; // Statikus tömb
    int counter;
};
typedef struct Map Map;

// Segéd: eltávolítja a \n karaktert a bemenet végéről
void strip(char *p); 

void menu(void);
void print_menu(void);
void show_map(Map map);
void print_bus_stop(Map map);
void create_bus_stop(Map *map);
void addStop(Map *map, char *bufferName, int row, int col);
void delete_bus_stop(Map *map); 

#endif