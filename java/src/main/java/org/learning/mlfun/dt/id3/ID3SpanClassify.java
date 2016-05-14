package org.learning.mlfun.dt.id3;

import org.learning.mlfun.ISpamPredictor;
import org.learning.mlfun.dt.id3.base.DecisionTreeClassifier;

/**
 * Decision Tree classifier
 *
 * 
 * @author gokulvanan.v
 *
 */
public class ID3SpanClassify implements ISpamPredictor {

	private final DecisionTreeClassifier<String,SpamCheckClassifications> classifer;
	
	public ID3SpanClassify(DecisionTreeClassifier<String, SpamCheckClassifications> classifier) {
		this.classifer = classifier;
	}
	
	@Override
    public boolean isSpam(String message) {
       return classifer.getClassification(message) == SpamCheckClassifications.SPAM;
    }

    @Override
    public void markAsSpam(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void markAsNotSpam(String message) {
        // TODO Auto-generated method stub
        
    }

	
}
