#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *title;
    int year;
    int age;
} Movie;

int main(){
    Movie *movies=NULL;
    char filename[100];
    char temp[200];
    int year,age;
    int count=0;

    printf("fájl neve:");
    scanf("%99s", filename);

    FILE *f = fopen(filename, "r");
    if (!f){perror("Nem nyithato meg a fajl.");return 1;}

    while(fgets(temp,200,f)!=NULL){
        temp[strcspn(temp,"\n")] =0;

        if(fscanf("%d %d",&year,&age) != 2) break;
        fgetc(f);
        if(year<1980 || age<0 || age>18){continue;}

        movies=realloc(movies,(count+1)*sizeof(Movie));
        movies[count].title=malloc(strlen(temp)+1);
        strcpy(movies[count].title,temp);
        movies[count].age=age;
        movies[count].year=year;
        count++;
    }
    double atlag=0;
    int db=0;
    int ossz=0;
    int legujabbind=0;
    int maxev=0;
    printf("ÖSSZES:\n");
    for( int i=0; i<count;i++){
        printf("%s (%d) -%d+\n",movies[i].title,movies[i].year,movies[i].age);

        if(movies[i].year>2000){
            db++;
            ossz=ossz+movies[i].age;
        }
        if(movies[i].year>maxev ){
            maxev=movies[i].year;
            legujabbind=i;
        }
    }
    printf("18+:\n");
    for( int i=0; i<count;i++){
        if(movies[i].age==18){
            printf("%s (%d)\n",movies[i].title,movies[i].year);
        }
    }
    
    if(db>0) {
        atlag=(double)ossz/db;
        printf("2000 utáni átlagos korosztály: %.2f\n",atlag);
    }

    printf("legújabb film:%s - %d\n",movies[legujabbind].title, maxev);


    for( int i=0; i<count;i++){
        free(movies[i].title);
    }

    free(movies);
    return 0;
}