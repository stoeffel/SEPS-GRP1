package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.screen.Renderer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup extends Rectangle{
	private static final long serialVersionUID = 1L;
	private Array<Vector2> path;
	private int countVector = 0;
	private Vector2 start = new Vector2();
	private Vector2 end = new Vector2();
	private Vector2 position = new Vector2();
	private float distance = 0;
	private float speed;
	
	private Array<AbstractEnemy> members;
	private Vector2 direction;
	
	public EnemyGroup(final float x, final float y, 
			final Array<AbstractEnemy> members, final Array<Vector2> pathP, 
			final boolean adjustPath, final float speed) {
		this.x = x;
		this.y = y;
		position.set(x,y);
		if(adjustPath){
			//copy
			path = new Array<Vector2>();
			for(final Vector2 vector : pathP){
				path.add(new Vector2(vector));
			}
			//adjust
			for(final Vector2 vector : path){
				//adjustment, world height / 2
				vector.y += y - Renderer.WORLD_HEIGHT / 2;
			}
		}
		else {
			this.path = pathP;
		}
		start.set(position);
		end.set(path.get(countVector));
		distance = start.dst(end);
		direction = end.cpy().sub(start);
		this.speed = speed;
		this.members = members;
		
		AbstractEnemy top = null, bottom = null, farRight = null, farLeft = null;
		for(final AbstractEnemy enemy : members){
			if(top == null || top.offsetY + top.height < enemy.offsetY + enemy.height){
				top = enemy;
			}
			if(bottom == null || bottom.offsetY > enemy.offsetY){
				bottom = enemy;
			}
			if(farRight == null || farRight.offsetX + farRight.width < enemy.offsetX + enemy.width){
				farRight = enemy;
			}
			if(farLeft == null || farLeft.offsetX > enemy.offsetX){
				farLeft = enemy;
			}
		}
		height = top.offsetY + top.height - bottom.offsetY;
		width = farRight.offsetX + farRight.width - farLeft.offsetX;
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
		for (final AbstractEnemy member : members) {
			member.x = member.offsetX + x;
			member.y = member.offsetY + y;
			member.move(delta); // enable moving in a group itself
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
