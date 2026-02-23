#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "library.h"

void menu(void) {
    Loan *loans = NULL;
    int count = 0;

    char filename[100];
    char title[200];
    char borrower[200];
    int year, days;

    printf("Fajl neve: ");
    scanf("%99s", filename);

    FILE *f = fopen(filename, "r");
    if (!f) {
        perror("Nem lehet megnyitni");
        return;
    }

    /* ===== FÁJL BEOLVASÁS ===== */
    while (fgets(title, 200, f) != NULL) {
        title[strcspn(title, "\n")] = 0;

        if (fgets(borrower, 200, f) == NULL)
            break;
        borrower[strcspn(borrower, "\n")] = 0;

        if (fscanf(f, "%d %d", &year, &days) != 2)
            break;
        fgetc(f);

        if (year < 2000 || days <= 0 || days > 60)
            continue;

        Loan *tmp = realloc(loans, (count + 1) * sizeof(Loan));
        if (!tmp) break;
        loans = tmp;

        loans[count].title = malloc(strlen(title) + 1);
        loans[count].borrower = malloc(strlen(borrower) + 1);

        strcpy(loans[count].title, title);
        strcpy(loans[count].borrower, borrower);

        loans[count].year = year;
        loans[count].days = days;
        count++;
    }
    fclose(f);

    /* ===== MENÜ ===== */
    int choice;
    do {
        printf("\n1 - Osszes kolcsonzes\n");
        printf("2 - 2023-as kolcsonzesek\n");
        printf("3 - Hosszu kolcsonzesek (30+ nap)\n");
        printf("4 - Atlagos kolcsonzesi ido\n");
        printf("5 - Uj kolcsonzes\n");
        printf("6 - Kilepes\n");
        printf("Valasztas: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                list_all(loans, count);
                break;
            case 2:
                list_2023(loans, count);
                break;
            case 3:
                list_long(loans, count);
                break;
            case 4:
                printf("Atlag: %.2f nap\n", average_days(loans, count));
                break;
            case 5:
                loans = add_loan(loans, &count);
                break;
            case 6:
                free_loans(loans, count);
                printf("Kilepes...\n");
                break;
            default:
                printf("Hibas valasztas!\n");
        }
    } while (choice != 6);
}

/* ===== LISTÁZÁSOK ===== */

void list_all(Loan *loans, int count) {
    for (int i = 0; i < count; i++) {
        printf("%s - %s (%d) %d nap\n",
               loans[i].title,
               loans[i].borrower,
               loans[i].year,
               loans[i].days);
    }
}

void list_2023(Loan *loans, int count) {
    for (int i = 0; i < count; i++) {
        if (loans[i].year == 2023) {
            printf("%s - %s (%d nap)\n",
                   loans[i].title,
                   loans[i].borrower,
                   loans[i].days);
        }
    }
}

void list_long(Loan *loans, int count) {
    for (int i = 0; i < count; i++) {
        if (loans[i].days > 30) {
            printf("%s - %s (%d nap)\n",
                   loans[i].title,
                   loans[i].borrower,
                   loans[i].days);
        }
    }
}

double average_days(Loan *loans, int count) {
    if (count == 0) return 0.0;

    int sum = 0;
    for (int i = 0; i < count; i++) {
        sum += loans[i].days;
    }
    return (double)sum / count;
}

/* ===== ÚJ KÖLCSÖNZÉS ===== */

Loan *add_loan(Loan *loans, int *count) {
    char title[200];
    char borrower[200];
    int year, days;

    getchar();  // ENTER kiszedése

    printf("Konyv cime: ");
    fgets(title, 200, stdin);
    title[strcspn(title, "\n")] = 0;

    printf("Kolcsonzo neve: ");
    fgets(borrower, 200, stdin);
    borrower[strcspn(borrower, "\n")] = 0;

    printf("Ev es napok: ");
    scanf("%d %d", &year, &days);

    if (year < 2000 || days <= 0 || days > 60) {
        printf("Hibas adat!\n");
        return loans;
    }

    Loan *tmp = realloc(loans, (*count + 1) * sizeof(Loan));
    if (!tmp) return loans;
    loans = tmp;

    loans[*count].title = malloc(strlen(title) + 1);
    loans[*count].borrower = malloc(strlen(borrower) + 1);

    strcpy(loans[*count].title, title);
    strcpy(loans[*count].borrower, borrower);

    loans[*count].year = year;
    loans[*count].days = days;

    (*count)++;
    return loans;
}

/* ===== FELSZABADÍTÁS ===== */

void free_loans(Loan *loans, int count) {
    for (int i = 0; i < count; i++) {
        free(loans[i].title);
        free(loans[i].borrower);
    }
    free(loans);
}
