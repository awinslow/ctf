package totally.awesome.ctf;

public class Sentinel extends Player {
	
	public Sentinel()
	{
		name1 = "Increase Agil (4AP)";
		name2 = "Sacrifice (6AP)";
		name3 = "Sneak Attack (8AP)";
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
