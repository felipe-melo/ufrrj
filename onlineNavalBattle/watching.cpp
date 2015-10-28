#include <iostream>
#include <utility>
#include <unistd.h>
#include <vector>

#include "listenning.cpp"

using namespace std;

void listenningConfig(string port);

/*config para listenning*/
int create_socketW, new_socketW;
socklen_t addrlenW;
int bufsizeW;
char *bufferW;
struct sockaddr_in addressW;
/*config para listenning*/

void watching(int *screenView, SDL_Surface *screen, SDL_bool *quit, string port){

	int id1;
	int id2;
	int id;

	int playX;
	int playY;

	int hit, over = 0;

	bool first = true;

	const char *msg;
	int len;
	ssize_t bytes_sent;

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

	SDL_Surface *background;
	SDL_Surface *grid;

	SDL_Surface *win;

	SDL_Surface *player1;
	SDL_Surface *player2;
	
	SDL_Rect rectBackground  = {0, 0, 800, 500};
	SDL_Rect rectGrid  		 = {20, 40, 354, 355};
	SDL_Rect rectWin  		 = {20, 40, 354, 355};

	vector<SDL_Rect*> bombsRect;
	vector<SDL_Surface*> bombs;
	
	SDL_Event event;

	SDL_Surface *letters1[10];
	SDL_Surface *numbers1[10];

	SDL_Surface *letters2[10];
	SDL_Surface *numbers2[10];

	SDL_Rect rectPlayer1 	 = {130, 20, 35, 70};
	SDL_Rect rectPlayer2 	 = {570, 20, 35, 70};

	for (int i = 0; i < 10; i++){
		letters1[i] = TTF_RenderText_Blended (fontMenu, letters_A_to_J[i], green);
		numbers1[i] = TTF_RenderText_Blended (fontMenu, numbers_1_to_10[i], green);

		letters2[i] = TTF_RenderText_Blended (fontMenu, letters_A_to_J[i], green);
		numbers2[i] = TTF_RenderText_Blended (fontMenu, numbers_1_to_10[i], green);
	}

	player1 = TTF_RenderText_Blended (fontMenu, "Player1", green);
	player2 = TTF_RenderText_Blended (fontMenu, "Player2", green);
	win = TTF_RenderText_Blended (fontMenu, "Acabou", green);

	loadImageFile(&background, "images/sea.png");
	loadImageFile(&grid, "images/grid.png");

	stringstream teste;
	teste << WATCH_GAME << " " << port.c_str();

	pair<bool, string> values = makeRequest(teste.str().c_str(), "127.0.0.1", SERVER_PORT);

	sscanf (values.second.c_str(), "%d %d", &id1, &id2);

	while (*screenView == WATCHING) {
		while (SDL_PollEvent(&event)) {
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
		SDL_BlitSurface(grid, NULL, screen, &rectGrid);

		for (int i = 0; i < 10; i++) {
			SDL_BlitSurface(letters1[i], NULL, screen, &rectLetters1[i]);
			SDL_BlitSurface(numbers1[i], NULL, screen, &rectNumbers1[i]);

			SDL_BlitSurface(letters2[i], NULL, screen, &rectLetters2[i]);
			SDL_BlitSurface(numbers2[i], NULL, screen, &rectNumbers2[i]);
		}

		for (int i = 0; i < bombs.size(); i++) {
			SDL_BlitSurface(bombs[i], NULL, screen, bombsRect[i]);
		}

		SDL_BlitSurface(player1, NULL, screen, &rectPlayer1);
		SDL_BlitSurface(player2, NULL, screen, &rectPlayer2);
		SDL_BlitSurface(win, NULL, screen, &rectWin);

		SDL_Flip (screen);

		if (over == 1) continue;

		if (first) {
			first = false;
			listenningConfig(port);
		}

		if (listen(create_socketW, 3) == -1){
	        cout << "listen error" << endl;
		}

		addrlenW = sizeof(struct sockaddr_in);
		new_socketW = accept(create_socketW, (struct sockaddr *)&addressW, &addrlenW);

		char incomming_data_buffer[1000] = {0};
		ssize_t bytes_recieved = recv(new_socketW, incomming_data_buffer, 1000, 0);

		sscanf (incomming_data_buffer, "%d %d %d %d %d", &id, &playY, &playX, &hit, &over);

		SDL_Rect *bombRect = new SDL_Rect();
		SDL_Surface* bomb = new SDL_Surface();

		if (id == id1){
			bombRect->x = 30 + playX * 35;
			bombRect->y = 83 + playY * 35;
			bombRect->w = 35;
			bombRect->h = 35;
		}else if (id == id2){
			bombRect->x = 438 + playX * 35;
			bombRect->y = 83 + playY * 35;
			bombRect->w = 35;
			bombRect->h = 35;
		}
		
		if (hit == 0){
			loadImageFile(&bomb, "images/x.png");
		}else{
			loadImageFile(&bomb, "images/explosao.png");
		}
		bombsRect.push_back(bombRect);
		bombs.push_back(bomb);

		msg = "ok";
		len = strlen(msg);
		bytes_sent = send(new_socketW, msg, len, 0);
	}
}

void listenningConfig(string port) {
	/*config para listenning*/
	bufsizeW = 1024;
	bufferW = (char*) malloc(bufsizeW);

	if ((create_socketW = socket(AF_INET,SOCK_STREAM,0)) > 0)
	printf("The socket was created\n");
	addressW.sin_family = AF_INET;
	addressW.sin_addr.s_addr = INADDR_ANY;
	addressW.sin_port = htons(atoi(port.c_str()));
	if (bind(create_socketW,(struct sockaddr *)&addressW, sizeof(addressW)) == 0)
		printf("Binding Socket\n");
	/*config para listenning*/
}