package org.learning.mlfun.bayes;


public class BayesSpamPredictor implements ISpamPredictor {

	private final double threshold;
	private final IMessageBucket spamBucket;
	private final IMessageBucket notSpamBucket;
	private final int laplaceSmoothingConstant;
	
	public BayesSpamPredictor() {
		this.threshold = 0.70d;
		this.spamBucket = new BagOfWordsBucketModel();
		this.notSpamBucket = new BagOfWordsBucketModel();
		this.laplaceSmoothingConstant = 1;
	}
	
	@Override
	public boolean isSpam(String message) {
//		String[] words = tokenizedWords(message);
		int totalUniqueTokens = spamBucket.uniqueTokenSize() + notSpamBucket.uniqueTokenSize();
		double porbOfSpamGivenMessage = spamBucket.getProbOfMsgInBucket(message, totalUniqueTokens, laplaceSmoothingConstant);
		double probOfNotSpamGiventMessage = notSpamBucket.getProbOfMsgInBucket(message, totalUniqueTokens, laplaceSmoothingConstant);
		double probOfSpam = computeProbOfSpamFromLearntMessages(spamBucket,notSpamBucket);
		
		//BayesRule
		double probOfBeingSpamGivenMsg = porbOfSpamGivenMessage * probOfSpam 
				/ ((porbOfSpamGivenMessage * probOfSpam ) + (probOfNotSpamGiventMessage * (1d - probOfSpam)));
		
		return probOfBeingSpamGivenMsg > threshold;
	}


	@Override
	public void markAsSpam(String message) {
		spamBucket.upsert(message);
	}

	@Override
	public void markAsNotSpam(String message) {
		notSpamBucket.upsert(message);
	}
	
	
	private double computeProbOfSpamFromLearntMessages(IMessageBucket spam, IMessageBucket notSpam) {
		double totalSize = spam.noOfMessages() + laplaceSmoothingConstant + notSpam.noOfMessages() + laplaceSmoothingConstant;
		return (spam.noOfMessages() + laplaceSmoothingConstant) / totalSize ;
	}


}
