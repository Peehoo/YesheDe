package yeshede.server.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

public class GsonSingleton {
	private static Gson gsonInstance;
	
	public static Gson getGsonInstance(){
		if (gsonInstance == null) {
			SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
			gsonInstance = new GsonBuilder()
			   .registerTypeAdapter(java.util.Date.class, sqlAdapter )
			   .setDateFormat("yyyy-MM-dd")
			   .create();
		}
		return gsonInstance;
	}
}
