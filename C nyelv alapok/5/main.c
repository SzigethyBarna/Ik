#include <stdio.h>

#define SIZE 10

int main() {

    int x1, y1, x2, y2;

    printf("Start (x y): ");
    scanf("%d %d", &x1, &y1);

    printf("Finish (x y): ");
    scanf("%d %d", &x2, &y2);

    char map[SIZE][SIZE];

    // 1. feltöltés '.'-ekkel
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            map[i][j] = '.';
        }
    }

    // 2. Start és Finish jelölése
    map[y1 - 1][x1 - 1] = 'S';
    map[y2 - 1][x2 - 1] = 'F';

    // 3. IDE JÖN AZ ÚTVONALRAJZOLÁS
    if (y1>y2){
        if(x1==x2){
            for( int i=y2+1; i<y1;i++){
                map[i-1][x2-1]='*';
            }
        }
        else{
            for( int i=y2+1; i<=y1;i++){
                map[i-1][x1-1]='*';
            }
            if(x1>x2){for( int i=x2+1; i<x1;i++){
                map[y1-1][i-1]='*';
                }
            }
                else{
                    for( int i=x1+1; i<x2;i++){
                        map[y1-1][i-1]='*';
                    }
                }
        }
    }
    else if(y1<y2)
    {
        if(x1==x2){
            for( int i=y1+1; i<y2;i++){
                map[i-1][x2-1]='*';
            }
        }
        else{
            for( int i=y1+1; i<=y2;i++){
                map[i-1][x1-1]='*';
            }
            if(x1>x2){for( int i=x2+1; i<x1;i++){
                map[y1-1][i-1]='*';
                }
            }
                else{
                    for( int i=x1+1; i<x2;i++){
                        map[y1-1][i-1]='*';
                    }
                }
        }
    }
    else{
        if(x1>x2){
            for( int i=x2+1;i<x1;i++){
                map[y1][i] ='*';
            }
        }
        else{
            for( int i=x1+1;i<x2;i++){
                map[y1][i] ='*';
            }

        }
    }
    // 4. Térkép kiírása
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%c ", map[i][j]);
        }
        printf("\n");
    }

    return 0;
}
