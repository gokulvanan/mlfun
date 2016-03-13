package org.learning.mlfun.bayes;

import java.util.Set;

public interface IMessageBucket {

	/**
	 * ingest message
	 * @param words
	 */
	void upsert(String message);

	/**
	 * count of number of messages upserted note one upsert call is one message
	 * @return
	 */
	double noOfMessages();

	/**
	 * Return prob score of Message in this bucket
	 * @param word
	 * @param totalUniqueTokens
	 * @param laplaceSmoothingConstant
	 * @return
	 */
	double getProbOfMsgInBucket(String message, int totalUniqueTokens,
			int laplaceSmoothingConstant);


    Set<String> getUniqueTokens();

}
