package exam01;

public class SoldierMain {

	public static void main(String[] args) {
		Gun gun = new Gun("K2", 2);
		Soldier soldier = new Soldier("람보", gun);
		
		soldier.shoot();			// 빵야! 1발 남았다.
		soldier.shoot();			// 빵야! 0발 남았다.
		soldier.shoot();			// 헛빵!
		
		soldier.reload(3);			// 3발이 장전되었다. 현재 3발.
		
		soldier.reload(6);			// 6발이 장전되었다. 현재 6발.
		soldier.reload(6);			// 0발이 장전되었다. 현재 6발.
		
		soldier.info();				// 람보 : K2(6)
	}

}
