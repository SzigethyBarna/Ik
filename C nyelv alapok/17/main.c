#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int x;
    int y;
} Stop;

int main() {
    Stop *stops = NULL;
    int count = 0;
    char filename[100];
    char temp[200];
    int x, y;

    printf("Adja meg a fájl nevét: ");
    scanf("%99s", filename);

    FILE *f = fopen(filename, "r");
    if (!f) {
        printf("Fájl nem nyitható meg!\n");
        return 1;
    }

    while (fgets(temp, 200, f) != NULL) {
        temp[strcspn(temp, "\n")] = 0;

        if (fscanf(f, "%d %d", &x, &y) != 2)
            break;
        fgetc(f);

        if (x < 1 || x > 10 || y < 1 || y > 10)
            continue;

        stops = realloc(stops, (count + 1) * sizeof(Stop));
        stops[count].name = malloc(strlen(temp) + 1);
        strcpy(stops[count].name, temp);
        stops[count].x = x;
        stops[count].y = y;
        count++;
    }
    fclose(f);

    char map[11][11];
    char row = 'A';

    for (int i = 0; i <= 10; i++) {
        for (int j = 0; j <= 10; j++) {
            if (i == 0 && j == 0)
                map[i][j] = ' ';
            else if (i == 0)
                map[i][j] = '0' + j;
            else if (j == 0)
                map[i][j] = row++;
            else {
                map[i][j] = ' ';
                for (int k = 0; k < count; k++) {
                    if (stops[k].x == i && stops[k].y == j) {
                        map[i][j] = 'B';
                        break;
                    }
                }
            }
        }
    }

    printf("\nTÉRKÉP:\n");
    for (int i = 0; i <= 10; i++) {
        for (int j = 0; j <= 10; j++) {
            printf("%c ", map[i][j]);
        }
        printf("\n");
    }

    for (int i = 0; i < count; i++) {
        free(stops[i].name);
    }
    free(stops);

    return 0;
}
