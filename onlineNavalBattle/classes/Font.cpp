#include "SDL/SDL.h"
#include "SDL/SDL_ttf.h"

#include <iostream>
#include <string>

using namespace std;

class Font {
	private:
		TTF_Font *font;
		char *path;
		int size;

	public:

		Font(char *path, int size){
			this->path = path;
			this->size = size;
			const char *aux = path;
			updateFont(aux, this->size);
		}

		updateFont(const char *path, int size){
			font = TTF_OpenFont(path, size);
		}
}