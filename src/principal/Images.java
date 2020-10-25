package principal;

import principal.graphics.Animation;
import principal.graphics.Sprite;
import principal.util.ResourceLoader;

public class Images {


	private static String character= "lama";

	// RALPH'S ANIMATIONS
	private final String[] monsterDemolishing = {
		"images/ralph/Demolishing/0.png",	
		"images/ralph/Demolishing/1.png",

	};

	private final String[] monsterClimbingPath = {
		"images/ralph/Climbing/0.png",
		"images/ralph/Climbing/1.png",
	};

	
	private final String[] monsterMovePath = {
		"images/ralph/Moving/0.png",
		"images/ralph/Moving/1.png",
	};
	
	// OBJECTS PATHS
	private final String[] birdLeftPaths = {
		"images/entities/bird/birdLeft/0.png",
		"images/entities/bird/birdLeft/1.png"
	};

	
	private final String[] birdRightPaths = {
		"images/entities/bird/birdRight/0.png",
		"images/entities/bird/birdRight/1.png"
	};
	
	
	private final String[] brickPaths = {
		"images/entities/brick/0.png",
		"images/entities/brick/1.png"
	};

	private final String[] eggPaths= {
			"images/entities/egg/0.png",
	};

	//	Ralph Animations
	private final Animation monsterClimbing;
	public final Animation monsterDemolition;
	public final Animation monsterMove;
	
	
	// Felix Animations
	public final Animation playerMoveLeft;
	private final Animation playerMoveRight;

	private final Animation playerNormalRight;
	private final Animation platerNormalLeft;

	private final Animation playerCleaningRight;
	private final Animation playerCleaningLeft;

	private final Animation playerFalling;

	private final Animation playerWin;
	
	// OBJECTS
	private final Sprite flowerPot;
	private final Sprite roof;
	private final Sprite twoPanels; 
	private final Animation brick;
	private final Animation birdLeft;
	private final Animation birdRight;
	private final Sprite life;
	private final Sprite bush;
	private final Sprite building;
	private final Sprite menu;
	private final Sprite config;
	private final Sprite buildingRoof;

	private final Sprite bgShop;
	private final Sprite block;
	private final Sprite icon;
	private final Sprite bgScore;

	private final Animation egg;
	
	// WINDOWS & PARTS
	private Sprite[] glasses; 
	private Sprite[] doubleDoor;
	private Sprite[] semicircular;
	private Sprite[] door;
	private Sprite[] shopchar;
	
	public Images(String character) {
		
		// Monster's Animations
		monsterDemolition = new Animation (monsterDemolishing);
		monsterClimbing = new Animation (monsterClimbingPath);
		monsterMove = new Animation(monsterMovePath);
		
		// Player's Animations
		this.character = character;
		playerMoveLeft = new Animation(playerMovingLeftPaths);
		playerMoveRight = new Animation (playerMovingRightPaths);
		playerNormalRight = new Animation(playerNormalRightPaths);
		platerNormalLeft = new Animation(playerNormalLeftPaths);
		playerCleaningLeft = new Animation(playerCleaningLeftPaths);
		playerCleaningRight = new Animation(playerCleaningRightPaths);
		playerFalling = new Animation(playerFallingPaths);
		playerWin = new Animation(playerWinPaths);
		
		// OBJECTS
		flowerPot =  new Sprite(ResourceLoader.getLoader().
				loadImage("images/window/obstacles/flowerpot.png"));
		
		roof =  new Sprite(ResourceLoader.getLoader().
				loadImage("images/window/obstacles/roof.png"));
		
		twoPanels = new Sprite(ResourceLoader.getLoader().
				loadImage("images/window/0.png"));
		
		life = new Sprite(ResourceLoader.getLoader().
				loadImage("images/life.png"));
		
		bush = new Sprite(ResourceLoader.getLoader().
				loadImage("images/bush.png"));
		
		buildingRoof = new Sprite(ResourceLoader.getLoader().
				loadImage("images/sprites_sin_fondo.png"));

		building = new Sprite(ResourceLoader.getLoader().loadImage("images/building/0.png"));
		brick = new Animation(brickPaths);


		egg = new Animation(eggPaths);
		birdLeft = new Animation(birdLeftPaths);
		birdRight = new Animation(birdRightPaths);
		
		menu = new Sprite (ResourceLoader.getLoader().loadImage("images/initial_menu.png"));
		config = new Sprite(ResourceLoader.getLoader().loadImage("images/config.png"));

		bgShop = new Sprite(ResourceLoader.getLoader().loadImage("images/bg/bgShop.jpg"));
		block = new Sprite(ResourceLoader.getLoader().loadImage("images/bg/block.png"));
		icon = new Sprite(ResourceLoader.getLoader().loadImage("images/icon.png"));
		bgScore = new Sprite(ResourceLoader.getLoader().loadImage("images/bg/bgScore.jpg"));

		// WINDOWS & PARTS
		initGlasses();
		initDoubleDoor();
		initSemicircular();
		initDoor();
		initshopchar();
	}

	private final String[] playerMovingLeftPaths = {
			"images/character/"+character+"/moving/movingLeft/0.png",
			"images/character/"+character+"/moving/movingLeft/1.png",
			"images/character/"+character+"/moving/movingLeft/2.png",
			"images/character/"+character+"/moving/movingLeft/3.png",
	};


	private final String[] playerMovingRightPaths = {
			"images/character/"+character+"/moving/movingRight/0.png",
			"images/character/"+character+"/moving/movingRight/1.png",
			"images/character/"+character+"/moving/movingRight/2.png",
			"images/character/"+character+"/moving/movingRight/3.png",
	};


	private final String[] playerCleaningLeftPaths = {
			"images/character/"+character+"/fixing/fixingLeft/0.png",
			"images/character/"+character+"/fixing/fixingLeft/1.png",
	};


	private String[] playerCleaningRightPaths = {
			"images/character/"+character+"/fixing/fixingRight/0.png",
			"images/character/"+character+"/fixing/fixingRight/1.png",
	};


	private String[] playerNormalRightPaths = {
			"images/character/"+character+"/normal/normalRight/0.png",
	};


	private String[] playerNormalLeftPaths = {
			"images/character/"+character+"/normal/normalLeft/0.png",
	};


	private String[] playerFallingPaths = {
			"images/character/"+character+"/falling/0.png",
			"images/character/"+character+"/falling/1.png",
	};

	private String[] playerWinPaths = {
			"images/character/"+character+"/win/0.png",
			"images/character/"+character+"/win/1.png",
			"images/character/"+character+"/win/2.png",
			"images/character/"+character+"/win/3.png",
			"images/character/"+character+"/win/4.png",
			"images/character/"+character+"/win/5.png"
	};
	
	private void initGlasses() {
		glasses = new Sprite[7];
		for (int i = 0; i < glasses.length; i++) {
			glasses[i] = new Sprite(ResourceLoader.getLoader().loadImage("images/window/glasses/"+i+".png"));
		}
	}

	private void initDoubleDoor() {
		doubleDoor = new Sprite[4];
		for (int i = 0; i < doubleDoor.length; i++){
			doubleDoor[i] =  new Sprite(ResourceLoader.getLoader().loadImage("images/window/doubledoor/"+i+".png"));
		}
	}
	
	private void initSemicircular() {
		semicircular = new Sprite[5];
		for (int i = 0; i < semicircular.length; i++){
			semicircular[i] = new Sprite(ResourceLoader.getLoader().loadImage("images/window/semicircular/bigwindow/"+ i+".png"));
		}
	}
	
	private void initDoor() {
		door = new Sprite[5];
		for (int i = 0; i < door.length; i++) {
			door[i] = new Sprite(ResourceLoader.getLoader().loadImage("images/window/semicircular/door/"+ i+".png"));
		}
	}

	private void initshopchar(){
		shopchar = new Sprite[15];
		for (int i=0;i<shopchar.length;i++){
			shopchar[i] = new Sprite(ResourceLoader.getLoader().loadImage("images/shop/"+i+".png"));
		}

	}

	public Animation getBrick() {
		return brick;
	}

	public Animation getEgg() {
		return egg;
	}
	
	
	// RALPH'S ANIMATION
	public Animation getClimbing() {
		return monsterClimbing;
	}
	
	public Animation getMonsterDemolition(){
		return monsterDemolition;
	}
	
	public Animation getMonsterMove() {
		return monsterMove;
	}
	

	
	// FELIX'S ANIMATION
	public Animation getPlayerMoveLeft(){
		return playerMoveLeft;
	}
	
	public Animation getPlayerMoveRight(){
		return playerMoveRight;
	}
	
	public Animation getPlaterNormalLeft(){
		return platerNormalLeft;
	}
	
	public Animation getPlayerNormalRight(){
		return playerNormalRight;
	}
	
	public Animation getPlayerCleaningLeft(){
		return playerCleaningLeft;
	}
	
	public Animation getPlayerCleaningRight(){
		return playerCleaningRight;
	}
	
	
	public Animation getPlayerFalling(){
		return playerFalling;
	}
	

	
	
	// OBJECTS
	public Sprite getBuildingRoof() {
		return buildingRoof;
	}
	
	
	public Sprite getFlowerPot() {
		return flowerPot;
	}
	
	
	public Sprite getRoof() {
		return roof;
	}
	
	
	public Animation getLeftBird(){
		return birdLeft;
	}
	
	
	public Animation getRightBird(){
		return birdRight;
	}


	public Sprite getTwoPanels() {
		return twoPanels;
	}
	
	
	public Sprite getBush() {
		return bush;
	}

	
	public Sprite getBuilding() {
		return building;
	}



	public Sprite getBgShop(){return bgShop; }

	public Sprite getBlock() {return  block; }

	public Sprite getIcon(){return icon;}

	public Sprite getBgScore() {return bgScore;}


	public Sprite getLife() {
		return life;
	}

	
	public Sprite getMenu(){
		return menu;
	}
	
	public Sprite getConfig(){
		return config;
	}
	
	// WINDOWS & PARTS
	public Sprite getGlass(int i){
		return glasses[i];
	}

	
	public Sprite[] getDoubleDoor(){
		return doubleDoor;
	}
	
	
	public Sprite[] getSemicircular(){
		return semicircular;
	}


	public Sprite[] getDoor(){
		return door;
	}

	public Sprite[] getShopchar(){
		return shopchar;
	}

	public Animation getPlayerWin(){
		return playerWin;
	}

	public Animation getBirdMove() {
		return birdLeft;
	}

	
}
