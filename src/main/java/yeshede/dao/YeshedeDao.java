package yeshede.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import yeshede.domain.Book;
import yeshede.domain.OriginalSource;
import yeshede.domain.Text;

public class YeshedeDao {

	// getting the Original Source
	public List<OriginalSource> getOriginalResources() throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from original_source";
		ResultSet rs = stmt.executeQuery(sql);

		List<OriginalSource> originalSources = new ArrayList<OriginalSource>();

		while (rs.next()) {
			OriginalSource originalSource = new OriginalSource();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			originalSource.setId(id);
			originalSource.setName(name);
			originalSources.add(originalSource);
		}
		return originalSources;
	}

	// inserting the Original Source
	public boolean insertOriginalSource(OriginalSource originalSource) throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = String.format("insert into original_source values  (%d, \'%s\')", originalSource.getId(),
				originalSource.getName());
		return stmt.execute(sql);
	}

	// getting the Book resource
	public List<Book> getBookResource() throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from book";
		ResultSet rs = stmt.executeQuery(sql);

		List<Book> books = new ArrayList<Book>();

		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setPredecessorId(rs.getInt("predecessor"));
			book.setSuccessorId(rs.getInt("successor"));
			book.setNumCopiesPrinted(rs.getInt("numCopiesPrinted"));
			book.setPressPlateLocationId(rs.getInt("pressPlateLocationId"));
			book.setProductionYear(rs.getDate("productionYear"));
			book.setTitle(rs.getString("title"));
			book.setNotes(rs.getString("notes"));
			books.add(book);
		}
		return books;
	}

	// inserting the book resource
	public boolean insertBookResource(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = String.format("insert into book values (%d, %d, %d, %d, %d, %tD, \'%s\', \'%s\')", book.getId(),
				book.getPredecessorId(), book.getSuccessorId(), book.getNumCopiesPrinted(),
				book.getPressPlateLocationId(), book.getProductionYear(), book.getTitle(), book.getNotes());
		return stmt.execute(sql);
	}

	// getting the Text resource
	public List<Text> getTextResources() throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from text";
		ResultSet rs = stmt.executeQuery(sql);

		List<Text> texts = new ArrayList<Text>();

		while (rs.next()) {
			Text text = new Text();
			text.setAuthorId(rs.getInt("id"));
			text.setAlternateTitles(rs.getString("alternateTitles"));
			text.setAuthorId(rs.getInt("authorId"));
			text.setTitle(rs.getString("title"));
			texts.add(text);
		}
		return texts;
	}

	// inserting the text resource
	public boolean insertTextResource(Text text) throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConnection();
		Statement stmt = conn.createStatement();
		String sql = String.format("insert into text values (%d, \'%s\', \'%s\', %d)", text.getId(), text.getTitle(),
				text.getAlternateTitles(), text.getAuthorId());
		return stmt.execute(sql);
	}

}
