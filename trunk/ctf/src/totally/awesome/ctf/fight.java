package totally.awesome.ctf;

public class fight {
	int myHealth;
	int enemyHealth;
	int enemyID;
	int myId;
	String enemyName = "Name Not Set";
	String myName;
	int myMaxHealth;
	int enemyMaxHealth;
	
	boolean myTurn;
	
	fight(int eid){
		//myMaxHealth = m;
		//enemyMaxHealth = e;
		enemyID = eid;
		myName = info.theAuth.name;
		myId = info.theAuth.id;
	}
	
	void setMyMaxHealth(int h)
	{
		myMaxHealth = h;
	}
	
	void setEnemyMaxHealth(int h)
	{
		enemyMaxHealth = h;
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
	
	//
}
