#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct Task {
    char *text;
    int priority;
    struct Task *next;
} Task;

Task *create_task(const char *text, int *priority){
    Task *t=malloc(sizeof(Task));
    if(!t){
        return NULL;
    }
    t->text=malloc(strlen(text)+1);
    if(!t->text){
        free(t);
        return NULL;
    }
    strcpy(t->text,text);
    t->priority;
    t->next = NULL;

    return t;
}

void push_back(Task **head, Task *new_task){
    if(*head==NULL){
        *head=new_task;
        return;
    }
    Task *current = *head;
    while(current->next != NULL){
        current = current->next;
    }    
    current->next = new_task;
}

void remove_low_priority(Task **head){
    while (*head!= NULL && (*head)->priority<=2){
        Task *tmp = *head;
        *head = (*head)->next;
        free(tmp->text);
        free(tmp);
    }

    if (*head == NULL) return;
    Task *current = *head;
    while (current->next != NULL){
        if (current->next->priority <=2){
            Task *del = current->next;
            current->next = del->next;
            free(del->next);
            free(del);
        }else{
            current = current->next;
        }
    }
}

void free_list(Task *head){
    while (head!=NULL){
        Task *next =head->next;
        free(head->text);
        free(head);
        head=next;
    } 
}


int main() {
    Task *head = NULL;

    push_back(&head, create_task("Bevasarlas", 3));
    push_back(&head, create_task("Tanulas", 5));
    push_back(&head, create_task("Mosas", 2));
    push_back(&head, create_task("Edzes", 1));

    printf("Eredeti lista:\n");
    print_tasks(head);

    remove_low_priority(&head);

    printf("\nSzures utan:\n");
    print_tasks(head);

    free_list(head);
    return 0;
}
