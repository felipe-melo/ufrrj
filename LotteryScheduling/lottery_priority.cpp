void *coreLotteryPriority(void *param);
void raffle_priority(int currentRound, vector<Processe*> processes);
bool isInRun(vector<Core*> cores, int pid);
int drawATicketInLottery(vector<Core*> cores);

map <int, Processe*> lottery; //Global mesmo hahahahahaha Se quiser mudar pra interno, fique a vontade... EEE Ve se é asism que declara
vector<int> lotteryBase;
int numOfProcessesInLoteryBase = 0;
int Lottery_priority(vector<Processe*> processes, vector<Core*> cores) {

	int currentRound = 0;
	vector<Processe*> aux;
	sortProcesses(processes);

	while(processes.size() > 0){
		cout << "\n" << endl;
		raffle_priority(currentRound, processes); //Essa função esta distribuindo os tickets agora.. Talvez mude nome

		if(lotteryBase.size() <= 0 )
		{
			currentRound++;
			continue;
		}

		for (int i = 0; i < processes.size(); i++){
			bool flag = true;
			for (int j = 0; j < aux.size(); j++){
				if (currentRound < processes[i]->init || processes[i]->pid == aux[j]->pid){
					flag = false;
					break;
				}
			}
			if (flag)
				processes[i]->wait++;
		}


		for (int i = 0; i < cores.size() && i < numOfProcessesInLoteryBase; i++){ // Se tiver ticket na loteria...
			int auxId = i;
			int *pid = &auxId;

			if (cores[*pid]->isAvailable){
				int ticket = drawATicketInLottery(cores); // Sorteio um ticket na Loteria (Na vdd, só um indice no vetor)
				int *t = &ticket;
				cores[*pid]->isAvailable = false;
				cores[*pid]->pid = ticket;
				pthread_create(&cores[*pid]->thread, NULL, coreLotteryPriority, lottery[*t]); //Passo o processo sorteado -> n sei o q essa funcao faz .-.
			}
		}

		for (int i = 0; i < cores.size(); i++){ // Não sei o que esse for esta fazendo hahahaha
			pthread_join(cores[i]->thread, NULL);
			cores[i]->isAvailable = true;
			cores[i]->pid = 0;
		}

		cout << "termino:" << currentRound << endl;
		for (int i = 0; i < processes.size(); i++){
			if (processes[i]->duration == 0){
				cout << "terminou:" << processes[i]->pid << endl;
  				processes.erase(processes.begin()+i);
  				numOfProcessesInLoteryBase--;
  				i--;
			}
		}
		currentRound++;

	}
}


void *coreLotteryPriority(void *param) {
	Processe *proc = (Processe*) param;
	proc->duration--;
	sleep(1);
}

void raffle_priority(int currentRound, vector<Processe*> processes) {
	for (int i = 0; i < processes.size(); i++) {
		if (currentRound == processes[i]->init) {
			for (int j = 1; j <= processes[i]->priority ; j++) { //Distribuo a quantidade de tickets de sua prioridade
				lotteryBase.push_back(processes[i]->pid);
			}
			numOfProcessesInLoteryBase++;
			lottery[processes[i]->pid] = processes[i]; // Adiciono o processo na loteria
			cout << "Novo:" << processes[i]->pid << ":" << processes[i]->priority << ":"<<processes[i]->duration <<endl; 
		}
	}
}

int drawATicketInLottery(vector<Core*> cores) 
{
	int ticket;
	int pid;
	while(lotteryBase.size() > 0){
		ticket = rand() % lotteryBase.size(); //Sorteio um indice na loteria
		pid = lotteryBase[ticket];
		if(lottery[pid]->duration <= 0){ // Se o processo ja terminou, faço novo sorteio	
			lotteryBase.erase(lotteryBase.begin() + ticket);
			continue;
		}else{
			if(isInRun(cores, pid))
				continue;
			break;
		}
		
	}
	cout << "Sorted:"<<pid << ":" << lottery[pid]->duration <<endl ;
	return pid;
}

bool isInRun(vector<Core*> cores, int pid)
{
	for (int i = 0; i < cores.size(); ++i)
	{
		if(cores[i]->pid == pid)
			return true;
	}	
	return false;
}

