package totally.awesome.ctf;

public abstract class Player {
	
	String name0, name1, name2, name3;
	
	public Player()
	{
		name0 = "Attack!";
	}
	void attack0()
	{
		//Basic Attack
	}
	
	abstract void attack1();
	
	abstract void attack2();
	
	abstract void attack3();
	
	abstract String getName();
	

}
