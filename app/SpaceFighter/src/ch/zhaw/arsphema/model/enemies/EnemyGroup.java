package ch.zhaw.arsphema.model.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup{
	private Array<Vector2> path = EnemyPaths.ZICK_ZACK;
	private int countVector = 0;
	private Vector2 start = new Vector2();
	private Vector2 end = new Vector2();
	private Vector2 position = new Vector2();
	private float distance = 0;
	private float speed;
	private float x,y;
	
	private Array<AbstractEnemy> members;
	private Vector2 direction;
	
	public EnemyGroup(float x, float y,Array<AbstractEnemy> members) {
		position.set(x,y);
		start.set(position);
		end.set(path.get(countVector));
		distance = start.dst(end);
		direction = end.cpy().sub(start);
		speed = 0.5f;
		this.members = members;
		this.x = x;
		this.y = y;
	}
	
	public boolean move(float delta){
		position.add(direction.cpy().mul(delta*speed));
		x = position.x;
		y = position.y;
		
		if (start.dst(position) >= distance && countVector < path.size-1) {
			position.set(end.x,end.y);
			start.set(position);
			countVector++;
			end.set(path.get(countVector));
			distance = start.dst(end);
			direction = end.cpy().sub(start);
		} else if (start.dst(position) >= distance) {
			return true;
		}
		for (AbstractEnemy member : members) {
			member.x = member.offset_x + x;
			member.y = member.offset_y + y;
		}
		
		return false;
	}

	public Array<AbstractEnemy> getMembers() {
		return members;
	}

	public void setMembers(Array<AbstractEnemy> members) {
		this.members = members;
	}

}
