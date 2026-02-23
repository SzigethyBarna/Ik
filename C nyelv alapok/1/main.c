#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *nev;
    int lakossag;
} City;

int main(){
    City *citys
    int count =0;

    char temp[100];
    int lakossag;
    while (1){
        scanf(" %99[^\n] ",temp);

        if(strcmp(temp, "0" ) == 0)
            break;

        scanf("%d", &lakossag)
        citys = reallock(citys, (count+1)*sizeof(City));

        citys[count].nev = malloc(strlen(temp)+1);
        strcpy(citys[count].nev,temp);
        citys[count].lakossag =lakossag;
        count++;
    }
    for (int i=0, i<count;i++ ){
        printf("%d. %s %d lak",i+1,citys[i].nev,citys[i].lakossag);
    }
    for (int i = 0; i < count; i++)
        free(citys[i].nev);

    free(citys);

    return 0;
}


