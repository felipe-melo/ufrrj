#include <stdio.h>
#include <vector>
#include <cstring>      // Needed for memset
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <arpa/inet.h>
#include <iostream>
#include <sstream>

#include "Constants.cpp"

#include "listenning.cpp"

#include "client.cpp"

using namespace std;

struct Player{
	int grid[10][10];
	int id;
	string ip;
	int largeX;
	int largeY;
	string port;
	int medium1X;
	int medium1Y;
	int medium2X;
	int medium2Y;
	int smallX;
	int smallY;
	int score;
};

struct Watcher{
	int id1;
	int id2;
	string ip;
	string port;
};

struct Game{
	Player *player1;
	Player *player2;
	bool isRunning;
	Watcher *watcher;
};

string newPlayer(char buffer[], vector<Game>*, string);
void movement(char buffer[], int, vector<Game>*);
string watch(char buffer[], vector<Game> *games, string);

string jogada(char buffer[], vector<Game> *games);

int IDS = 0;

int main() {

	vector<Game> games;

	int create_socket, new_socket, op;
	socklen_t addrlen;
	int bufsize = 1024;
	char *buffer = (char*) malloc(bufsize);
	struct sockaddr_in address;
   
	if ((create_socket = socket(AF_INET,SOCK_STREAM,0)) > 0)
	printf("The socket was created\n");
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	int port = atoi(SERVER_PORT);
	address.sin_port = htons(port);
	if (bind(create_socket,(struct sockaddr *)&address, sizeof(address)) == 0)
		printf("Binding Socket\n");

	while(true){
		if (listen(create_socket, 3) == -1){
            cout << "listen error" << endl;
            continue;
		}

		addrlen = sizeof(struct sockaddr_in);
		new_socket = accept(create_socket, (struct sockaddr *)&address, &addrlen);

		char incomming_data_buffer[1000] = {0};
		ssize_t bytes_recieved = recv(new_socket, incomming_data_buffer, 1000, 0);

		//lê código da requisição
		sscanf (incomming_data_buffer, "%d", &op);

		string buffer_return = "";

		//encaminha para função adequada
		switch (op){
			case NEW_PLAYER:
				buffer_return = newPlayer(incomming_data_buffer, &games, inet_ntoa(address.sin_addr));
				break;

			case MOVEMENT:
				buffer_return = jogada(incomming_data_buffer, &games);
				break;

			case WATCH_GAME:
				buffer_return = watch(incomming_data_buffer, &games, inet_ntoa(address.sin_addr));
				break;
		}
		//responde com o retorno da função
		const char *msg = buffer_return.c_str();
		int len;
		ssize_t bytes_sent;
		len = strlen(msg);
		bytes_sent = send(new_socket, msg, len, 0);
	}
	close(new_socket);
	close(create_socket);
	return 0;
}

string jogada(char buffer[], vector<Game> *games){

	int op, x, y, id;

	stringstream buffer_jogada;
	stringstream buffer_jogadaWatch;

	sscanf (buffer, "%d %d %d %d", &op, &id, &y, &x);

	for (int i = 0; i < games->size(); i++){
		if (id == (*games)[i].player1->id && (*games)[i].player2->grid[y][x] == 1){
			
			(*games)[i].player1->score += 1;
			if ((*games)[i].player1->score < 12){
				buffer_jogada << MOV << " " << y << " " << x << " " << "1 0";
			}else{
				buffer_jogada << MOV << " " << y << " " << x << " " << "1 1";
			}
			makeRequest(buffer_jogada.str().c_str(), (*games)[i].player2->ip.c_str(), (*games)[i].player2->port.c_str());
			//envia para quem está assistindo
			if ((*games)[i].watcher != NULL){
				if ((*games)[i].player1->score < 12){
					buffer_jogadaWatch << (*games)[i].player2->id << " " << y << " " << x << " " << "1 0";
				}else{
					buffer_jogadaWatch << (*games)[i].player2->id << " " << y << " " << x << " " << "1 1";
				}
				makeRequest(buffer_jogadaWatch.str().c_str(), (*games)[i].watcher->ip.c_str(), (*games)[i].watcher->port.c_str());
			}
			if ((*games)[i].player1->score == 12){
				return "1 1";
			}
			return "1 0";
		}else if (id == (*games)[i].player1->id && (*games)[i].player2->grid[y][x] == 0){
			buffer_jogada << MOV << " " << y << " " << x << " " << "0 0";
			makeRequest(buffer_jogada.str().c_str(), (*games)[i].player2->ip.c_str(), (*games)[i].player2->port.c_str());
			//envia para quem está assistindo
			if ((*games)[i].watcher != NULL){
				buffer_jogadaWatch << (*games)[i].player2->id << " " << y << " " << x << " " << "0 0";
				makeRequest(buffer_jogadaWatch.str().c_str(), (*games)[i].watcher->ip.c_str(), (*games)[i].watcher->port.c_str());
			}
			return "0 0";
		}

		if (id == (*games)[i].player2->id && (*games)[i].player1->grid[y][x] == 1){
			
			(*games)[i].player2->score += 1;
			if ((*games)[i].player2->score < 12){
				buffer_jogada << MOV << " " << y << " " << x << " " << "1 0";
			}else{
				buffer_jogada << MOV << " " << y << " " << x << " " << "1 1";
			}
			makeRequest(buffer_jogada.str().c_str(), (*games)[i].player1->ip.c_str(), (*games)[i].player1->port.c_str());
			//envia para quem está assistindo
			if ((*games)[i].watcher != NULL){
				if ((*games)[i].player2->score < 12){
					buffer_jogadaWatch << (*games)[i].player1->id << " " << y << " " << x << " " << "1 0";
				}else{
					buffer_jogadaWatch << (*games)[i].player1->id << " " << y << " " << x << " " << "1 1";
				}
				makeRequest(buffer_jogadaWatch.str().c_str(), (*games)[i].watcher->ip.c_str(), (*games)[i].watcher->port.c_str());
			}
			if ((*games)[i].player2->score == 12){
				return "1 1";
			}
			return "1 0";
		}else if (id == (*games)[i].player2->id && (*games)[i].player1->grid[y][x] == 0){
			buffer_jogada << MOV << " " << y << " " << x << " " << "0";
			makeRequest(buffer_jogada.str().c_str(), (*games)[i].player1->ip.c_str(), (*games)[i].player1->port.c_str());
			//envia para quem está assistindo
			if ((*games)[i].watcher != NULL){
				buffer_jogadaWatch << (*games)[i].player1->id << " " << y << " " << x << " " << "0 0";
				makeRequest(buffer_jogadaWatch.str().c_str(), (*games)[i].watcher->ip.c_str(), (*games)[i].watcher->port.c_str());
			}
			return "0 0";
		}
	}
	return "0 0";
}

string newPlayer(char buffer[], vector<Game> *games, string ip){
	Player *p = new Player();
	int op, idAdv = 0;
	char port[4];
	for (int i = 0; i < 10; i++){
		for (int j = 0; j < 10; j++){
			p->grid[i][j] = 0;
		}
	}
	sscanf (buffer, "%d (%d),(%d),(%d),(%d),(%d),(%d),(%d),(%d) %s",
		&op, &(p->largeY), &(p->largeX), &(p->medium1Y), &(p->medium1X), &(p->medium2Y), &(p->medium2X), &(p->smallY), &(p->smallX), port);

	p->grid[p->largeX][p->largeY] = 1;
	p->grid[p->largeX+1][p->largeY] = 1;
	p->grid[p->largeX+2][p->largeY] = 1;
	p->grid[p->largeX+3][p->largeY] = 1;

	p->grid[p->medium1X][p->medium1Y] = 1;
	p->grid[p->medium1X+1][p->medium1Y] = 1;
	p->grid[p->medium1X+2][p->medium1Y] = 1;

	p->grid[p->medium2X][p->medium2Y] = 1;
	p->grid[p->medium2X+1][p->medium2Y] = 1;
	p->grid[p->medium2X+2][p->medium2Y] = 1;

	p->grid[p->smallX][p->smallY] = 1;
	p->grid[p->smallX+1][p->smallY] = 1;

	p->ip = ip;
	p->port = port;
	p->id = ++IDS;
	p->score = 0;

	if ((*games).size() > 0 && (*games).back().player2 == NULL && (*games).back().player1 != NULL){
		(*games).back().player2 = p;
		//manda para o player1 que ele tem um adv
		idAdv = (*games).back().player1->id;
		stringstream buffer_adv;
		buffer_adv << ADV;
		makeRequest(buffer_adv.str().c_str(), (*games).back().player1->ip.c_str(), (*games).back().player1->port.c_str());
		(*games).back().isRunning = true;
	}else{
		Game game;
		game.player1 = p;
		game.player2 = NULL;
		game.watcher = NULL;
		game.isRunning = false;
		(*games).push_back(game);
	}
	stringstream buffer_return;

	buffer_return << p->id << " " << idAdv;

	return buffer_return.str();
}

string watch(char buffer[], vector<Game> *games, string ip){
	//cria watcher
	int op;
	Watcher *w = new Watcher();
	char port[4];
	stringstream buffer_return;

	cout << "-" << buffer << "-" << endl;

	sscanf (buffer, "%d %s", &op, port);

	for (int i = 0; i < (*games).size(); i++){
		if ((*games)[i].watcher == NULL && (*games)[i].isRunning){
			w->id1 = (*games)[i].player1->id;
			w->id2 = (*games)[i].player2->id;
			w->ip = ip;
			w->port = port;
			buffer_return << w->id1 << " " << w->id2;
			(*games)[i].watcher = w;
		}
	}
	return buffer_return.str();
}