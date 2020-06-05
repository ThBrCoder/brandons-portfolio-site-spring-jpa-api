package com.brandon.portfolio.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="GAMES_DISPLAY_LIST")
// @SecondaryTable(name="GAMES_MASTER_LIST") // Experimental. Remove if this doesn't succeed.
public class GamesDisplayList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="year")
	private int year;
	
	public GamesDisplayList() {}
	
	public GamesDisplayList(String title, int year) {
		this.title = title;
		this.year = year;
	}

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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", year=" + year + "]";
	}
}
