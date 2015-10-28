#include "SDL/SDL.h"
#include <iostream>
#include <string>

using namespace std;

class Color {
	private:
		SDL_Color color;

	public:

		Font(int r, int g, int b, int a){
			updateColor(r, g, b, a);
		}

		updateFont(int r, int g, int b, int a){
			this->color.r = r;
			this->color.g = g;
			this->color.b = b;
			this->color.a = a;
		}

		SDL_Color getColor(){
			return this->color;
		}
}