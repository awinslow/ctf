package totally.awesome.ctf;

public class Soldier extends Player {

	public Soldier() {
		// TODO Auto-generated constructor stub
		name1 = "Increase Attack";
		name2 = "Increase Defense";
		name3 = "Attack X2";
	}

	@Override
	boolean attack1(int type) {
		return super.attack1(getNumber());
	}

	@Override
	boolean attack2(int type) {
		// TODO Auto-generated method stub
		return super.attack2(getNumber());
	}

	@Override
	boolean attack3(int type) {
		// TODO Auto-generated method stub
		return super.attack3(getNumber());
	}
	
	@Override
	String getName()
	{
		return "Soldier";
	}
	
	@Override
	int getNumber()
	{
		return 0;
	}

}