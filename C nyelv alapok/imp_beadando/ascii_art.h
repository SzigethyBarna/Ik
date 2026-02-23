#ifndef ASCII_ART_H
#define ASCII_ART_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


#define CHAR_COUNT 27
#define MAX_LINE_LENGTH 1024 


typedef struct {
    char **rows;     
    int width;       
} CharArt;


typedef struct {
    CharArt chars[CHAR_COUNT]; 
    int height;                
} Font;


Font *read_font(const char *filename);


void draw_str(const char *str, const Font *font);


void free_font(Font *font);

#endif 