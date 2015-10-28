#include <iostream>
#include <utility>
#include <unistd.h>

using namespace std;

void runLoading(int *screenView, SDL_Surface *screen, int positions[4][2], string *buffer, SDL_bool *quit, string port){

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

	pair<bool, string> values;

	SDL_Surface *background;

	SDL_Surface *title;
	SDL_Surface *loadingMessage;
	
	SDL_Rect rectBackground  = {0, 0, 800, 500};
	SDL_Rect rectTitle 		 = {130, 80, 0, 0};
	SDL_Rect rectLoading	 = {380, 450, 0, 0};
	
	SDL_Event event;

	loadImageFile(&background, "images/menu_background.png");

	title = TTF_RenderText_Blended (fontTitle, NAME, blue);
	loadingMessage = TTF_RenderText_Blended (fontMenu, "Carregando...", blue);
	
	while (*screenView == LOADING){
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
					break;
			}
		}
		SDL_BlitSurface(background, &rectBackground, screen, NULL);
		SDL_BlitSurface(loadingMessage, NULL, screen, &rectLoading);
		SDL_Flip (screen);

		stringstream teste;

		teste << NEW_PLAYER << " ";
		teste << "(" << positions[0][0] << "),";
		teste << "(" << positions[0][1] << "),";
		teste << "(" << positions[1][0] << "),";
		teste << "(" << positions[1][1] << "),";
		teste << "(" << positions[2][0] << "),";
		teste << "(" << positions[2][1] << "),";
		teste << "(" << positions[3][0] << "),";
		teste << "(" << positions[3][1] << ") " << port;

		cout << teste << endl;

		values = makeRequest(teste.str().c_str(), "127.0.0.1", SERVER_PORT);
		
		if (!values.first){
			loadingMessage = TTF_RenderText_Blended (fontMenu, CONNECTION_ERROR, blue);
			SDL_BlitSurface(background, &rectBackground, screen, NULL);
			SDL_BlitSurface(title, NULL, screen, &rectTitle);
			rectLoading.x = 320;
			SDL_BlitSurface(loadingMessage, NULL, screen, &rectLoading);
			SDL_Flip (screen);
			sleep(5);
			*screenView = MENU;
		}else{
			(*buffer) = values.second;
			*screenView = PLAYING;
		}
	}
}