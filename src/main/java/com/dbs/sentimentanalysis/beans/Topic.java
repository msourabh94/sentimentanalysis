package com.dbs.sentimentanalysis.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "topic", types = { CommentsDump.class })
public interface Topic {
	@Value("#{target.topicName}")
	String getTopicName();

	@Value("#{target.counter}")
	int getCounter();
}