package principal;

public class Level {

	private int actualLevel;

	private float birdVel; //Vel refers to velocity
	private float vomitVel;
	private float monsterVel;
	private float eggVel;

	private long monsterTime;
	
	private static Level level = new Level();
	
	private Level() {
		actualLevel = 1;
		initLevel();
	}
	
	private void initLevel() {
		vomitVel = 2.0f;
		birdVel = 1.5f;
		monsterVel = 0.8f;
		monsterTime = 3000;
		eggVel = 2.0f;
	}
	
	public static Level getLevel(){
		return level;
	}
	
	public void levelUp() {
		actualLevel++;
		monsterVel += monsterVel * 15/100;
		birdVel += birdVel * 15/100;
		vomitVel += vomitVel * 15/100;
	}
	
	public void resetGame() {
		actualLevel = 1;
		initLevel();
	}

	public int getActualLevel() {
		return actualLevel;
	}

	public float getBirdVel() {
		return birdVel;
	}
	public float getVomitVel() {
		return vomitVel;
	}
	public float getEggVel() {
		return eggVel;
	}


	public long getMonsterTime() {
		return monsterTime;
	}
	public float getMonsterVel() {
		return monsterVel;
	}

	
	
	public void chooseLevel(String level) {
		switch(level){
		case "Level 1":
			setLevelVelocities(1);
			actualLevel = 1;
			break;
		case "Level 2":
			setLevelVelocities(2);
			actualLevel = 2;
			break;
		case "Level 3":
			setLevelVelocities(3);
			actualLevel = 3;
			break;
		case "Level 4":
			setLevelVelocities(4);
			actualLevel = 4;
			break;
		case "Level 5":
			setLevelVelocities(5);
			actualLevel = 5;
			break;
		case "Level 6":
			setLevelVelocities(6);
			actualLevel = 6;
			break;
		case "Level 7":
			setLevelVelocities(7);
			actualLevel = 7;
			break;
		case "Level 8":
			setLevelVelocities(8);
			actualLevel = 8;
			break;
		case "Level 9":
			setLevelVelocities(9);
			actualLevel = 9;
			break;		
		case "Level 10":
			setLevelVelocities(10);
			actualLevel = 10;
			break;	
		default:
			break;
			
		}
	}
	
	
	public void setLevelVelocities(int level) {
		initLevel();
		for (int i = 1; i < level; i++) {
			monsterVel += monsterVel * 15/100;
			birdVel += birdVel * 15/100;
			vomitVel += vomitVel * 15/100;
			monsterTime -= monsterTime *15/100;
		}
	}

	
}
