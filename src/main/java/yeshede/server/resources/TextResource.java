package yeshede.server.resources;

import java.util.List;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import yeshede.dao.YeshedeDao;
import yeshede.domain.Text;

public class TextResource extends ServerResource{
	private YeshedeDao dao;
	
	public TextResource(){
		dao = new YeshedeDao();
	}
	
	public JsonRepresentation getTexts() {
		List<Text> texts = dao.getTextResources();
		return new JsonRepresentation(new Gson().toJson(texts));
	}
}
