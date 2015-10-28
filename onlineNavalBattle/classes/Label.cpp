#include "Font.h"
#include "Color.h"

#include "SDL/SDL.h"
#include <iostream>
#include <string>

using namespace std;

class Label {
	private:
		SDL_Rect rect;
		SDL_Surface *surface;
		Font font;
		Color color;

	public:

		char *text;
		int positionVet[4];

		Label(positionVet, char *text, Font font, Color color){
			updatePosition(positionVet);
			this->font = font;
			this->color = color;
			this->text = text;
		}

		updatePosition(int positionVet[4]){
			this->rect.x 		= this->x = positionVet[0];
			this->rect.y 		= this->y = positionVet[1];
			this->rect.width 	= this->width = positionVet[2];
			this->rect.height 	= this->height = positionVet[3];
		}

		updateInScreen(SDL_Surface *screen, SDL_Rect rect){
			const char *aux = this->text;
			surface = TTF_RenderText_Blended (font, aux, color);
			SDL_BlitSurface(surface, rect, screen, this->&rect);
		}
}