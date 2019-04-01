package com.dbs.sentimentanalysis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.sentimentanalysis.beans.CommentsDump;
import com.dbs.sentimentanalysis.beans.Reason;
import com.dbs.sentimentanalysis.beans.Topic;
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

}
