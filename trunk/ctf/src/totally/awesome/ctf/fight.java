package totally.awesome.ctf;

public class fight {
	int myHealth;
	int enemyHealth;
	int enemyID;
	
	fight(int m, int e, int eid){
		myHealth = m;
		enemyHealth = e;
		enemyID = eid;
	}
	
	void didWin(boolean won){
		
	}

	void attack(int hp){
		message.sendMessage(Integer.toString(enemyID), "attacked."+Integer.toString(hp));
	}
	
	void attackResult(int hp){
		enemyHealth-=hp;
	}
	
	void gotAttacked(int hp){
		myHealth-=hp;
	}
	
	int getMyHealth(){
		return myHealth;
	}
	
	int getEnemyHealth(){
		return enemyHealth;
	}
}
