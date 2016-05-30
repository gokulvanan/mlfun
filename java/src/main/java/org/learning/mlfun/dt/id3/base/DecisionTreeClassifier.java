package org.learning.mlfun.dt.id3.base;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * Navie implementation of the DecisionTree classifer with no defensive checks
 * @author gokulvanan.v
 *
 */
public class DecisionTreeClassifier<Data,Classification> {

	static class Node<D,C>{
		public Node<D,C>[] childreen;
		public IAttribute<D> attr; // if node is not the leaf node
		public C classification; //for leaf nod
	}
	
	Node<Data,Classification> root;
	
	public DecisionTreeClassifier(
			Map<Data,Classification> dataClassified, 
			Set<IAttribute<Data>> attributes,
			Classification[] classifications
			){
			this.root  = new Node<>();
			recBuilder(root, dataClassified, attributes, classifications);
	}
	
	
	private Node<Data, Classification> recBuilder(
			Node<Data,Classification> node,
			Map<Data,Classification> dataClassified, 
			Set<IAttribute<Data>> attributes,
			Classification[] classifications){
		
		Map<Classification, Integer> classifcationTypes = Maps.newHashMap();
		Classification mostPopular = null;
		int maxCount = 0;
		for(Data d : dataClassified.keySet()){
			Classification cls = dataClassified.get(d);
			int count = classifcationTypes.getOrDefault(cls, 0) + 1;
			classifcationTypes.put(cls, count);
			if(count > maxCount){
				maxCount = count;
				mostPopular = cls;
			}
		}
		
		//Base cases
		if(classifcationTypes.size() == 1){ //all data is classified
			node.classification = classifcationTypes.keySet().iterator().next();
		}else if(attributes == null || attributes.size() == 0){ // not attributes hence pick the most popular as the answer
			node.classification = mostPopular;
		}else{
			BestAttrTuple<Data,Classification> bestTupple = EntropyCalculator.INSTANCE.getBestAttribute(dataClassified,attributes);
			node.attr = bestTupple.attr;
			Set<IAttribute<Data>> newAttrSet = attributes.stream().
					filter(obj -> !obj.equals(bestTupple.attr)).collect(Collectors.toSet());
			node.childreen = new Node[bestTupple.classfiedDataBuckets.length];
			for(int i=0; i<bestTupple.classfiedDataBuckets.length; i++){
				Node<Data,Classification> child = new Node<>();
				recBuilder(child, bestTupple.classfiedDataBuckets[i], newAttrSet, classifications);
				node.childreen[i] = child;
			}
		}
		return node;
		
	}
	//traverse the tree to leaf node and return with classfication
	public Classification getClassification(Data d){
		Node<Data,Classification> n = root;
		while(n.attr != null){
			int index = root.attr.classify(d);
			n = root.childreen[index];
		}
		return n.classification;
		
	}
	
	
	static class BestAttrTuple<Data,Classification>{
		IAttribute<Data>  attr;
		Map<Data,Classification>[] classfiedDataBuckets;
	}
	
	static enum EntropyCalculator{
		INSTANCE;
		
		public <Data,Classification>  BestAttrTuple<Data,Classification>  getBestAttribute(
				Map<Data, Classification> dataClassified,
				Set<IAttribute<Data>> attributes){
			return null;
		}
	}
	
	
}
