package ch.zhaw.arsphema.controller;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractController
{
	enum IngameKeys {
		LEFT, RIGHT, BOMB, SPRAY, BACK, CONFIRM
	}


	static Map<IngameKeys, Boolean> keys = new HashMap<AbstractController.IngameKeys, Boolean>();
	static {
		keys.put(IngameKeys.LEFT, false);
		keys.put(IngameKeys.RIGHT, false);

		keys.put(IngameKeys.BOMB, false);
		keys.put(IngameKeys.SPRAY, false);
	};

}
