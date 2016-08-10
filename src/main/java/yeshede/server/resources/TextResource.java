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
import yeshede.domain.Text;

public class TextResource extends ServerResource{
	private YeshedeDao dao;
	
	public TextResource(){
		dao = new YeshedeDao();
	}
	
	@Get
	public JsonRepresentation getTexts() throws ClassNotFoundException, SQLException {
		List<Text> texts = dao.getTextResources();
		Gson gson = GsonSingleton.getGsonInstance();
		return new JsonRepresentation(gson.toJson(texts));
	}
	
	@Post
	public void addText(Representation request)
			throws IOException, JSONException, ClassNotFoundException, SQLException{
		JsonRepresentation jsonRepresentation = new JsonRepresentation(request);
		String jsonString = jsonRepresentation.getJsonObject().toString();
		Gson gson = GsonSingleton.getGsonInstance();
		Text text = gson.fromJson(jsonString, Text.class);
		dao.insertTextResource(text);
	}
	
}
