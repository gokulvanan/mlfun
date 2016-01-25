package org.learning.mlfun;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.learning.mlfun.bayes.BayesSpamPredictor;
import org.learning.mlfun.bayes.ISpamPredictor;

public class SpamTest {
	private ISpamPredictor predictor;
	
	@Before
	public void  setup(){
		predictor = new BayesSpamPredictor();
		//TODO ingest training data
	}
	@Test
	public void test(){
		System.out.println("Running SpamTest");
		//TODO check if spam or not
	}
	
	
	@After
	public void tearDown(){
		predictor = null;
	}
}
