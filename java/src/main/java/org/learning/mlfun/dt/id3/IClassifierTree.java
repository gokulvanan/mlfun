package org.learning.mlfun.dt.id3;

public interface IClassifierTree {
    
    public IClassifier getNextClassifier(IClassification classification);

    public void buildTree();
}
