package ch.zhaw.arsphema.util;


public class Sizes {

	// 178x95
	public static float SHIP_WIDTH = 8.9f;
	public static float SHIP_HEIGHT = 4.75f;
	public static float SHIP_COUNTER_WIDTH = 3f;
	public static float SHIP_COUNTER_HEIGHT = 1.6f;
	

	public static float SHOT_WIDTH = 2;
	public static float SHOT_HEIGHT = 1;
	
	public static float DEFAULT_WORLD_WIDTH = 96;
	public static float DEFAULT_WORLD_HEIGHT = 84;
	public static int BUTTON_WIDTH = 25;

	public static float POWER_UP_WITDH = 4;
	public static float POWER_UP_HEIGHT = 4;
	
	//Enemies
	public static float UFO_WIDTH = 3.64f;
	public static float UFO_HEIGHT = 5;
	public static float SAUCER_WIDTH = 4f;
	public static float SAUCER_HEIGHT = 1.84f;
	
	public static float OVERHEATBAR_HEIGHT = 1.5f;
	public static float OVERHEATBAR_WIDTH = 1;
	public static float BLOB_HEIGHT = 10;
	public static float BLOB_WIDTH = 10;	
	

	public static void resize(final float oldPpuX, final float oldPpuY, final float newPpuX, final float newPpuY){
		SHIP_WIDTH /= oldPpuX * newPpuX;
		SHIP_HEIGHT /= oldPpuY * newPpuY;
		SHIP_COUNTER_WIDTH /= oldPpuX * newPpuX;
		SHIP_COUNTER_HEIGHT /= oldPpuY * newPpuY;
		SHOT_WIDTH /= oldPpuX * newPpuX;
		SHOT_HEIGHT /= oldPpuY * newPpuY;
		POWER_UP_WITDH /= oldPpuX * newPpuX;
		POWER_UP_HEIGHT /= oldPpuY * newPpuY;

		// Enemies
		UFO_WIDTH /= oldPpuX * newPpuX;
		UFO_HEIGHT /= oldPpuY * newPpuY;
		SAUCER_WIDTH /= oldPpuX * newPpuX;
		SAUCER_HEIGHT /= oldPpuY * newPpuY;

		OVERHEATBAR_HEIGHT = 1.5f;
		OVERHEATBAR_WIDTH /= oldPpuX * newPpuX;
		BLOB_HEIGHT /= oldPpuY * newPpuY;
		BLOB_WIDTH /= oldPpuX * newPpuX;
	}
	
	public static void setSize(final float newPpuX, final float newPpuY){
		SHIP_WIDTH *= newPpuX;
		SHIP_HEIGHT *= newPpuY;
		SHIP_COUNTER_WIDTH *= newPpuX;
		SHIP_COUNTER_HEIGHT *= newPpuY;
		SHOT_WIDTH *= newPpuX;
		SHOT_HEIGHT *= newPpuY;

		//Enemies
		UFO_WIDTH *= newPpuX;
		UFO_HEIGHT *= newPpuY;
		SAUCER_WIDTH *= newPpuX;
		SAUCER_HEIGHT *= newPpuY;
		
		OVERHEATBAR_HEIGHT *= newPpuY;
		OVERHEATBAR_WIDTH *= newPpuX;
		BLOB_HEIGHT *= newPpuY;
		BLOB_WIDTH *= newPpuX;	
	}	

}
