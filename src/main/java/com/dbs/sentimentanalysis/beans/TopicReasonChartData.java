package com.dbs.sentimentanalysis.beans;

import java.util.List;
import java.util.Set;

public class TopicReasonChartData {
	private Set<String> categoryList;
	private List<SeriesData> seriesDataList;

	public Set<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<String> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SeriesData> getSeriesDataList() {
		return seriesDataList;
	}

	public void setSeriesDataList(List<SeriesData> seriesDataList) {
		this.seriesDataList = seriesDataList;
	}

}
