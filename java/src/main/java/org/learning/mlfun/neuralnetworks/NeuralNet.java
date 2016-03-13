package org.learning.mlfun.neuralnetworks;

public class NeuralNet {

    public static void main(String[] args){
        
        trainingAPerceptronUsingPerceptronRule();
       
    }
    
    
    //Here attempting to train a perceptron using sample training data
    // this data is such that any data with x dimesion +ve is true and rest are false.
    static void trainingAPerceptronUsingPerceptronRule(){
        //training data tha is linearly seperable
        int [] [] x = {
                {2,3}, {2,-5}, {4,4}, {-3,-3}, {-9,5} ,{6,6}
        }; 
        boolean [] y = {
                true, true, true, false, false, true
        };
        
        ThresholdedPerceptron perceptron = new ThresholdedPerceptron();
        perceptron.threshold =0;
        //Some ramndom weights
        perceptron.w = new double[2];
        perceptron.w[0]= 3;
        perceptron.w[1]= 3;
        
        boolean converged = false;
        double learningRate = 0.001d;
        do{                                 //start infinite iteration till convergence
            int convergeCount = 0;
            for(int i=0; i<x.length; i++){  //iterate for all inputs
                perceptron.x = x[i];
                boolean actualY = perceptron.willFire(); //check if perceptron will fire
                if(actualY == y[i]) convergeCount ++; //this input has converged
                while(y[i] != actualY){                  // check this to actual result expected of perceptron
                    // perceptron rule for learning
                    int direction  = (y[i] == true) ? 1 : -1; // direction to move based on comparison of expected with actual result
                    for(int j=0; j< perceptron.w.length; j++){
                        perceptron.w[j] = (perceptron.w[j] + (learningRate * direction * perceptron.x[j])); // modify weights to accomodate this case
                    }
                    actualY = perceptron.willFire(); //rerun perceptron with new weights
                }
                
            }
            converged = (convergeCount == y.length); // all training data are correctly classified
        }while(!converged);
        
        System.out.println(perceptron.w[0]+","+perceptron.w[1]); //print the converged weights
    }
    
    
    //basic example showing how ot update weights using perceptron rule
    static void perceptronRuleExample(){
        ThresholdedPerceptron perceptron = new ThresholdedPerceptron();
        perceptron.threshold =0;
        perceptron.w = new double[2];
        perceptron.w[0]= 5;
        perceptron.w[1]= -3;
        
        int[] sampleX  = {2,5}; boolean expectedY = true;
        perceptron.x = sampleX;
        boolean actualY = perceptron.willFire();
        
        double learningRate = 0.001d;
        while(expectedY != actualY){
            // perceptron rule for learning
            int direction  = (expectedY == true) ? 1 : -1; // direction to move
            for(int i=0; i< perceptron.w.length; i++){
                perceptron.w[i] = (perceptron.w[i] + (learningRate * direction * perceptron.x[i]));
            }
            actualY = perceptron.willFire();
            System.out.println(actualY);
        }
    }
} 
