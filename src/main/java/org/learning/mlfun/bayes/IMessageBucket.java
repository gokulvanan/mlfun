package org.learning.mlfun.bayes;

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

	/**
	 * Get unique number of message tokens indexed
	 * @return
	 */
	int uniqueTokenSize();

}
