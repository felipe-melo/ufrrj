#include <iostream>
#include <utility>
#include <unistd.h>
#include <vector>

#include "listenning.cpp"

using namespace std;

void playingListenningConfig(string port);

bool isInGrid(int gX, int gY, int gW, int gH, int x, int y){

	if(gX > x || gY > y) {
		return false;
    }
    
    if(gX + gW < x || gY + gH < y) {
        return false;
    }
	return true;
}

/*config para listenning*/
int create_socket, new_socket, op;
socklen_t addrlen;
int bufsize;
char *buffer;
struct sockaddr_in address;
/*config para listenning*/

void playing(int *screenView, SDL_Surface *screen, int positions[4][2], string buffer, SDL_bool *quit, string port){

	int id;
	int idAdv;
	bool isYourTurn, didPlay = false, first = true;
	bool over = false, winner = false;

	int retorno, ganhou;

	int hisGrid[10][10];

	for (int i = 0; i < 10; i++){
		for (int j = 0; j < 10; j++){
			hisGrid[i][j] = 0;
		}
	}

	int playX, playY, hit;

	const char *msg;
	int len;
	ssize_t bytes_sent;

	sscanf (buffer.c_str(), "%d %d", &id, &idAdv);

	if (idAdv == 0) {
		isYourTurn = false;
		cout << "not your turn\n";
	}else{
		isYourTurn = true;
		cout << "your turn\n";
	}

	TTF_Font *fontTitle = TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 50);
	TTF_Font *fontMenu 	= TTF_OpenFont("fonts/TT Masters DEMO Black.otf", 20);

	SDL_Surface *background;
	SDL_Surface *grid;
	
	SDL_Rect rectBackground  = {0, 0, 800, 500};
	SDL_Rect rectGrid  		 = {20, 40, 354, 355};

	SDL_Rect rectWin  		 = {20, 40, 354, 355};
	SDL_Rect rectLose  		 = {20, 40, 354, 355};

	vector<SDL_Rect*> bombsRect;
	vector<SDL_Surface*> bombs;

	vector<SDL_Rect*> hisBombsRect;
	vector<SDL_Surface*> hisBombs;
	
	SDL_Event event;

	SDL_Surface *myLetters[10];
	SDL_Surface *myNumbers[10];
	SDL_Surface *yours;
	SDL_Surface *his;
	SDL_Surface *win;
	SDL_Surface *lose;

	SDL_Surface *hisLetters[10];
	SDL_Surface *hisNumbers[10];

	SDL_Surface *largeShip;
	SDL_Surface *mediumShip1;
	SDL_Surface *mediumShip2;
	SDL_Surface *smallShip;

	SDL_Rect rectLargeShip 	 = {positions[0][0] * 35 + 20, positions[0][1] * 35 + 75, 35, 140};
	SDL_Rect rectMediumShip1 = {positions[1][0] * 35 + 20, positions[1][1] * 35 + 75, 35, 105};
	SDL_Rect rectMediumShip2 = {positions[2][0] * 35 + 20, positions[2][1] * 35 + 75, 35, 105};
	SDL_Rect rectSmallShip 	 = {positions[3][0] * 35 + 20, positions[3][1] * 35 + 75, 35, 70};
	SDL_Rect rectYours 	 = {130, 20, 35, 70};
	SDL_Rect rectHis 	 = {570, 20, 35, 70};

	loadImageFile(&largeShip, "images/ship_large.png");
	loadImageFile(&mediumShip1, "images/ship_medium1.png");
	loadImageFile(&mediumShip2, "images/ship_medium2.png");
	loadImageFile(&smallShip, "images/ship_small.png");

	for (int i = 0; i < 10; i++){
		myLetters[i] = TTF_RenderText_Blended (fontMenu, letters_A_to_J[i], green);
		myNumbers[i] = TTF_RenderText_Blended (fontMenu, numbers_1_to_10[i], green);

		hisLetters[i] = TTF_RenderText_Blended (fontMenu, letters_A_to_J[i], green);
		hisNumbers[i] = TTF_RenderText_Blended (fontMenu, numbers_1_to_10[i], green);
	}

	yours = TTF_RenderText_Blended (fontMenu, "Your Grid", green);
	his = TTF_RenderText_Blended (fontMenu, "His Grid", green);

	win = TTF_RenderText_Blended (fontMenu, "Você Ganhou", green);
	lose = TTF_RenderText_Blended (fontMenu, "Você Perdeu", green);

	loadImageFile(&background, "images/sea.png");
	loadImageFile(&grid, "images/grid.png");
	
	while (*screenView == PLAYING){
		while (isYourTurn && SDL_PollEvent(&event)){
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

			if (event.type == SDL_MOUSEBUTTONUP) {
				if((event.button.button & SDL_BUTTON_LEFT) == SDL_BUTTON_LEFT) {
					if (isInGrid(438, 83, 340, 340, event.button.x, event.button.y)){
						playX = (event.button.x - 438)/35;
						playY = (event.button.y - 83)/35;
						if (hisGrid[playX][playY] == 0){
							didPlay = true;
							hisGrid[playX][playY] = 1;
						}else{
							cout << "já jogou ai\n";
						}
					}
                }
			}
		}

		SDL_BlitSurface(background, &rectBackground, screen, NULL);
		SDL_BlitSurface(grid, NULL, screen, &rectGrid);

		SDL_BlitSurface(largeShip, NULL, screen, &rectLargeShip);
		SDL_BlitSurface(mediumShip1, NULL, screen, &rectMediumShip1);
		SDL_BlitSurface(mediumShip2, NULL, screen, &rectMediumShip2);
		SDL_BlitSurface(smallShip, NULL, screen, &rectSmallShip);

		for (int i = 0; i < 10; i++){
			SDL_BlitSurface(myLetters[i], NULL, screen, &rectMyLetters[i]);
			SDL_BlitSurface(myNumbers[i], NULL, screen, &rectMyNumbers[i]);

			SDL_BlitSurface(hisLetters[i], NULL, screen, &rectHisLetters[i]);
			SDL_BlitSurface(hisNumbers[i], NULL, screen, &rectHisNumbers[i]);
		}

		for (int i = 0; i < bombs.size(); i++){
			SDL_BlitSurface(bombs[i], NULL, screen, bombsRect[i]);
		}

		for (int i = 0; i < hisBombs.size(); i++){
			SDL_BlitSurface(hisBombs[i], NULL, screen, hisBombsRect[i]);
		}

		if (over){
			if (winner)
				SDL_BlitSurface(win, NULL, screen, &rectWin);
			else
				SDL_BlitSurface(lose, NULL, screen, &rectLose);
		}

		SDL_BlitSurface(yours, NULL, screen, &rectYours);
		SDL_BlitSurface(his, NULL, screen, &rectHis);

		SDL_Flip (screen);

		if (over) continue;

		if (!isYourTurn){
			if (first){
				first = false;
				playingListenningConfig(port);
			}
			AGAIN:
			if (listen(create_socket, 3) == -1){
		        cout << "listen error" << endl;
			}

			addrlen = sizeof(struct sockaddr_in);
			new_socket = accept(create_socket, (struct sockaddr *)&address, &addrlen);

			char incomming_data_buffer[1000] = {0};
			ssize_t bytes_recieved = recv(new_socket, incomming_data_buffer, 1000, 0);

			sscanf (incomming_data_buffer, "%d", &op);

			string buffer_return;

			switch (op){
				case ADV:
					msg = "ok";
					len = strlen(msg);
					bytes_sent = send(new_socket, msg, len, 0);
					goto AGAIN;
				case MOV:
					isYourTurn = true;
					didPlay = false;
					sscanf (incomming_data_buffer, "%d %d %d %d %d", &op, &playY, &playX, &hit, &ganhou);
					SDL_Rect *bombRect = new SDL_Rect();
					bombRect->x = 30 + playX * 35;
					bombRect->y = 83 + playY * 35;
					bombRect->w = 35;
					bombRect->h = 35;
					SDL_Surface* bomb = new SDL_Surface();
					if (hit == 0){
						loadImageFile(&bomb, "images/x.png");
					}else{
						loadImageFile(&bomb, "images/explosao.png");
					}
					bombsRect.push_back(bombRect);
					bombs.push_back(bomb);

					if (ganhou == 1){
						over = true;
						winner = false;
					}

					break;
			}
			msg = "ok";
			len = strlen(msg);
			bytes_sent = send(new_socket, msg, len, 0);
			SDL_PeepEvents(&event, 20, SDL_PEEKEVENT, SDL_MOUSEMOTIONMASK);
		}else if (didPlay){
			stringstream *jogada = new stringstream();
			
			*jogada << MOVEMENT << " " << id << " " << playY << " " << playX;

			pair<bool, string> values = makeRequest((*jogada).str().c_str(), "127.0.0.1", SERVER_PORT);

			sscanf (values.second.c_str(), "%d %d", &retorno, &ganhou);

			if (values.first){
				SDL_Rect *bombRect = new SDL_Rect();
				bombRect->x = 438 + playX * 35;
				bombRect->y = 83 + playY * 35;
				bombRect->w = 35;
				bombRect->h = 35;
				SDL_Surface* bomb = new SDL_Surface();
				if (retorno == 0){
					loadImageFile(&bomb, "images/x.png");
				}else{
					loadImageFile(&bomb, "images/explosao.png");
				}

				if (ganhou == 1){
					over = true;
					winner = true;
				}
				hisBombsRect.push_back(bombRect);
				hisBombs.push_back(bomb);
			}
			isYourTurn = false;
			didPlay = false;
		}
	}
}

void playingListenningConfig(string port) {
	/*config para listenning*/
	bufsize = 1024;
	buffer = (char*) malloc(bufsize);

	if ((create_socket = socket(AF_INET,SOCK_STREAM,0)) > 0)
	printf("The socket was created\n");
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(atoi(port.c_str()));
	if (bind(create_socket,(struct sockaddr *)&address, sizeof(address)) == 0)
		printf("Binding Socket\n");
	/*config para listenning*/
}