#include "ascii_art.h"


static int get_char_index(char c) {
    if (c >= 'A' && c <= 'Z') {
        return c - 'A';
    } else if (c >= 'a' && c <= 'z') {
        
        return toupper(c) - 'A';
    } else if (c == ' ') {
        return CHAR_COUNT - 1; 
    }
    return -1; 
}


void draw_str(const char *str, const Font *font) {
    if (str == NULL || font == NULL) {
        return;
    }

    
    for (int i = 0; i < font->height; i++) {
        
        for (const char *p = str; *p != '\0'; p++) {
            int idx = get_char_index(*p);

            if (idx != -1) {
                printf("%s", font->chars[idx].rows[i]);
            } else {
                fprintf(stderr,"Nem támogatott karakter kihagyva: '%c'\n", *p);
            }
        }
        printf("\n"); 
    }
}



int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Hiba: Kevés vagy rossz parancssori argumentum.\n");
        fprintf(stderr, "Használat: %s <betűkészlet_fájl_neve>\n", argv[0]);
        return EXIT_FAILURE;
    }

    const char *font_filename = argv[1];

    
    Font *font = read_font(font_filename);
    if (font == NULL) {
        return EXIT_FAILURE;
    }

    printf("Betűkészlet betöltve (%d sor magas).\n", font->height);
    printf("Kérlek adj meg szövegeket az ASCII art generáláshoz (EOF (Ctrl+D / Ctrl+Z) küldésével léphetsz ki):\n");

    char input_buffer[MAX_LINE_LENGTH];
    while (fgets(input_buffer, MAX_LINE_LENGTH, stdin) != NULL) {
        size_t len = strlen(input_buffer);
        if (len > 0 && input_buffer[len - 1] == '\n') {
            input_buffer[len - 1] = '\0';
        }
        
        if (strlen(input_buffer) > 0) {
            draw_str(input_buffer, font);
        }
    }

    free_font(font);

    printf("\nProgram leállítva.\n");
    return EXIT_SUCCESS;
}