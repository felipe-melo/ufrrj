const char* menuOptionsText[] = {"Game","Assistir Jogo", "Sair"};

void enterMenuPressed(int selected, int *screenView, SDL_bool *quit){
	switch (selected){
		case PLAY:
		*screenView = SET_BOATS;
		break;
		case WATCH_PLAY:
		*screenView = WATCHING;
		break;
		case CLOSE:
		*screenView = EXIT;
		*quit = SDL_TRUE;
	}
}

void changeMenuPressed(int key, int *selected){
	int index = *selected;
	if (key == SDLK_UP){	
		*selected = (index-1 < 0 ? QUANT_MENU_OPTION - 1 : index-1) % QUANT_MENU_OPTION;
	}else{
		*selected = (index+1) % QUANT_MENU_OPTION;
	}
}

void runMenu(int *screenView, SDL_Surface *screen, SDL_bool *quit){

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

	int menuSelected = 0; //Inicialmente o primeiro item do menu está selecionado

	SDL_Surface *background;

	SDL_Surface *title;
	SDL_Surface **menuOptions;
	SDL_Surface *bomb;
	
	SDL_Rect rectBackground  = {0, 0, 800, 500};
	SDL_Rect rectTitle 		 = {130, 80, 0, 0};
	SDL_Rect rectMenu[] 	 = {{350, 350, 0, 0},{350, 370, 0, 0}, {350, 390, 0, 0},{350, 410, 0, 0}};
	SDL_Rect rectBomb  		 = {315, 350, 0, 0};

	SDL_Event event;

	menuOptions = (SDL_Surface**) malloc(sizeof(SDL_Surface*) * QUANT_MENU_OPTION);

	loadImageFile(&background, "images/menu_background.png");
	loadImageFile(&bomb, "images/bomb.png");

	title = TTF_RenderText_Blended (fontTitle, NAME, blue);

	while (*screenView == MENU){
		while (SDL_PollEvent(&event)){
			switch (event.type){
				case SDL_QUIT:
					*quit = SDL_TRUE;
					return;
				case SDL_KEYDOWN:

					int key = event.key.keysym.sym;

					if (key == SDLK_ESCAPE){
						*quit = SDL_TRUE;
						return;
					}
					
					if (key == SDLK_UP || key == SDLK_DOWN){
						changeMenuPressed(key, &menuSelected);
						break;
					}
					if (key == SDLK_KP_ENTER || key == SDLK_RETURN){
						enterMenuPressed(menuSelected, screenView, quit);
						break;
					}
					break;
			}
		}
		rectBomb.y = 350 + (menuSelected * 20);
		SDL_BlitSurface(background, &rectBackground, screen, NULL); //fundo do game
		SDL_BlitSurface(title, NULL, screen, &rectTitle);
		SDL_BlitSurface(bomb, NULL, screen, &rectBomb);

		for (int i = 0; i < QUANT_MENU_OPTION; i++){
			menuOptions[i]	= TTF_RenderText_Blended (fontMenu, menuOptionsText[i], (i == menuSelected ? darkBlue : blue));
			SDL_BlitSurface(menuOptions[i], NULL, screen, &(rectMenu[i]));
		}
		SDL_Flip (screen);
	}
}