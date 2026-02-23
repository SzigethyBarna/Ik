#include <stdio.h>
#include <stdlib.h>

/*
    Egy bináris keresőfa csomópontja.
    - dist: a tárolt érték
    - left: bal gyerek
    - right: jobb gyerek
*/
typedef struct Node {
    int dist;
    struct Node *left;
    struct Node *right;
} Node;

/*
    Új csomópont létrehozása
*/
Node *create_node(int dist) {
    Node *n = malloc(sizeof(Node));
    if (!n) {
        return NULL;
    }

    n->dist = dist;
    n->left = NULL;
    n->right = NULL;
    return n;
}

/*
    Elem beszúrása a bináris keresőfába
*/
Node *insert(Node *root, int dist) {
    // Ha üres helyet találtunk, ide szúrunk
    if (root == NULL) {
        return create_node(dist);
    }

    // Kisebb érték balra kerül
    if (dist < root->dist) {
        root->left = insert(root->left, dist);
    }
    // Nagyobb érték jobbra kerül
    else if (dist > root->dist) {
        root->right = insert(root->right, dist);
    }
    // Egyenlő értéket nem szúrunk be

    return root;
}

/*
    Fa kiírása inorder bejárással
    (bal - gyökér - jobb)
    Ez rendezett sorrendet ad
*/
void print_tree(Node *root) {
    if (root == NULL) {
        return;
    }

    print_tree(root->left);
    printf("%d\n", root->dist);
    print_tree(root->right);
}

/*
    Elem törlése a fából
    Csak 0 vagy 1 gyerekes esetet kezelünk
*/
Node *delete_node(Node *root, int dist) {
    if (root == NULL) {
        return NULL;
    }

    if (dist < root->dist) {
        root->left = delete_node(root->left, dist);
    }
    else if (dist > root->dist) {
        root->right = delete_node(root->right, dist);
    }
    else {
        // Megtaláltuk a törlendő elemet

        // Nincs bal gyerek
        if (root->left == NULL) {
            Node *tmp = root->right;
            free(root);
            return tmp;
        }

        // Nincs jobb gyerek
        if (root->right == NULL) {
            Node *tmp = root->left;
            free(root);
            return tmp;
        }

        // Két gyerekes esetet nem kezeljük (ZH-n elfogadott)
    }

    return root;
}

/*
    A teljes fa felszabadítása
*/
void free_tree(Node *root) {
    if (root == NULL) {
        return;
    }

    free_tree(root->left);
    free_tree(root->right);
    free(root);
}

/*
    Főprogram – menüvezérelt működés
*/
int main() {
    Node *root = NULL;
    int choice;
    int x;

    while (1) {
        printf("\nMENÜ:\n");
        printf("1 - Elem beszúrása\n");
        printf("2 - Fa kiírása (rendezve)\n");
        printf("3 - Elem törlése\n");
        printf("4 - Kilépés\n");
        printf("Választás: ");

        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Beszúrandó szám: ");
                scanf("%d", &x);
                root = insert(root, x);
                break;

            case 2:
                printf("Fa elemei:\n");
                print_tree(root);
                break;

            case 3:
                printf("Törlendő szám: ");
                scanf("%d", &x);
                root = delete_node(root, x);
                break;

            case 4:
                free_tree(root);
                printf("Kilépés.\n");
                return 0;

            default:
                printf("Hibás választás!\n");
        }
    }
}