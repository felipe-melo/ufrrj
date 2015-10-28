#include "SDL/SDL.h"
#include "SDL/SDL_image.h"

#include <iostream>
#include <string>

using namespace std;

class Image {
	private:
		SDL_Surface *surface;

	public:

		char *path;
		int positionVet[4];
		SDL_Rect rect;

		Label(int positionVet[4], char *path){
			updatePosition(positionVet);
			const char *aux = path
			this->loadImageFile(aux);
		}

		updatePosition(int positionVet[4]){
			this->rect.x 		= positionVet[0];
			this->rect.y 		= positionVet[1];
			this->rect.width 	= positionVet[2];
			this->rect.height 	= positionVet[3];
		}

		updateInScreen(SDL_Surface *screen, SDL_Rect rect){
			SDL_BlitSurface(surface, rect, screen, this->&rect);
		}

		void loadImageFile(const char* path){
			surface = IMG_Load(path);
		}
}