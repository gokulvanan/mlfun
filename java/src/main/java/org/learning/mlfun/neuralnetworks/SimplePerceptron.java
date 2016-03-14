package org.learning.mlfun.neuralnetworks;

public class SimplePerceptron {

    public double [] w; //weights
    public int [] x;
    
    
    public double getY(){
        double output = 0d;
        for(int i=0; i<w.length; i++){
           output += w[i]*x[i]; 
        }
        return output;
    }
    
    public void updateWeights(int index, double weight){
        this.w[index] = weight;
    }

    public double dotProductOfWX() {
        double output = 0d;
        for(int i=0; i< x.length; i++){
            output = x[i] * w[i];
        }
        return output;
    }
}
