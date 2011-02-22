package totally.awesome.ctf;

public class Hacker extends Player {
	
	public Hacker()
	{
		name1 = "Hack!";
		name2 = "Kill!";
		name3 = "Da Adam Buttun!";
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
