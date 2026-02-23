#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int score;
} Student;

int main(){
    Student *students=NULL;
    int count =0;
    char filename[100];
    printf("Fájl neve:");
    scanf("%99s",filename);
    FILE *f=fopen(filename, "r");
    if (!f){printf("file nem nyithato meg\n");return 1;}

    char temp[200];
    int score;

    while(fgets(temp,200,f) != NULL){
        temp[strcspn(temp,"\n")] =0;
        if(fscanf(f,"%d",&score)!=1){break;}
        fgetc(f);

        if(score<0 || score>100){
            continue;
        }
        students=realloc(students,(count+1)*sizeof(Student));
        students[count].name=malloc(strlen(temp)+1);
        strcpy(students[count].name,temp);
        students[count].score=score;
        count++;
    }
    float atlag=0;
    int osszpont=0;
    int db=0;
    printf("ÖSSZES:\n");
    for(int i=0; i<count;i++){
        printf("%s - %d pont\n",students[i].name,students[i].score);
        db++;
        osszpont=osszpont+students[i].score;
    }
    printf("ÁTMENTEK:\n");
    for(int i=0; i<count;i++){
        if(students[i].score>=50){
            printf("%s\n",students[i].name);
        }
    }
    if(db>0){
        atlag=(float)osszpont/db;}
    printf("Átlagos pontszám: %.2f",atlag);

    for (int i=0; i<count;i++){
        free(students[i].name);
    }
    free(students);

}