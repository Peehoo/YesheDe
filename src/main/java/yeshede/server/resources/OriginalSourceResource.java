package yeshede.server.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import yeshede.dao.YeshedeDao;
import yeshede.domain.OriginalSource;

public class OriginalSourceResource extends ServerResource {

	private YeshedeDao dao;

	public OriginalSourceResource() {
		dao = new YeshedeDao();
	}

	@Get
	public JsonRepresentation getOriginalSource() throws ClassNotFoundException, SQLException, JSONException {
		List<OriginalSource> originalSources = dao.getOriginalResources();
		Gson gson = GsonSingleton.getGsonInstance();
		return new JsonRepresentation(gson.toJson(originalSources));
	}

	@Post("json")
	public void addOriginalSource(Representation request)
			throws ClassNotFoundException, SQLException, IOException, JSONException {
		JsonRepresentation jsonRepresentation = new JsonRepresentation(request);
		String jsonString = jsonRepresentation.getJsonObject().toString();
		Gson gson = GsonSingleton.getGsonInstance();
		OriginalSource originalSource = gson.fromJson(jsonString, OriginalSource.class);
		dao.insertOriginalSource(originalSource);
	}
}
