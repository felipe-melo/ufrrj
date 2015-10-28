/*
	Universidade Federal Rural do Rio de Janeiro
	Projeto: Online Naval Battle
	Nome: Felipe Melo
	Disciplina: Laboratório de Rede de Computadores
	Professor: Marcel
	Início: 23/05/2015
	Fim: 23/05/2015
*/

#include <iostream>
#include <sstream>
#include <string>

#include "Constants.cpp"

#include "menu_func.cpp"
#include "client.cpp"
#include "loading.cpp"
#include "Boats.cpp"
#include "playing.cpp"
#include "watching.cpp"

using namespace std;

int main(int argc, char* argv[]) {

	string port = argv[1];

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

	int screenView = MENU;

	SDL_bool quit = SDL_FALSE;

	SDL_Surface *screen;

	string buffer;

	SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO | TTF_Init());

	int positions[4][2];
	
	screen = SDL_SetVideoMode(800, 500, 16, 0);

	while (!quit){

		switch (screenView){
			case MENU:
				runMenu(&screenView, screen, &quit);
				break;

			case LOADING:
				runLoading(&screenView, screen, positions, &buffer, &quit, port);
				break;

			case WATCHING:
				watching(&screenView, screen, &quit, port);
				break;

			case PLAYING:
				playing(&screenView, screen, positions, buffer, &quit, port);
				break;

			case SET_BOATS:
				setBoats(&screenView, screen, positions, &quit);
				break;

			default:
				cout << "Nenhuma tela está selecionada\n";
		}
	}

	SDL_Quit();
	TTF_Quit();

	return 0;
}