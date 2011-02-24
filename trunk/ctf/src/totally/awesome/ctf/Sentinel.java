package totally.awesome.ctf;

public class Sentinel extends Player {
	
	public Sentinel()
	{
		name1 = "Increase Agil";
		name2 = "Sacfifice";
		name3 = "Sneak Attack";
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
		return "Sentinel";
	}
	
	@Override
	int getNumber()
	{
		return 1;
	}

}
