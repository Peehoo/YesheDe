package yeshede.domain;

public class Text {
	private int id;
	private String title;
	private String alternateTitles;
	private int authorId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlternateTitles() {
		return alternateTitles;
	}
	public void setAlternateTitles(String alternateTitles) {
		this.alternateTitles = alternateTitles;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}
