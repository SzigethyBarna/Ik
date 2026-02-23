#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char *name;
    int x;
    int y;
} Event;
int main(){
    Event *events = NULL;
    int count= 0;
    char filename[100];

    printf("Fajl neve ");
    scanf("%99s", filename);

    FILE *f =fopen(filename, "r");
    if (f== NULL){
        printf("Nem lehet megnyitni \n");
        return 1;
    }
    char temp[200];
    int x,y;
    while (fgets(temp,200,f)!=NULL){
        temp[strcspn(temp,"\n")] =0;
        if (fscanf(f,"%d %d",&x,&y) !=2){
            break;}
        fgetc(f);
        if (x < 1 || x > 10 || y < 1 || y > 10) {
            continue;}
        events=realloc(events,(count+1)*sizeof(Event));
        events[count].name =malloc(strlen(temp)+1);
        strcpy(events[count].name,temp);
        events[count].x =x;
        events[count].y =y;
        count++;
        
    }
    printf("Listazas\n");
    for(int i=0;i<count;i++){
        printf("%s (%d,%d)\n",events[i].name,events[i].x,events[i].y);
    }

    fclose(f);
    for (int i = 0; i < count; i++) {
    free(events[i].name);
    }
    free(events);
}