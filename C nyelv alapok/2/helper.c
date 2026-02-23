#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "helper.h"

void menu(void){
    Movie *movie = NULL;
    int count=0;
    int choice;
    do {
        printf("\n1. Uj film\n");
        printf("2. Listazas\n");
        printf("3. Torles\n");
        printf("4. Kilepes\n");
        printf("Valasztas: ");
        scanf("%d", &choice);
        switch(choice){
            case 1: {

                char temp[100];
                int year,length;
                printf("Film címe: ");
                getchar();  // előző ENTER kiszedése
                fgets(temp, 100, stdin);
                temp[strcspn(temp, "\n")] = 0;  // \n törlése
                printf("Megjelenes eve");
                scanf("%d",&year);

                printf("Hossz");
                scanf("%d",&length);

                movie = realloc(movie, (count+1)*sizeof(Movie));
                movie[count].title = malloc(strlen(temp)+1);
                strcpy(movie[count].title,temp);

                movie[count].year =year;
                movie[count].length =length;
                count++;
                break;
            }
                
            case 2: 
                for (int i = 0; i<count; i++ ){
                    printf("%s,%d ev, %d hossz", movie[i].title, movie[i].year, movie[i].length);
                }break;
            case 3:
                printf("Hanyadik filmet torli?");
                int torlendo;
                scanf("%d",&torlendo);
                if (torlendo < 1 || torlendo > count) {
                    printf("Hibas sorszam!\n");
                        break;
                }
                torlendo--;
                free( movie[torlendo].title);

                for (int i = torlendo; i<count-1; i++ ){
                    movie[i]=movie[i+1];
                }
                count--;
                movie =realloc(movie, count*sizeof(Movie));
                break;
            case 4:
                printf("Kilépés...\n");
                break;

            default:{
                printf("Hibás választás!\n");
                }
        }

    } while (choice != 4);
}