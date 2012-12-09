package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.util.EnemyTextures;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class EnemyFactory
{
	private static final int POINTS_BLOB = 5;
	private static final int POINTS_UFO = 1;
	private static final int POINTS_UFO_BAD_BOY = 1;
	private static final int POINTS_SAUCER = 2;
	private static final int POINTS_BOITUMELO = 2;
	private static final int POINTS_HIDAI = 10;
	
	private static EnemyFactory instance;
	private Random random = new Random();
	private final int AMOUNT_OF_EASY_GROUPS = 3;
	private final int AMOUNT_OF_MEDIUM_GROUPS = 3;
	private final int AMOUNT_OF_HARD_GROUPS = 2;
    private EnemyFactory(){/*Singleton*/}
    
    public static EnemyFactory getInstance()
    {
    	if(instance == null)
    		instance = new EnemyFactory();
    	return instance;
    }

	public EnemyGroup createGroupByDifficultyLevel(final int nextGroupDiffLevel, final float elapsed) {
		// a group should not be wider than a screen width
		switch (nextGroupDiffLevel)
		{
		case 0:
			return createEasyGroup(elapsed);
		case 1:
			return createMediumGroup(elapsed);
		case 2:
			return createHardGroup(elapsed);
		}
		throw new IllegalStateException();
	}

	private EnemyGroup createEasyGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_EASY_GROUPS);
		switch (nextGroupId){
		case 0:
			return createUfoGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		case 1:
			return createSaucerGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT, 5, EnemyTextures.SAUCER_EASY);
		case 2:
			return createRockGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		}
		throw new IllegalStateException();
	}
	
	private EnemyGroup createMediumGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_MEDIUM_GROUPS);
		switch (nextGroupId){
		case 0:
			return createSaucerGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT, 10, EnemyTextures.SAUCER_MEDIUM);
		case 1:
			return createUfoBadBoysGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		case 2:
			return createBoitumeloGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		}
		throw new IllegalStateException();
	}
	
	private EnemyGroup createHardGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_HARD_GROUPS);
		switch (nextGroupId){
		case 0:
			return createBlobGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		case 1:
			return createHidaiGroup(random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		}
		throw new IllegalStateException();
	}

	public EnemyGroup createUfoGroup(final float y)
	{
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		ufos.add(createUfo(0,Sizes.UFO_HEIGHT));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,0));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,2*Sizes.UFO_HEIGHT));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_WIDTH, y, ufos, 
				EnemyPaths.ZICK_ZACK, false, EnemyPaths.ZICK_ZACK_SPEED);
	}
    
    public AbstractEnemy createUfo(float offsetX, float offsetY)
    {
    	Ufo ufo = new Ufo(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_WIDTH, 
    			Sizes.DEFAULT_WORLD_HEIGHT / 2, offsetX, offsetY, 
    			Sizes.UFO_WIDTH, Sizes.UFO_HEIGHT, EnemyTextures.UFO, POINTS_UFO);
    	return ufo;
    }
	
	public EnemyGroup createRockGroup(final float y)
	{
		Array<AbstractEnemy> rock = new Array<AbstractEnemy>();
		rock.add(new Rock(0, 0, 0, 0, Sizes.ROCK_WIDTH, Sizes.ROCK_HEIGHT, EnemyTextures.ROCK));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.ROCK_WIDTH, y, rock, 
				EnemyPaths.STRAIGHT, false, EnemyPaths.STRAIGHT_SAUCER_SPEED);
	}

	public EnemyGroup createUfoBadBoysGroup(final float y)
	{
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		final float groupY;
		if(y > (Sizes.DEFAULT_WORLD_HEIGHT * 9 / 10) - (3 * Sizes.UFO_BADBOY_HEIGHT))
			groupY = (Sizes.DEFAULT_WORLD_HEIGHT * 9 / 10) - (3 * Sizes.UFO_BADBOY_HEIGHT);
		else if(y < Sizes.UFO_BADBOY_HEIGHT + Sizes.DEFAULT_WORLD_HEIGHT / 10)
			groupY = Sizes.UFO_BADBOY_HEIGHT + Sizes.DEFAULT_WORLD_HEIGHT / 10;
		else
			groupY = y;
		ufos.add(createUfoBadBoy(0, Sizes.UFO_BADBOY_HEIGHT));
		ufos.add(createUfoBadBoy(Sizes.UFO_BADBOY_WIDTH + 2, 0));
		ufos.add(createUfoBadBoy(Sizes.UFO_BADBOY_WIDTH + 2, 2 * Sizes.UFO_BADBOY_HEIGHT));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_BADBOY_WIDTH, groupY, ufos, 
				EnemyPaths.ROUND_PATH, true, EnemyPaths.ROUND_PATH_SPEED);
	}
    
    public AbstractEnemy createUfoBadBoy(float offsetX, float offsetY)
    {
    	UfoBadBoy ufo = new UfoBadBoy(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_BADBOY_WIDTH, 
    			Sizes.DEFAULT_WORLD_HEIGHT / 2, offsetX, offsetY, 
    			Sizes.UFO_BADBOY_WIDTH, Sizes.UFO_BADBOY_HEIGHT, EnemyTextures.UFO_BAD_BOY, POINTS_UFO_BAD_BOY);
    	return ufo;
    }
    
	public EnemyGroup createBoitumeloGroup(final float y)
	{
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		ufos.add(createBoitumelo(0, Sizes.ROCKET_HEIGHT));
		ufos.add(createBoitumelo(Sizes.ROCKET_WIDTH + 2, 0));
		ufos.add(createBoitumelo(Sizes.ROCKET_WIDTH + 2, 2 * Sizes.ROCKET_HEIGHT));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.ROCKET_WIDTH, y, ufos, 
				EnemyPaths.ZICK_ZACK, false, EnemyPaths.ZICK_ZACK_SPEED);
	}

    public AbstractEnemy createBoitumelo(float offsetX, float offsetY)
    {
    	Boitumelo boitumelo = new Boitumelo(Sizes.DEFAULT_WORLD_WIDTH + Sizes.ROCKET_WIDTH, 
    			Sizes.DEFAULT_WORLD_HEIGHT / 2, offsetX, offsetY, 
    			Sizes.ROCKET_WIDTH, Sizes.ROCKET_HEIGHT, EnemyTextures.BOITUMELO, POINTS_BOITUMELO);
    	return boitumelo;
    }

	public EnemyGroup createBlobGroup(final float y)
	{
		Array<AbstractEnemy> blobs = new Array<AbstractEnemy>();
		blobs.add(createBlob(0, 0));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.BLOB_WIDTH, y, blobs, 
				EnemyPaths.ZICK_ZACK, false, EnemyPaths.ZICK_ZACK_SPEED);
	}
    
    public AbstractEnemy createBlob(final float offsetX, final float offsetY)
    {
    	final Blob blob = new Blob(0, 0, offsetX, offsetY, 
    			Sizes.BLOB_WIDTH, Sizes.BLOB_HEIGHT, EnemyTextures.BLOB, POINTS_BLOB);
    	return blob;
    }

	public EnemyGroup createSaucerGroup(final float y, final int amountOfEnemies, final TextureRegion texture)
	{
		final Array<AbstractEnemy> saucers = new Array<AbstractEnemy>();
		final float groupHeight = amountOfEnemies * Sizes.SAUCER_HEIGHT;
		
		final float groupY;
		if(y < groupHeight)
			groupY = y + groupHeight;
		else if (y > Sizes.DEFAULT_WORLD_HEIGHT - groupHeight)
			groupY = Sizes.DEFAULT_WORLD_HEIGHT - groupHeight;
		else
			groupY = y;
		
		for(int i = 0; i < amountOfEnemies; i++){
			saucers.add(createSaucer(i * Sizes.SAUCER_WIDTH * 0.5f, 
					random.nextInt((int)(groupHeight - (2 * Sizes.SAUCER_HEIGHT))) + Sizes.SAUCER_HEIGHT, 
					groupHeight, i % 2, texture));
		}
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.SAUCER_WIDTH, groupY, saucers, 
				EnemyPaths.STRAIGHT, true, EnemyPaths.STRAIGHT_SAUCER_SPEED, groupHeight, (amountOfEnemies + 1) * Sizes.SAUCER_WIDTH * 0.5f);
	}
	
    public AbstractEnemy createSaucer(final float offsetX, final float offsetY, final float height, final int direction,
    		final TextureRegion texture)
    {
    	final Saucer saucer = new Saucer(0, 0, offsetX, offsetY, Sizes.SAUCER_WIDTH, Sizes.SAUCER_HEIGHT, 
    			texture, POINTS_SAUCER, height, direction);
    	return saucer;
    }
    
	public EnemyGroup createHidaiGroup(final float y)
	{
		Array<AbstractEnemy> hidaiArray = new Array<AbstractEnemy>();

		final Hidai hidai = new Hidai(0, 0, 0, 0, Sizes.HIDAI_WIDTH, Sizes.HIDAI_HEIGHT, EnemyTextures.HIDAI, POINTS_HIDAI);
		hidaiArray.add(hidai);
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.BLOB_WIDTH, y, hidaiArray, 
				EnemyPaths.LURKING, false, EnemyPaths.LURKING_SPEED);
	}
}