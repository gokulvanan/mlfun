package org.learning.mlfun.neuralnetworks;

public class ThresholdedPerceptron {

    public double [] w; //weights
    public int [] x;
    public double threshold;
    
    
    public boolean willFire(){
        double output = 0d;
        for(int i=0; i<w.length; i++){
           output += w[i]*x[i]; 
        }
        return output > threshold;
    }
    
    public void updateWeights(int index, double weight){
        this.w[index] = weight;
    }
}
