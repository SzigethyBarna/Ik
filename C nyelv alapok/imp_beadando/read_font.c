#include "ascii_art.h"

static void strip_newline(char *str) {
    size_t len = strlen(str);
    if (len > 0 && str[len - 1] == '\n') {
        str[len - 1] = '\0';
    }
}

Font *read_font(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Hiba a betűkészlet fájl megnyitásakor");
        return NULL;
    }

    Font *font = (Font *)malloc(sizeof(Font));
    if (font == NULL) {
        fclose(file);
        perror("Memóriafoglalási hiba a Font struktúrához");
        return NULL;
    }

    char buffer[MAX_LINE_LENGTH];
    
    if (fgets(buffer, MAX_LINE_LENGTH, file) == NULL) {
        fprintf(stderr, "Hiba: A betűkészlet fájl üres vagy olvashatatlan.\n");
        free(font);
        fclose(file);
        return NULL;
    }
    font->height = atoi(buffer);
    if (font->height <= 0) {
        fprintf(stderr, "Hiba: Érvénytelen magasság a fájlban: %d.\n", font->height);
        free(font);
        fclose(file);
        return NULL;
    }

    int space_idx = CHAR_COUNT - 1;
    font->chars[space_idx].width = 1;
    font->chars[space_idx].rows = (char **)calloc(font->height, sizeof(char *));
    if (font->chars[space_idx].rows == NULL) {
        free(font);
        fclose(file);
        return NULL; 
    }
    for (int i = 0; i < font->height; i++) {
        font->chars[space_idx].rows[i] = (char *)malloc(2 * sizeof(char));
        if (font->chars[space_idx].rows[i] == NULL) {
            free(font);
            fclose(file);
            return NULL;
        }
        strcpy(font->chars[space_idx].rows[i], " ");
    }


    // 3. A-Z betűk beolvasása
    for (int i = 0; i < CHAR_COUNT - 1; i++) { // CHAR_COUNT - 1 = 26 (A-Z)
        CharArt *current_char = &font->chars[i];
        current_char->rows = (char **)calloc(font->height, sizeof(char *));
        if (current_char->rows == NULL) {
            // Komplexebb felszabadítás...
            free(font);
            fclose(file);
            return NULL;
        }

        // Sorok beolvasása az aktuális betűhöz (magasság * sor)
        for (int j = 0; j < font->height; j++) {
            if (fgets(buffer, MAX_LINE_LENGTH, file) == NULL) {
                fprintf(stderr, "Hiba: Váratlan EOF a betűkészlet fájlban a betűk olvasása közben.\n");
                // Komplexebb felszabadítás...
                free(font);
                fclose(file);
                return NULL;
            }
            
            strip_newline(buffer);
            
            // Dinamikus memóriafoglalás a sor tartalmához
            current_char->rows[j] = strdup(buffer);
            if (current_char->rows[j] == NULL) {
                // Komplexebb felszabadítás...
                free(font);
                fclose(file);
                return NULL;
            }
        }
        
        // Vastagság beállítása (ugyanaz minden sorra)
        current_char->width = (int)strlen(current_char->rows[0]);
    }

    fclose(file);
    return font;
}

// Dinamikusan foglalt memória felszabadítása
void free_font(Font *font) {
    if (font == NULL) {
        return;
    }

    for (int i = 0; i < CHAR_COUNT; i++) {
        CharArt *current_char = &font->chars[i];
        if (current_char->rows != NULL) {
            for (int j = 0; j < font->height; j++) {
                free(current_char->rows[j]);
            }
            free(current_char->rows);
        }
    }
    free(font);
}