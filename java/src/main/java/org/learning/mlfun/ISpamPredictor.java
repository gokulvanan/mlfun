package org.learning.mlfun;

public interface ISpamPredictor {
	
	public boolean isSpam(String message);

	public void markAsSpam(String message);
	
	public void markAsNotSpam(String message);
}
