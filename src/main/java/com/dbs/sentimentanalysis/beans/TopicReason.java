package com.dbs.sentimentanalysis.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "topicreason", types = { CommentsDump.class })
public interface TopicReason {
	@Value("#{target.topicName}")
	String getTopicName();

	@Value("#{target.reasonName}")
	String getReasonName();

	@Value("#{target.counter}")
	int getCounter();

	@Value("#{target.averageRating}")
	float getAverageRating();

}
