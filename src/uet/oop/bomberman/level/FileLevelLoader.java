package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.ai.Doll;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.StringTokenizer;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		try {
			InputStream fileReader = FileLevelLoader.class.getResourceAsStream("/levels/Level1.txt");
			InputStreamReader inputStreamReader = new InputStreamReader(fileReader);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String a = bufferedReader.readLine();
			StringTokenizer string1 = new StringTokenizer(a);
			_level = Integer.parseInt(string1.nextToken());
			_height = Integer.parseInt(string1.nextToken());
			_width = Integer.parseInt(string1.nextToken());
			_map = new char[_height][_width];
			//a = bufferedReader.readLine();
			for (int i = 0; i < _height; i++) {
				String line = bufferedReader.readLine();
				for (int j = 0; j < _width; j++) {
					_map[i][j] = line.charAt(j);
				}
			}
		}catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch (IOException e)
		{
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		for(int x=0;x<_width;x++)
		{
			for(int y=0;y<_height;y++){
				int pos=x+y*_width;
				switch (_map[y][x]){
					case '#':
						_board.addEntity(pos,new Wall(x,y,Sprite.wall ));
						break;
					case '*':
						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new Brick(x,y,Sprite.brick)));
						break;
					case 'x':
						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new Portal(x,y,Sprite.portal,_board),new Brick(x,y,Sprite.brick)));
						break;
					case 'p':
						_board.addCharacter(new Bomber(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						Screen.setOffset(0,0);
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					case '1':
						_board.addCharacter(new Balloon(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					case '2':
						_board.addCharacter(new Oneal(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos, new Grass(x,y,Sprite.grass));
						break;
					case '3':
						_board.addCharacter(new Doll(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos, new Grass(x,y,Sprite.grass));
						break;
					case 'b':
						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new BombItem(x,y,Sprite.powerup_bombs),new Brick(x,y,Sprite.brick)));
						break;
					case 'f':
						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new FlameItem(x,y,Sprite.powerup_flames),new Brick(x,y,Sprite.brick)));
						break;
					case 's':
						_board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new SpeedItem(x,y,Sprite.powerup_speed),new Brick(x,y,Sprite.brick)));
						break;
					default:
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
				}
			}
		} 
	}

}
