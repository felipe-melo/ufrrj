#include "SDL/SDL.h"
#include "SDL/SDL_image.h"
#include "SDL/SDL_ttf.h"
#include "SDL/SDL_mixer.h"

#include <iostream>
#include <string>

using namespace std;

#define PLAY 0
#define WATCH_PLAY 1
#define CLOSE 2

#define NAME "Online Battle Shipp"

#define CONNECTION_ERROR "Erro ao se Conectar"

#define SET_SHIPS "Posicione Sua Frota"

#define PLAY_LINK "Play"

#define QUANT_MENU_OPTION 3

#define MENU 0
#define LOADING 1
#define WATCHING 2
#define SET_BOATS 3
#define PLAYING 4
#define EXIT 5

#define NEW_PLAYER 0
#define MOVEMENT 1
#define WATCH_GAME 2
#define ID 3


#define ADV 0
#define MOV 1

#define SERVER_PORT "4444"

SDL_Color blue = {100, 50, 200};
SDL_Color darkBlue = {50, 0, 200};

SDL_Color green = {60, 120, 20};

SDL_Rect rectLetters[10] = {{340, 110, 10, 10}, {340, 145, 10, 10}, {340, 180, 10, 10}, {340, 215, 10, 10}, {340, 250, 10, 10}, 
							 {340, 285, 10, 10}, {340, 320, 10, 10}, {340, 355, 10, 10}, {340, 390, 10, 10}, {340, 425, 10, 10}};

SDL_Rect rectNumbers[10] = {{365, 80, 10, 10}, {400, 80, 10, 10}, {435, 80, 10, 10}, {470, 80, 10, 10}, {505, 80, 10, 10}, 
							 {540, 80, 10, 10}, {575, 80, 10, 10}, {610, 80, 10, 10}, {645, 80, 10, 10}, {680, 80, 10, 10}};

SDL_Rect rectMyLetters[10] = {{10, 85, 10, 10}, {10, 120, 10, 10}, {10, 155, 10, 10}, {10, 190, 10, 10}, {10, 225, 10, 10}, 
							 {10, 260, 10, 10}, {10, 295, 10, 10}, {10, 330, 10, 10}, {10, 365, 10, 10}, {10, 400, 10, 10}};

SDL_Rect rectMyNumbers[10] = {{40, 50, 10, 10}, {75, 50, 10, 10}, {110, 50, 10, 10}, {145, 50, 10, 10}, {180, 50, 10, 10}, 
							 {215, 50, 10, 10}, {250, 50, 10, 10}, {285, 50, 10, 10}, {320, 50, 10, 10}, {350, 50, 10, 10}};

SDL_Rect rectHisLetters[10] = {{410, 85, 10, 10}, {410, 120, 10, 10}, {410, 155, 10, 10}, {410, 190, 10, 10}, {410, 225, 10, 10}, 
							 {410, 260, 10, 10}, {410, 295, 10, 10}, {410, 330, 10, 10}, {410, 365, 10, 10}, {410, 400, 10, 10}};

SDL_Rect rectHisNumbers[10] = {{445, 50, 10, 10}, {480, 50, 10, 10}, {515, 50, 10, 10}, {550, 50, 10, 10}, {585, 50, 10, 10}, 
							 {620, 50, 10, 10}, {655, 50, 10, 10}, {690, 50, 10, 10}, {725, 50, 10, 10}, {760, 50, 10, 10}};

//for watching
SDL_Rect rectLetters1[10] = {{10, 85, 10, 10}, {10, 120, 10, 10}, {10, 155, 10, 10}, {10, 190, 10, 10}, {10, 225, 10, 10},
							 {10, 260, 10, 10}, {10, 295, 10, 10}, {10, 330, 10, 10}, {10, 365, 10, 10}, {10, 400, 10, 10}};

SDL_Rect rectNumbers1[10] = {{40, 50, 10, 10}, {75, 50, 10, 10}, {110, 50, 10, 10}, {145, 50, 10, 10}, {180, 50, 10, 10}, 
							 {215, 50, 10, 10}, {250, 50, 10, 10}, {285, 50, 10, 10}, {320, 50, 10, 10}, {350, 50, 10, 10}};

SDL_Rect rectLetters2[10] = {{410, 85, 10, 10}, {410, 120, 10, 10}, {410, 155, 10, 10}, {410, 190, 10, 10}, {410, 225, 10, 10}, 
							 {410, 260, 10, 10}, {410, 295, 10, 10}, {410, 330, 10, 10}, {410, 365, 10, 10}, {410, 400, 10, 10}};

SDL_Rect rectNumbers2[10] = {{445, 50, 10, 10}, {480, 50, 10, 10}, {515, 50, 10, 10}, {550, 50, 10, 10}, {585, 50, 10, 10}, 
							 {620, 50, 10, 10}, {655, 50, 10, 10}, {690, 50, 10, 10}, {725, 50, 10, 10}, {760, 50, 10, 10}};

//for watching

const char* letters_A_to_J[10] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
const char* numbers_1_to_10[10] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

void loadImageFile(SDL_Surface **image, const char* path){
	*image = IMG_Load(path);
}

bool intersects(SDL_Rect *rect, int mouseX, int mouseY) {
	if(mouseX < rect->x || mouseY < rect->y) {
		return false;
    }
    
    if(mouseX > rect->x + rect->w || mouseY > rect->y + rect->h) {
        return false;
    }
	return true;
}