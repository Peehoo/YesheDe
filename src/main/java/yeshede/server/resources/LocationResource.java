package yeshede.server.resources;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import yeshede.dao.YeshedeDao;
import yeshede.domain.Location;

public class LocationResource extends ServerResource{
	private YeshedeDao dao;
	
	public LocationResource(){
		dao = new YeshedeDao();
	}
	
	@Get
	public JsonRepresentation getLocation() throws ClassNotFoundException, SQLException, JSONException {
		List<Location> locations = dao.getLocations();
		Gson gson = GsonSingleton.getGsonInstance();
		return new JsonRepresentation(gson.toJson(locations));
	}
}
