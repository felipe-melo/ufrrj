#include <stdio.h>
#include <stdlib.h>

struct Core {
    bool isAvailable;
    pthread_t thread;
    int pid;
};
