package org.learning.mlfun.dt.id3;


public interface IClassifier<Data> {

    public IClassification classify(Data data); 
}
