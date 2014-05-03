package optativa;

public class Cavalo extends Peca{

	@Override
	public void movimenta(int x, int y) {
		// TODO Auto-generated method stub
		if (x+2 == this.x){
			if (y+1 == this.y || y-1 == this.y){
				this.x = x;
				this.y = y;
				return;
			}
		}
		
		if (x+1 == this.x){
			if (y+2 == this.y || y-2 == this.y){
				this.x = x;
				this.y = y;
				return;
			}
		}
		
		if (x-1 == this.x){
			if (y+2 == this.y || y-2 == this.y){
				this.x = x;
				this.y = y;
				return;
			}
		}
		
		if (x-2 == this.x){
			if (y+1 == this.y || y-1 == this.y){
				this.x = x;
				this.y = y;
				return;
			}
		}
	}

}
