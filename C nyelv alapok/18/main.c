#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *student;
    char *subject;
    int year;
    int credit;
    int grade;
} Sigma;

int main(){
    Sigma *sigmas = NULL;
    int year,credit,grade;
    char student[200];
    char subject[200];
    char filename[100];
    int count=0;
    printf("Adja meg a fájlnevet: \n");
    scanf("%99s",filename);
    FILE *f=fopen(filename,"r");
    if(!f){perror("Nem olvashato fájl"); return 1;}

    while(fgets(student,200,f) != NULL){
        student[strcspn(student,"\n")]=0;

        if(fgets(subject,200,f)==NULL){
            break;
        }
        subject[strcspn(subject,"\n")]=0;

        if(fscanf(f,"%d %d",&year,&credit)!=2){
            perror("Hibás bemenet");
            return 1;
        }
        fgetc(f);
        if(fscanf(f,"%d",&grade)!=1){
            perror("Hibás bemenet");
            return 1;
        }
        fgetc(f);

        if(2010>year || 0>=credit || credit>10 || grade < 1 || grade > 5){
            continue;
        }

        sigmas=realloc(sigmas,(count+1)*sizeof(Sigma));
        sigmas[count].student=malloc(strlen(student)+1);
        strcpy(sigmas[count].student,student);
        sigmas[count].subject=malloc(strlen(subject)+1);
        strcpy(sigmas[count].subject,subject);



        sigmas[count].year=year;
        sigmas[count].credit=credit;
        sigmas[count].grade=grade;
        count++;
    }
    fclose(f);

    printf("1\n");
    for(int i=0; i<count;i++){
            printf("%s - %s - %d - %d\n",sigmas[i].student,sigmas[i].subject,sigmas[i].year,sigmas[i].grade);
    }

    printf("2\n");
    for(int i=0; i<count;i++){
        if(sigmas[i].grade >=2){
            printf("%s - %s \n",sigmas[i].student,sigmas[i].subject);
        }
    }

    printf("3\n");

    int db=0;
    int ossz=0;
    double atlag=0;

    for(int i=0; i<count;i++){
        if(sigmas[i].year==2023){
            db++;
            ossz=ossz+sigmas[i].grade;
        }
    }
    if (db>0){
        atlag=(double)ossz/db;
        printf("%d db tantárgy volt, ezek átlaga: %.2f\n",db,atlag);
    }
    else{printf("Nem volt tantárgy az évben");}

    printf("4\n");
    printf("4 - Legjobb hallgató (kredit-súlyozott átlag):\n");

    double best_avg = -1.0;
    char *best_student = NULL;
    
    for (int i = 0; i < count; i++) {
        int sum_credit = 0;
        int sum_weighted = 0;
    
        // végigmegyünk újra az összes rekordon
        for (int j = 0; j < count; j++) {
            if (strcmp(sigmas[i].student, sigmas[j].student) == 0) {
                sum_credit += sigmas[j].credit;
                sum_weighted += sigmas[j].grade * sigmas[j].credit;
            }
        }
    
        if (sum_credit > 0) {
            double avg = (double)sum_weighted / sum_credit;
        
            if (avg > best_avg) {
                best_avg = avg;
                best_student = sigmas[i].student;
            }
        }
    }

if (best_student != NULL) {
    printf("Legjobb hallgató: %s (átlag: %.2f)\n",
           best_student, best_avg);
}


    printf("5\n");
    char hallgato[200];
    char targy[200];
    int ev,kredit,jegy;
    printf("tantargy neve:");
    scanf("%199s",hallgato);
    printf("hallgato neve:");
    scanf("%199s",targy);
    printf("év:");
    scanf("%d",&ev);
    printf("credit érték:");
    scanf("%d",&kredit);
    printf("jegy:");
    scanf("%d",&jegy);

    sigmas=realloc(sigmas,(count+1)*sizeof(Sigma));
    sigmas[count].student=malloc(strlen(hallgato)+1);
    strcpy(sigmas[count].student,hallgato);
    sigmas[count].subject=malloc(strlen(targy)+1);
    strcpy(sigmas[count].subject,targy);

    sigmas[count].year=ev;
    sigmas[count].grade=jegy;
    sigmas[count].credit=kredit;
    count++;

    printf("6:törlés:\n");
    char torlendo[200];
    printf("Melyik adatot torli:");
    scanf("%199s",torlendo);
    torlendo[strcspn(torlendo,"\n")]=0;
    
    for(int i=0;i<count;i++){
        if(strcmp(torlendo,sigmas[i].subject)==0){
            free(sigmas[i].student);
            free(sigmas[i].subject);
            for(int j=i;j<count;j++){
                sigmas[i]=sigmas[i+1];
                
            }
            count--;
            sigmas=realloc(sigmas,count*sizeof(Sigma));
            i--;
        }
    }
    
    printf("7:\n");
    for(int i=0;i<count;i++){
        free(sigmas[i].student);
        free(sigmas[i].subject);
    }
    free(sigmas);


    return 0;
}
