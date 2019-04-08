package com.dbs.sentimentanalysis.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.sentimentanalysis.beans.CommentsDump;
import com.dbs.sentimentanalysis.beans.Reason;
import com.dbs.sentimentanalysis.beans.SeriesData;
import com.dbs.sentimentanalysis.beans.Topic;
import com.dbs.sentimentanalysis.beans.TopicReason;
import com.dbs.sentimentanalysis.beans.TopicReasonChartData;
import com.dbs.sentimentanalysis.beans.TopicReasonDataResponse;
import com.dbs.sentimentanalysis.repository.SentimentAnalysisRepository;

@RestController
@CrossOrigin(origins = "*")
public class AnalysisController {

	@Autowired
	DataSource dataSource;

	@Autowired
	SentimentAnalysisRepository sentimentAnalysisRepository;

	@RequestMapping("/getData")
	public List<CommentsDump> getData() {
		Iterable<CommentsDump> systemlist = sentimentAnalysisRepository.findAll();
		List<CommentsDump> commentsList = new ArrayList<CommentsDump>();
		for (CommentsDump commentModel : systemlist) {
			commentsList.add(commentModel);
		}
		return commentsList;
	}

	@RequestMapping("/getTopicData")
	public Iterable<Topic> getTopicData() {
		Iterable<Topic> systemlist = sentimentAnalysisRepository.findTopicsAndCount();
		/*
		 * Map<String, Integer> commentsList = new TreeMap<String, Integer>(); for
		 * (Topic commentModel : systemlist) {
		 * commentsList.put(commentModel.getTopicName(), commentModel.getCounter()); }
		 */
		return systemlist;
	}

	@RequestMapping("/getReasonData")
	public Iterable<Reason> getReasonData() {
		Iterable<Reason> systemlist = sentimentAnalysisRepository.findReasonsAndCount();
		/*
		 * Map<String, Integer> commentsList = new TreeMap<String, Integer>(); for
		 * (Reason commentModel : systemlist) {
		 * commentsList.put(commentModel.getReasonName(), commentModel.getCounter()); }
		 */
		return systemlist;
	}

	@RequestMapping("/getTopicReasonData")
	public TopicReasonDataResponse getTopicReasonData(@RequestParam("topicMaxCount") Integer topicMaxCount) {
		TopicReasonDataResponse response = new TopicReasonDataResponse();
		Iterable<TopicReason> systemlist = sentimentAnalysisRepository.getTopicReasonData();

		Map<String, List<TopicReason>> topicReasonCategoryMap = new LinkedHashMap<String, List<TopicReason>>();
		Set<String> categoryList = new LinkedHashSet<String>();
		List<TopicReason> topicReasonList = null;
		topicMaxCount = 5;
		int topicCounter = 0;
		for (TopicReason topicReason : systemlist) {
			categoryList.add(topicReason.getTopicName());
			if (topicReasonCategoryMap.get(topicReason.getTopicName()) != null) {
				topicReasonCategoryMap.get(topicReason.getTopicName()).add(topicReason);
			} else {
				topicReasonList = new LinkedList<TopicReason>();
				topicReasonList.add(topicReason);
				topicReasonCategoryMap.put(topicReason.getTopicName(), topicReasonList);
			}
			if (topicCounter++ > topicMaxCount)
				break;
		}
		int i = 0;
		Integer[] data = null;
		List<SeriesData> seriesDataList = new LinkedList<SeriesData>();
		SeriesData seriesData = null;
		TopicReasonChartData topicReasonChartData = new TopicReasonChartData();
		i = 0;
		for (String topicName : topicReasonCategoryMap.keySet()) {

			int totalElements = topicReasonCategoryMap.size();

			System.out.println(totalElements);
			System.out.println("topicName : " + topicName);
			for (TopicReason topicReason : topicReasonCategoryMap.get(topicName)) {
				data = new Integer[totalElements];
				java.util.Arrays.fill(data, 0, i, 0);
				data[i] = topicReason.getCounter();
				java.util.Arrays.fill(data, i+1, totalElements, null);

				System.out.println("ReasonName : " + topicReason.getReasonName() + " data : ");
				for (int j = 0; j < data.length; j++) {
					System.out.print(" j : "+j+" : "+data[j] + " ");
				}

				seriesData = new SeriesData(topicReason.getReasonName(), data);
				seriesDataList.add(seriesData);
			}
			i++;
		}
		topicReasonChartData.setCategoryList(categoryList);
		topicReasonChartData.setSeriesDataList(seriesDataList);
		response.setTopicReasonChartData(topicReasonChartData);
		response.setCategoryList(categoryList);
		response.setTopicReasonCategoryMap(topicReasonCategoryMap);

		return response;
	}

}
