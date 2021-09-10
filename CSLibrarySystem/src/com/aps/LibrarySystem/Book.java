package com.aps.LibrarySystem;

public class Book {
	private String bookID;
	private String bookPath;
	
	public Book() {
		this.bookID = null;
		this.bookPath = null;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getBookPath() {
		return bookPath;
	}

	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}
}



