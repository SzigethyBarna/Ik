#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int year;
    int result;
} Lab;

int main(){
    Lab *labs = NULL;
    int count=0;
    int year, result;

    char filename[100];
    char temp[200];
    printf("Fájl neve:\n");
    scanf("%99s",filename);
    FILE *f= fopen(filename ,"r");
    if(!f){
        printf("fájl nem nyithato meg");
        return 1;
    }

    while(fgets(temp,200,f)!=NULL){
        temp[strcspn(temp, "\n")] =0;

        if (fscanf(f, "%d", &year) != 1) break;
        if (fscanf(f, "%d", &result) != 1) break;

        fgetc(f);
        
        if(year<2000 || result<0 || result>100){continue;}

        labs=realloc(labs,(count+1)*sizeof(Lab));
        labs[count].name=malloc(strlen(temp)+1);
        strcpy(labs[count].name,temp);
        labs[count].year =year;
        labs[count].result =result;
        count++;
    }
    double atlag=0;
    int legjobbind=0;
    int maxpont=0;
    int ossz=0;
    int db=0;
    printf("ÖSSZES REKORD:\n");
    for (int i=0; i<count;i++){
        printf("%s (%d) - %d pont\n",labs[i].name,labs[i].year, labs[i].result);
        ossz= ossz+labs[i].result;
        db++;
        if(maxpont<labs[i].result){
            maxpont=labs[i].result;
            legjobbind=i;
        }
    }
    printf("2023-AS EREDMÉNYEK:\n");
    for (int i=0; i<count;i++){
        if(labs[i].year == 2023){
            printf("%s - %d pont\n",labs[i].name, labs[i].result);
        }
    }
    printf("ATLAG EREDMÉNY:\n");
    if (db>0){
        atlag=(double)ossz/db;}
    printf("%.2f",atlag);

    printf("LEGJOBB:\n");
    printf("%s - %d ponttal",labs[legjobbind].name,maxpont);

    printf("2023-ban bukottak:\n");
    for (int i=0; i<count;i++){
        if(labs[i].year == 2023 && labs[i].result<50 ){
                printf("%s \n",labs[i].name);
            }
        }
    
    for (int i=0; i<count;i++){
        free(labs[i].name);
    }

    free(labs);




    return 0;
}