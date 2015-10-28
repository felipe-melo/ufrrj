#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <vector>
#include <map>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

using namespace std;

#include "Processes.h"
#include "Core.h"
#include "Ticket.h"

void sortProcesses(vector<Processe*> &processes);

#include "lottery.cpp"
#include "lottery_priority.cpp"
#include "fifo.cpp"

#define TRUE 1
#define FALSE 0

int main(int argc, const char* argv[]){

	char path[10] = "data/";
	int coresQuant = atoi(argv[1]);
	int	 method = atoi(argv[3]);
	int quant;

	std::vector<Processe*> processes;
	vector<Core*> cores;

	strcat(path, argv[2]);

	FILE *input = fopen(path, "r");

	if (input == NULL){
		cout << "Erro ao ler o arquivo\n";
		return 1;
	}
	fscanf(input, "%d\n", &quant);

	Processe *p;
	for (int i = 0; i < quant; i++){
		p = new Processe();
		fscanf(input, "%d", &p->pid);
		fscanf(input, "%d", &p->init);
		fscanf(input, "%d", &p->duration);
		fscanf(input, "%d", &p->priority);
		p->ticket = 0;
		p->wait = 0;
		processes.push_back(p);
	}

	Core *c;
	for (int i = 0; i < coresQuant; i++){
		c = new Core();
		c->isAvailable = true;
		cores.push_back(c);
	}

	/* initialize random seed: */
  	srand (time(NULL));

  	if (method == 0)
  		Fifo(processes, cores);
  	else if(method == 1)
		Lotery(processes, cores);
	else
		Lottery_priority(processes, cores);
	

	return 0;
}

void sortProcesses(vector<Processe*> &processes) {
	Processe *p;
	for (int i = 0; i < processes.size() - 1; i++) {
		for (int j = i+1; j < processes.size(); j++) {
			if (processes[i]->init > processes[j]->init) {
				p = processes[i];
				processes[i] = processes[j];
				processes[j] = p;
			}
		}
	}
}