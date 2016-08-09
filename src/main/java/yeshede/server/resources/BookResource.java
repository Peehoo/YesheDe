package yeshede.server.resources;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		List<Book> books = dao.getBookResource();
		Map<String, String> valueMap = getQuery().getValuesMap();
//		int id = Integer.parseInt(valueMap.get("id"));
//		int predecessorId = Integer.parseInt(valueMap.get("predecessor"));
//		int successorId = Integer.parseInt(valueMap.get("successor"));
//		int numCopiesPrinted = Integer.parseInt(valueMap.get("successor"));
//		int pressPlateLocationId = Integer.parseInt(valueMap.get("pressPlateLocationId"));
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        java.util.Date parsed = format.parse(valueMap.get("productionYear"));
//        Date productionYear = new Date(parsed.getTime());
//		
//		String title = valueMap.get("title");
//		String notes = valueMap.get("notes");
		JsonRepresentation a = new JsonRepresentation(new Gson().toJson(books));
		System.out.println(a.getJsonObject().toString());
		return new JsonRepresentation(new Gson().toJson(books));
	}
	
	@Post("json")
	public void addBook(Representation request)
			throws ClassNotFoundException, SQLException, IOException, JSONException {
		JsonRepresentation jsonRepresentation = new JsonRepresentation(request);
		String jsonString = jsonRepresentation.getJsonObject().toString();
		System.out.println(jsonString);
		Book book = new Gson().fromJson(jsonString, Book.class);
		dao.insertBookResource(book);
	}
}
