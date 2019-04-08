package com.dbs.sentimentanalysis.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopicReasonDataResponse {
	private Set<String> categoryList;
	private Map<String, List<TopicReason>> topicReasonCategoryMap;
	private TopicReasonChartData topicReasonChartData;

	public Set<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<String> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, List<TopicReason>> getTopicReasonCategoryMap() {
		return topicReasonCategoryMap;
	}

	public void setTopicReasonCategoryMap(Map<String, List<TopicReason>> topicReasonCategoryMap) {
		this.topicReasonCategoryMap = topicReasonCategoryMap;
	}

	public TopicReasonChartData getTopicReasonChartData() {
		return topicReasonChartData;
	}

	public void setTopicReasonChartData(TopicReasonChartData topicReasonChartData) {
		this.topicReasonChartData = topicReasonChartData;
	}

}
