#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int stops;
} Bus;

int main(){
    Bus *buses = NULL;
    int count=0;
    char filename[100];
    
    printf("Fajl:");
    scanf("%99s", filename);
    FILE *f=fopen(filename, "r");
    if(!f){perror("Nem lehet megnyitni");return 1;}

    char temp[200];
    int stops;
    while(fgets(temp,200,f)!=NULL){
        temp[strcspn(temp,"\n")]=0;

        if (fscanf(f,"%d",&stops) !=1){
            break;
        }
        fgetc(f);

        if(stops<=0 || stops>=20) {continue;}

        buses=realloc(buses,(count+1)*sizeof(Bus));
        buses[count].name=malloc(strlen(temp)+1);
        strcpy(buses[count].name,temp);
        buses[count].stops =stops;
        count++;

    
    }
    printf("ÖSSZES:\n");
    double atlag=0;
    int db=0;
    int osszeg=0;
    for(int i=0;i<count;i++){
        printf("%s - %d megallo\n",buses[i].name,buses[i].stops);
        osszeg=osszeg+buses[i].stops;
        db++;
    }
    printf("ATLAGOS MEGALLOSZAM:\n");
    atlag=(double)osszeg/db;
    printf("%.2f\n",atlag);

    for( int i=0;i<count;i++){
        free(buses[i].name);
    }
    free(buses);

}