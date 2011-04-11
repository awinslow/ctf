package totally.awesome.ctf;

import java.io.IOException;

import android.media.MediaPlayer;

public class Soldier extends Player {

	public Soldier() {
		// TODO Auto-generated constructor stub
		name1 = "Increase Attack (3AP)";
		name2 = "Increase Defense (3AP)";
		name3 = "Attack X2 (8AP)";
	}

	@Override
	boolean attack1(int type) {
		return super.attack1(getNumber());
	}
	
	void soundAttack1(){
		MediaPlayer mp = MediaPlayer.create(info.battleInst, R.raw.powerup);
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
	void soundAttack2(){
		MediaPlayer mp = MediaPlayer.create(info.battleInst, R.raw.powerup);
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
	void soundAttack3(){
		MediaPlayer mp = MediaPlayer.create(info.battleInst, R.raw.lasermachinegun);
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
		return "Soldier";
	}
	
	@Override
	int getNumber()
	{
		return 0;
	}

}
