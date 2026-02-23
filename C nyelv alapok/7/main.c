#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 10
int main(){
    char map[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            map[i][j] = '.';

    int x1,x2,y1,y2;
    printf("Start(x y):");
    scanf("%d %d",&x1,&y1);

    printf("Finish(x,y):");
    scanf("%d %d",&x2,&y2);

    map[y1-1][x1-1]='S'
    map[y2-1][x2-1]='F'
    if (x1<x2){
        for (int x =x1+1;x<x2;x++){
            if(map[y1-1][x1-1]!='S' && map[y1-1][x1-1]!='F')
            map[y1-1][x-1]='*'
        }
    }
    else if (x1>x2){
        for (int x =x1+1;x>x2;x--){
            if(map[y1-1][x1-1]!='S' && map[y1-1][x1-1]!='F')
            map[y1-1][x-1]='*'
        }
    }
    if (y1<y2){
        for (int y =y1+1;y<y2;y++){
            if(map[y1-1][x1-1]!='S' && map[y1-1][x1-1]!='F')
            map[y1-1][x-1]='*'
        }
    }
    else if (y1>y2){
        for (int y =y1+1;y>y2;y--){
            if(map[y1-1][x1-1]!='S' && map[y1-1][x1-1]!='F')
            map[y1-1][x-1]='*'
        }
    }
}