package totally.awesome.ctf;

public class Hacker extends Player {
	
	public Hacker()
	{
		name1 = "Steal AP";
		name2 = "Steal Health";
		name3 = "Disable Move";
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
		return "Hacker";
	}
	
	@Override
	int getNumber()
	{
		return 2;
	}
}
