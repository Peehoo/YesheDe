package yeshede.server.resources;

import com.google.gson.Gson;

public class GsonSingleton {
	private static Gson gsonInstance;
	
	public static Gson getGsonInstance(){
		if (gsonInstance == null) {
			gsonInstance = new Gson();
		}
		return gsonInstance;
	}
}
