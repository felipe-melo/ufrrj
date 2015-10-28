#include <iostream>
#include <utility>
#include <unistd.h>

using namespace std;

void changeMenuPressed(int key) {
	
}

bool isInGrid(SDL_Rect *grid, SDL_Rect *rect){

	if(rect->x < grid->x || rect->y < grid->y) {
		return false;
    }
    
    if(rect->x + rect->w > grid->x + 354 || rect->y + rect->h > grid->y + 355) {
        return false;
    }
	return true;
}

bool isShipSelected(int *offsetX, int *offsetY, SDL_Rect *rect, int x, int y){
	if(intersects(&(*rect), x, y)) {
		*offsetX = x - rect->x;
		*offsetY = y - rect->y;
		return true;
    }
    return false;
}

bool isRectSelected(SDL_Rect *rect, int x, int y){
	if(intersects(&(*rect), x, y)) {
		return true;
    }
    return false;
}

void setBoats(int *screenView, SDL_Surface *screen, int (&positions)[4][2], SDL_bool *quit){

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

    int offsetX 			= 0;
    int offsetY 			= 0;

    bool draggingL 			= false;
    bool draggingM1			= false;
    bool draggingM2			= false;
    bool draggingS 			= false;
    
    bool isInGridL 			= false;
    bool isInGridM1			= false;
    bool isInGridM2			= false;
    bool isInGridS 			= false;

	SDL_Surface *background;

	SDL_Surface *title;
	SDL_Surface *grid;
	SDL_Surface *play;

	SDL_Surface *letters[10];
	SDL_Surface *numbers[10];

	SDL_Surface *largeShip;
	SDL_Surface *mediumShip1;
	SDL_Surface *mediumShip2;
	SDL_Surface *smallShip;

	SDL_Rect rectLargeShip 	 = {70, 200, 35, 140};
	SDL_Rect rectmediumShip1 = {120, 200, 35, 105};
	SDL_Rect rectmediumShip2 = {170, 200, 35, 105};
	SDL_Rect rectsmallShip 	 = {220, 200, 35, 70};
	
	SDL_Rect rectBackground = {0, 0, 800, 500};
	SDL_Rect rectGrid  		= {350, 100, 354, 355};
	SDL_Rect rectTitle 		= {135, 30, 0, 0};
	SDL_Rect rectPlay 		= {120, 400, 40, 15};
	
	SDL_Event event;

	loadImageFile(&background, "images/sea.png");
	loadImageFile(&grid, "images/single_grid.png");

	loadImageFile(&largeShip, "images/ship_large.png");
	loadImageFile(&mediumShip1, "images/ship_medium1.png");
	loadImageFile(&mediumShip2, "images/ship_medium2.png");
	loadImageFile(&smallShip, "images/ship_small.png");

	title = TTF_RenderText_Blended (fontTitle, SET_SHIPS, green);
	play  = TTF_RenderText_Blended (fontMenu, PLAY_LINK, green);

	for (int i = 0; i < 10; i++){
		letters[i] = TTF_RenderText_Blended (fontMenu, letters_A_to_J[i], green);
		numbers[i] = TTF_RenderText_Blended (fontMenu, numbers_1_to_10[i], green);
	}
	
	while (*screenView == SET_BOATS){
		while (SDL_PollEvent(&event)){
			switch (event.type){
				case SDL_QUIT:
					*quit = SDL_TRUE;
					return;

				case SDL_KEYDOWN:
					int key = event.key.keysym.sym;

					if (key == SDLK_ESCAPE){
						*screenView = MENU;
						return;
					}
					break;
			}

			if (event.type == SDL_MOUSEBUTTONDOWN) {
				if (event.button.button & SDL_BUTTON_LEFT == SDL_BUTTON_LEFT){
					if (draggingL 	= isShipSelected(&offsetX, &offsetY, &rectLargeShip, event.button.x, event.button.y)) break;
					if (draggingM1 	= isShipSelected(&offsetX, &offsetY, &rectmediumShip1, event.button.x, event.button.y)) break;
					if (draggingM2 	= isShipSelected(&offsetX, &offsetY, &rectmediumShip2, event.button.x, event.button.y)) break;
					if (draggingS 	= isShipSelected(&offsetX, &offsetY, &rectsmallShip, event.button.x, event.button.y)) break;
				}
			} else if (event.type == SDL_MOUSEBUTTONUP) {
				offsetX = 0;
				offsetY = 0;
				if((event.button.button & SDL_BUTTON_LEFT) == SDL_BUTTON_LEFT) {
					if (draggingL) draggingL 	= false;
					if (draggingM1) draggingM1 	= false;
					if (draggingM2) draggingM2 	= false;
					if (draggingS) draggingS 	= false;
                }

                if (isInGridL && isInGridM1 && isInGridM2 && isInGridS && isRectSelected(&rectPlay, event.button.x, event.button.y)){
                	positions[0][0] = (rectLargeShip.x - rectGrid.x) / 35;
                	positions[0][1] = (rectLargeShip.y - rectGrid.y) / 35;
                	positions[1][0] = (rectmediumShip1.x - rectGrid.x) / 35;
                	positions[1][1] = (rectmediumShip1.y - rectGrid.y) / 35;

                	positions[2][0] = (rectmediumShip2.x - rectGrid.x) / 35;
                	positions[2][1] = (rectmediumShip2.y - rectGrid.y) / 35;
                	positions[3][0] = (rectsmallShip.x - rectGrid.x) / 35;
                	positions[3][1] = (rectsmallShip.y - rectGrid.y) / 35;
                	
                	*screenView = LOADING;
                	return;
				}
                
                if (isInGrid(&rectGrid, &rectLargeShip)) {
                	isInGridL = true; 
                	rectLargeShip.x = ((rectLargeShip.x - rectGrid.x) / 35) * 35 + rectGrid.x;
                	rectLargeShip.y = ((rectLargeShip.y - rectGrid.y) / 35) * 35 + rectGrid.y;
                }else {
                	isInGridL = false;
                }
                if (isInGrid(&rectGrid, &rectmediumShip1)) {
                	isInGridM1 = true;
                	rectmediumShip1.x = ((rectmediumShip1.x - rectGrid.x) / 35) * 35 + rectGrid.x;
                	rectmediumShip1.y = ((rectmediumShip1.y - rectGrid.y) / 35) * 35 + rectGrid.y;
                }else {
                	isInGridM1 = false;
                }
                if (isInGrid(&rectGrid, &rectmediumShip2)) {
                	isInGridM2 = true;
                	rectmediumShip2.x = ((rectmediumShip2.x - rectGrid.x) / 35) * 35 + rectGrid.x;
                	rectmediumShip2.y = ((rectmediumShip2.y - rectGrid.y) / 35) * 35 + rectGrid.y;
                }else{
                	isInGridM2 = false;
                }
                if (isInGrid(&rectGrid, &rectsmallShip)) {
                	isInGridS = true;
                	rectsmallShip.x = ((rectsmallShip.x - rectGrid.x) / 35) * 35 + rectGrid.x;
                	rectsmallShip.y = ((rectsmallShip.y - rectGrid.y) / 35) * 35 + rectGrid.y;
                }else{
                	isInGridS = false;
                }

			}else if (event.type == SDL_MOUSEMOTION) {
                if(draggingL) {
					rectLargeShip.x = event.motion.x - offsetX;
					rectLargeShip.y = event.motion.y - offsetY;
                }
                if(draggingM1) {
					rectmediumShip1.x = event.motion.x - offsetX;
					rectmediumShip1.y = event.motion.y - offsetY;
                }
                if(draggingM2) {
					rectmediumShip2.x = event.motion.x - offsetX;
					rectmediumShip2.y = event.motion.y - offsetY;
                }
                if(draggingS) {
					rectsmallShip.x = event.motion.x - offsetX;
					rectsmallShip.y = event.motion.y - offsetY;
                }
            }
		}
		SDL_BlitSurface(background, &rectBackground, screen, NULL);
		SDL_BlitSurface(grid, NULL, screen, &rectGrid);
		SDL_BlitSurface(title, NULL, screen, &rectTitle);

		SDL_BlitSurface(largeShip, NULL, screen, &rectLargeShip);
		SDL_BlitSurface(mediumShip1, NULL, screen, &rectmediumShip1);
		SDL_BlitSurface(mediumShip2, NULL, screen, &rectmediumShip2);
		SDL_BlitSurface(smallShip, NULL, screen, &rectsmallShip);

		if (isInGridL && isInGridM1 && isInGridM2 && isInGridS){
			SDL_BlitSurface(play, NULL, screen, &rectPlay);
		}

		for (int i = 0; i < 10; i++){
			SDL_BlitSurface(letters[i], NULL, screen, &rectLetters[i]);
			SDL_BlitSurface(numbers[i], NULL, screen, &rectNumbers[i]);
		}
		SDL_Flip (screen);
	}
}