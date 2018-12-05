package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi

		if(_bomber.getXTile()==_e.getXTile()){
			if(_bomber.getYTile()<_e.getYTile())
				return 0;
			else return 2;
		}
		if(_bomber.getYTile()== _e.getYTile()){
			if(_bomber.getXTile()>_e.getXTile()){
				return 1;
			}
			else return 3;
		}
		return random.nextInt(4);
	}

}
