package totally.awesome.ctf;

import java.io.IOException;

import android.media.MediaPlayer;

public class Hacker extends Player {
	
	public Hacker()
	{
		name1 = "Steal AP (5AP)";
		name2 = "Steal Health (6AP)";
		name3 = "Reflect (12AP)";
	}

	@Override
	boolean attack1(int type) {
		return super.attack1(getNumber());
	}
	
	void soundAttack1(){
		
	}
	void soundAttack2(){
		
	}
	void soundAttack3(){
		MediaPlayer mp = MediaPlayer.create(info.battleInst, R.raw.hacking);
		try {
			mp.prepare();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        mp.start();
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
