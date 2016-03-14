package org.learning.mlfun.neuralnetworks;

public class NeuralNet {

    public static void main(String[] args){
        
//        trainingAPerceptronUsingPerceptronRule();
        trainingAPerceptronUsingGradientDescent();
       
    }
    
    
    static void trainingAPerceptronUsingGradientDescent(){
        //TODO fix this and given proper training data.. this data is not helping
        int [] [] x = {
                {2,3}, {2,-5}, {4,4}, {-3,-3}, {-9,5} ,{6,6}
        }; 
        double [] y = {
                4, 7, 2, -3, -8, 5
        };
        
        SimplePerceptron perceptron = new SimplePerceptron();
        //Some ramndom weights
        perceptron.w = new double[2];
        perceptron.w[0]= 3;
        perceptron.w[1]= 3;
        
        double learningRate = 0.002, errorThreshold =0.3; //TODO fix this application and check
        boolean converged = false;
        do{
            int convergeCount = 0;
            for(int j=0; j<x.length; j++){ //check if existing weights converge for the sample inputs
                perceptron.x = x[j];
                double actualY = perceptron.getY();
                System.out.println("actual Y "+actualY+" expected Y "+y[j]);
                if((Math.abs(actualY - y[j])/100d) < errorThreshold){ // if prediction is within accepted threshold
                    convergeCount++;
                }
            }
           converged = (convergeCount == x.length);
           if(!converged){ //compute weights by gradientDescent
               for(int i=0 ;i<perceptron.w.length; i++){
                   perceptron.w[i] = perceptron.w[i] - 
                           (learningRate * gradientOfErrorFunction(perceptron,x,y, i));
               }
           }

        }while(!converged); // reitrate till convergence
        System.out.println(perceptron.w[0]+","+perceptron.w[1]);
    }
    
    private static double gradientOfErrorFunction(SimplePerceptron perceptron,
            int[][] x, double[] y, int wtIndex) {
        // (w.xd - yd)xid
        double buff = 0;
        double xid = 0;
        
        for(int i=0; i<y.length; i++){
            perceptron.x = x[i];
            double wxd = perceptron.dotProductOfWX();
            double yd = y[i];
            buff += wxd - yd;
            xid += x[i][wtIndex];
        }
        
        double gradient = buff * xid;
        return gradient;
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
        
        SimpleThresholdedPerceptron perceptron = new SimpleThresholdedPerceptron();
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
        SimpleThresholdedPerceptron perceptron = new SimpleThresholdedPerceptron();
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
