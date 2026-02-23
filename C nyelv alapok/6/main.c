#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int x, y;
} Event;

#define SIZE 10

int main() {
    Event *events = NULL;
    int count = 0;

    char filename[100];
    printf("Fajl neve: ");
    scanf("%99s", filename);

    FILE *f = fopen(filename, "r");
    if (!f) {
        printf("Hiba: fajl nem nyithato meg!\n");
        return 1;
    }

    char temp[200];
    int x, y;

    while (fgets(temp, 200, f) != NULL) {
        temp[strcspn(temp, "\n")] = 0;

        if (fscanf(f, "%d %d", &x, &y) != 2)
            break;

        fgetc(f);

        if (x < 1 || x > 10 || y < 1 || y > 10)
            continue;

        events = realloc(events, (count + 1) * sizeof(Event));
        events[count].name = malloc(strlen(temp) + 1);
        strcpy(events[count].name, temp);
        events[count].x = x;
        events[count].y = y;
        count++;
    }

    fclose(f);

    // 1) Térkép létrehozása
    char map[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            map[i][j] = '.';

    // 2) Események kirakása a térképre
    // >>> IDE KELL A MAI FELADAT MEGOLDÁSA <<<
        for (int k=0; k<count;k++){
                    if(map[events[k].y-1][events[k].x-1] =='.'){
                        map[events[k].y-1][events[k].x-1]=events[k].name[0];
                    }
                    else {
                        map[events[k].y-1][events[k].x-1] ='*'; 
                    }
        }



    // 3) Térkép kiírása
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++)
            printf("%c ", map[i][j]);
        printf("\n");
    }

    for (int i = 0; i < count; i++)
        free(events[i].name);
    free(events);

    return 0;
}
