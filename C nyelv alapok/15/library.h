#ifndef HELPER_H_INCLUDED
#define HELPER_H_INCLUDED

void menu(void);

void list_all(Loan *loans, int count);
void list_2023(Loan *loans, int count);
void list_long(Loan *loans, int count);
double average_days(Loan *loans, int count);
Loan *add_loan(Loan *loans, int *count);
void free_loans(Loan *loans, int count);

typedef struct{
    char *title;
    char *borrower;
    int year;
    int days;
} Loan;

#endif