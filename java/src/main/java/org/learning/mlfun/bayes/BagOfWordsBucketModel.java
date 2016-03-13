package org.learning.mlfun.bayes;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Model to break down message to bag of words - this is one such representation there can be others
 * using word count and also tokens rather than words where token could be modeled to get more context of message to 
 * build probability rather than just count of word or presence of word
 * @author gokulvanan
 *
 */
public class BagOfWordsBucketModel implements IMessageBucket{

	private final Set<String> words = Sets.newConcurrentHashSet();
	private int count = 0;

	@Override
	public void upsert(String message) {
		List<String> words = tokenizedWords(message);
		words.addAll(words);
		count++;
	}

	@Override
	public double noOfMessages() {
		return count;
	}

	@Override
	public double getProbOfMsgInBucket(String message, int totalUniqueTokens,
			int laplaceSmoothingConstant) {
		List<String> words = tokenizedWords(message);
		return words.stream()
		.map(word -> probOfWordInBagOfWords(word,totalUniqueTokens,laplaceSmoothingConstant))
		.reduce(1d,((a, b) -> a*b))
		.doubleValue();
	}

	@Override
	public Set<String> getUniqueTokens() {
		return words;
	}
	
	private List<String> tokenizedWords(String message){
		return Arrays.asList(message.split(" ")); //TODO change this to regex
	}


	private double probOfWordInBagOfWords(String word, int totalUniqueTokens,
			int laplaceSmoothingConstant) {
		double wordPresent = (word.contains(word)) ? 1d : 0d;
		return (wordPresent + laplaceSmoothingConstant)
				/(words.size() + (laplaceSmoothingConstant * totalUniqueTokens));
	}
}
