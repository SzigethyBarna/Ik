#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Student {
    char name[50];
    int grade;
    struct Student *next;
} Student;

Student *create_student(const char *name, int grade){
    Student *s = malloc(sizeof(Student));
    if (!s){
        return NULL;
    }
    strcpy(s->name,name);
    s->grade = grade;
    s->next = NULL;
    return s;
}

void push_front(Student **head, Student *new_student){
    new_student->next =*head;
    *head=new_student;
}

void print_list(Student *head){
    Student *current =head;
    while (current != NULL) {
        printf("%s - %d",current->name,current->grade);
        current =current->next;
    }
}

void free_list(Student *head){
    while (head!= NULL){
        Student *next = head->next;
        free(head);
        head= next;
    }
}

int main() {
    Student *head = NULL;

    push_front(&head, create_student("Anna", 5));
    push_front(&head, create_student("Bela", 3));
    push_front(&head, create_student("Csilla", 4));

    print_list(head);

    free_list(head);
    return 0;
}
