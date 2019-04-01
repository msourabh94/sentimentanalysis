package com.dbs.sentimentanalysis.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reason", types = { CommentsDump.class })
public interface Reason {
	@Value("#{target.reasonName}")
	String getReasonName();

	@Value("#{target.counter}")
	int getCounter();
}
