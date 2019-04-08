package com.dbs.sentimentanalysis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbs.sentimentanalysis.beans.CommentsDump;
import com.dbs.sentimentanalysis.beans.Reason;
import com.dbs.sentimentanalysis.beans.Topic;
import com.dbs.sentimentanalysis.beans.TopicReason;

@Repository
public interface SentimentAnalysisRepository extends CrudRepository<CommentsDump, Long> {

	@Query("SELECT topic as topicName, count(*) as counter FROM CommentsDump p group by topic order by counter desc")
	public Iterable<Topic> findTopicsAndCount();

	@Query("SELECT reason as reasonName, count(*) as counter FROM CommentsDump p group by reason order by counter desc")
	public Iterable<Reason> findReasonsAndCount();

	@Query("select distinct topic as topicName, reason as reasonName, avg(rating) as averageRating, count(*) as counter from CommentsDump group by topic, reason order by averageRating desc, counter desc")
	public Iterable<TopicReason> getTopicReasonData();
}