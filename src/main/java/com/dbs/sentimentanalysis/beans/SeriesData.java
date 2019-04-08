package com.dbs.sentimentanalysis.beans;

public class SeriesData {
	private String name;
	private Integer[] data;

	public SeriesData(String name, Integer[] data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getData() {
		return data;
	}

	public void setData(Integer[] data) {
		this.data = data;
	}
}
