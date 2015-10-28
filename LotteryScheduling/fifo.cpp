void *coreFifo(void *param);
void sortByTicketProcesses(vector<Processe*> &processes);
vector<Processe*>* getProcessesToExecute(vector<Processe*> processes, int cores);

vector<Processe*> processes;
int currentRound;

int Fifo(vector<Processe*> processesParam, vector<Core*> cores) {

	sortProcesses(processesParam);

	for (int i = 0; i < processesParam.size(); i++){
		cout << "pid: " << processesParam[i]->pid << " init: " << processesParam[i]->init << endl;
	}

	currentRound = 0;

	processes = processesParam;

	vector<Processe*> *aux;

	while(processes.size() > 0) {
		aux = new vector<Processe*>();
		aux = getProcessesToExecute(processes, cores.size());

		for (int i = 0; i < processes.size(); i++){
			bool flag = true;
			for (int j = 0; j < (*aux).size(); j++){
				if (currentRound < processes[i]->init || processes[i]->pid == (*aux)[j]->pid){
					flag = false;
					break;
				}
			}
			if (flag)
				processes[i]->wait++;
		}

		for (int i = 0; i < cores.size() && i < (*aux).size(); i++){
			int auxId = i;
			int *pid = &auxId;
			if (cores[*pid]->isAvailable){
				cores[*pid]->isAvailable = false;
				pthread_create(&cores[*pid]->thread, NULL, coreFifo, (*aux)[*pid]);
			}
		}

		for (int i = 0; i < cores.size(); i++){
			pthread_join(cores[i]->thread, NULL);
			cores[i]->isAvailable = true;
		}
		cout << "termino do ciclo: " << currentRound << endl;
		for (int i = 0; i < processes.size(); i++){
			if (processes[i]->duration == 0){
				cout << "processo: " << processes[i]->pid << " terminou" << " esperou: " << processes[i]->wait << endl;
  				processes.erase(processes.begin()+i);
  				i--;
			}
		}
		currentRound++;
	}
}

void *coreFifo(void *param) {
	Processe *proc = (Processe*) param;
	cout << "iniciou processo: " << proc->pid << " duration: " << proc->duration << endl;
	proc->duration--;
	sleep(1);
}

vector<Processe*>* getProcessesToExecute(vector<Processe*> processes, int cores){
	vector<Processe*> *aux = new vector<Processe*>();
	for (int i = 0; i < cores && i < processes.size(); i++){
		if (currentRound >= processes[i]->init){
			(*aux).push_back(processes[i]);
		}
	}
	return aux;
}