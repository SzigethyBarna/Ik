#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char *title;
    int year;
    int pages;
} Book;

int main(){
    Book *books =NULL;
    int count=0;
    char temp[200];
    int year, pages;
    char filename[100];
    printf("Fajl neve: ");
    scanf("%99s", filename);

    FILE *f = fopen(filename, "r");
    if (!f) {
        printf("Hiba: fajl nem nyithato meg!\n");
        return 1;
    }

    while(fgets(temp,200,f)!= NULL) {
        temp[strcspn(temp, "\n")] =0;
        if (fscanf(f, "%d %d", &year,&pages)!=2)
            break;
        fgetc(f);
    
        if (year<1900 || pages<=0)
            continue;
        books=realloc(books,(count+1)*sizeof(Book));
        books[count].title=malloc(strlen(temp)+1);
        strcpy(books[count].title,temp);
        books[count].year=year;
        books[count].pages=pages;
        count++;
    }
    fclose(f);

    printf("LISTA:\n");
    for(int i=0; i<count; i++){
        printf("%99s %d -%d oldal",books[i].title,books[i].year,books[i].pages);
    }
    printf("LISTA 300 oldal felett:\n");
    for(int i=0; i<count; i++){
        if(books[i].pages>300){
            printf("%99s %d -%d oldal",books[i].title,books[i].year,books[i].pages);}
    }
    printf("LISTA:\n");
    for(int i=0; i<count; i++){
        free(books[i].title);}
    free(books);
}