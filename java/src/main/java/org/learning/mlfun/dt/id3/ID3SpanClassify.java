package org.learning.mlfun.dt.id3;

import org.learning.mlfun.ISpamPredictor;
import org.learning.mlfun.dt.id3.classification.SpamCheckClassifications;

/**
 * Decision Tree classifier
 * Sample decision logic built 
 *  - Check is email is from UnkownConsumer if No - noSpam else 
 * 
 * @author gokulvanan.v
 *
 */
public class ID3SpanClassify implements ISpamPredictor {

    private IClassifierTree classifierTree;
    
    @Override
    public boolean isSpam(String message) {
        IClassification classification = null; //start without any classification
        do{
            IClassifier classifier =  classifierTree.getNextClassifier(classification);
            if(classifier == null) break;
            classification  = classifier.classify(message);
        }while(!classification.isFinal());
        return classification == SpamCheckClassifications.SPAM;
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
