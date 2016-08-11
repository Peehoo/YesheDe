package yeshede.server.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import yeshede.dao.YeshedeDao;
import yeshede.domain.Book;

public class BookResource extends ServerResource{

	private YeshedeDao dao;
	
	public BookResource() {
		dao = new YeshedeDao();
	}
	
	@Get
	public JsonRepresentation getBook() throws ClassNotFoundException, SQLException, ParseException, JSONException {
//		List<Book> books = dao.getBookResource();
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap = getQuery().getValuesMap();
		
		for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
			valuesMap.put(entry.getKey(), entry.getValue().replaceAll("^\"|\"$", ""));
		}
		
		List<Book> books = dao.getSearchedBookResource(valuesMap);
		
		
		Gson gson = GsonSingleton.getGsonInstance();
		return new JsonRepresentation(gson.toJson(books));
	}
	
	@Post("json")
	public void addBook(Representation request)
			throws ClassNotFoundException, SQLException, IOException, JSONException {
		Gson gson = GsonSingleton.getGsonInstance();
		JsonRepresentation jsonRepresentation = new JsonRepresentation(request);
		String jsonString = jsonRepresentation.getJsonObject().toString();
		Book book = gson.fromJson(jsonString, Book.class);
		dao.insertBookResource(book);
	}
	
//	@Get
//	public Book searchBook() {
//		String p = getQuery().getValues("title");
//		System.out.println(p);
//		return null;
//		
//	}
}
