#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *name;
    int x;
    int y;

} Event;

void menu(void){
    Event *events= NULL;
    int count =0;
    int choice;

    do{
        printf("\n1. Uj esemeny\n");
        printf("2. Listazas\n");
        printf("3. Torles\n");
        printf("4. Terkep\n");
        printf("5. Kilepes\n");
        printf("Valasztas: ");
        scanf("%d", &choice);
        switch (choice){
            case 1: {
                char temp[100];
                int x,y;
                printf("Uj esemeny neve: ");
                getchar();
                fgets(temp,100,stdin);
                temp[strcspn(temp,"\n")] = 0;
                printf("x:");
                scanf("%d",&x);
                printf("y:");
                scanf("%d",&y);
                if (x < 1 || x > 10 || y < 1 || y > 10) {
                    printf("Hibas koordinata!\n");
                    break;
                }
                events= realloc(events,(count+1)*sizeof(Event));
                events[count].name=malloc(strlen(temp)+1);
                strcpy(events[count].name,temp);
                events[count].x = x;
                events[count].y = y;
                count++;
                break;
            }
            case 2:{
                for (int i=0; i<count-1;i++){
                    printf("Event neve: %s (%d,%d)",events[i].name,events[i].x,events[i].y);
                }break;
            }
            case 3:{
            printf("Hanyadik filmet torli? ");
            int torlendo;
            scanf("%d",&torlendo);
            torlendo--;
            free(events[torlendo].name);
            for (int i=torlendo;i<count; i++){
                events[i]=events[i+1];
            }
            count--;
            events= realloc(events,count*sizeof(Event));
            }break;
            case 4:{
                for( int i=1;i<=10;i++){
                    for (int j=1;j<=10;j++){
                        int found=0;
                        for(int k=0;k<count;k++){
                            if (events[k].x==j && events[k].y == i){
                                found=1;
                            }
                        }
                        if (found){
                            printf( "E");
                        }
                        else{
                            printf(" ");
                        }
                        printf("\n");
                    }
                }
            }break;
            case 5: {
                printf("Kilepes");
                break;
            }

        }
    }while (choice!=5);
    free(events);
}