void *coreLottery(void *param);
vector<Processe*> *raffle(int currentRound, vector<Processe*> processes);
void sortByTicketProcesses(vector<Processe*> &processes);

int Lotery(vector<Processe*> processes, vector<Core*> cores) {

	int currentRound = 0;
	vector<Processe*> *aux;
	sortProcesses(processes);

	while(processes.size() > 0){

		aux = new vector<Processe*>();
		aux = raffle(currentRound, processes);
		sortByTicketProcesses(*aux);

		//Analisa os processos que não foram escolhidos e soma tempo de espera
		for (int i = 0; i < processes.size(); i++) {
			bool flag = true;
			for (int j = 0; j < (*aux).size(); j++) {
				if (currentRound < processes[i]->init || processes[i]->pid == (*aux)[j]->pid){
					flag = false;
					break;
				}
			}
			if (flag){
				processes[i]->wait++;
			}
		}

		for (int i = 0; i < cores.size() && i < (*aux).size(); i++) {
			int auxId = i;
			int *pid = &auxId;
			if (cores[*pid]->isAvailable){
				cores[*pid]->isAvailable = false;
				cout << currentRound << " " << *pid << " " << (*aux)[*pid]->pid << " " << (*aux)[*pid]->duration << endl;
				pthread_create(&cores[*pid]->thread, NULL, coreLottery, (*aux)[*pid]);
			}
		}
		for (int i = 0; i < cores.size(); i++){
			pthread_join(cores[i]->thread, NULL);
			cores[i]->isAvailable = true;
		}

		for (int i = 0; i < processes.size(); i++){
			if (processes[i]->duration == 0){
				//cout << processes[i]->wait << endl;
  				processes.erase(processes.begin()+i);
  				i--;
			}
		}
		currentRound++;

	}
}

void *coreLottery(void *param) {
	Processe *proc = (Processe*) param;
	proc->duration--;
	proc->ticket -= rand() % 10 + 1;
	sleep(1);
}

void sortByTicketProcesses(vector<Processe*> &processes) {
	Processe *p;
	for (int i = 0; i < processes.size() - 1; i++) {
		for (int j = i+1; j < processes.size(); j++) {
			if (processes[i]->ticket < processes[j]->ticket) {
				p = processes[i];
				processes[i] = processes[j];
				processes[j] = p;
			}
		}
	}
}

vector<Processe*> *raffle(int currentRound, vector<Processe*> processes) {
	vector<Processe*> *aux = new vector<Processe*>();
	for (int i = 0; i < processes.size(); i++){
		if (currentRound >= processes[i]->init){
			processes[i]->ticket += rand() % 10 + 1;
			(*aux).push_back(processes[i]);
		}else{
			return aux;
		}
	}
	return aux;
}