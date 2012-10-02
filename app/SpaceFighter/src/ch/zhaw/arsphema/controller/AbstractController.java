package ch.zhaw.arsphema.controller;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractController
{
	enum IngameKeys {
		UP, DOWN, BACK, CONFIRM
	}


	static Map<IngameKeys, Boolean> keys = new HashMap<AbstractController.IngameKeys, Boolean>();
	static {
		keys.put(IngameKeys.UP, false);
		keys.put(IngameKeys.DOWN, false);
	};

}
