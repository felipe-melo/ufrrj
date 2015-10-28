#include "Label.h"
#include "Image.h"
#include "Font.h"
#include "Color.h"

#include "SDL/SDL.h"
#include <iostream>
#include <string>

using namespace std;

class Menu {
	private:
		Label options[];
		Image selector;
		int space, quantOptions;
		Color _default, selected;
		Font font;
		int selectedIndex, selectorY;
		char* menuOptionsText[];

	public:
		Menu(int positionVet[4], int quantOptions, int space, char *menuOptionsText[quantOptions], Font font, Color selected, 
			Color defaultColor, Image selector){

			this->quantOptions = quantOptions;
			this->options = new Label[quantOptions];
			this->font = font;
			this->selected = selected;
			this->_default = defaultColor;
			this->space = space;
			this->selector = selector;
			this->selectorY = selector.rect.y;
			menuSelected = 0;
			for (int i = 0; i < quantOptions; i++){
				positionVet[1] += i * space;
				this->options[i] = new Label(positionVet, menuOptionsText[i], font, (i == selectedIndex ? this->selector : this->_default));
			}
		}

		updatePosition(int positionVet[4]){
			this->rect.x 		= this->x = positionVet[0];
			this->rect.y 		= this->y = positionVet[1];
			this->rect.width 	= this->width = positionVet[2];
			this->rect.height 	= this->height = positionVet[3];
		}

		updateInScreen(SDL_Surface *screen, SDL_Rect rect){
			for (int i = 0; i < quantOptions; i++){
				options[i].updateInScreen(screen, rect);
			}
			selector.rect.y = this->selectorY + (menuSelected * 20);
			selector.updateInScreen(screen, rect);
		}

		setMenuUp(){
			int index = this->selectedIndex-1;
			this->selectedIndex = (index < 0 ? quantOptions - 1 : index-1) % quantOptions;
		}

		setMenuDown(){
			int index = this->selectedIndex+1;
			this->selectedIndex = (index) % quantOptions;
		}
}