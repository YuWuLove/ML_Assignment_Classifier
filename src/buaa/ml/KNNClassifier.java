package buaa.ml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.NumberFormat;

import buaa.ml.KNN;
import buaa.ml.KNNClassifier;



public class KNNClassifier extends Classifier{
	
	public void Run()
	{
		TestKNN t = new TestKNN(); 
        String datafile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "train.txt"; 
        String testfile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "test.txt"; 
        String resultfile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "knn-result.txt";
        try 
        { 
            List<List<Double>> datas = new ArrayList<List<Double>>(); 
            List<List<Double>> testDatas = new ArrayList<List<Double>>(); 
            t.read(datas, datafile, 0); 
            t.read(testDatas, testfile, 1); 
            FileWriter out = new FileWriter(new File(resultfile));
            KNN knn = new KNN(); 
            for (int i = 0; i < testDatas.size(); i++) 
            { 
                List<Double> test = testDatas.get(i);  
                for (int j = 0; j < test.size(); j++) 
                {	
                    out.write(test.get(j) + ",");
                } 
                if (Math.round(Float.parseFloat((knn.knn(datas, test, 5)))) == 1)
                {
                	out.write("m\n");
                }
                else
                {
                	out.write("f\n");
                }
            }
            out.close();
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
		
	}
	
	public void Evaluate()
	{
		int correct = 0;
		int incorrect = 0;
		int total = 0;
		double correctRate = 0.00;
		double incorrectRate = 0.00;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		
		TestKNN t = new TestKNN();
		
		String datafile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "train.txt"; 
        String testfile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "test.txt"; 
        String resultfile = new File("").getAbsolutePath() + File.separator + "weka" + File.separator + "knn-result.txt";
        try
        {
        	List<List<Double>> resultDatas = new ArrayList<List<Double>>(); 
            List<List<Double>> testDatas = new ArrayList<List<Double>>(); 
            t.read(resultDatas, resultfile, 0); 
            t.read(testDatas, testfile, 0);
            for(int i = 0; i < testDatas.size(); i++)
            {
            	List<Double> test = testDatas.get(i);
            	List<Double> result = resultDatas.get(i);
            	if (test.equals(result))
            		correct ++;
            	else
            		incorrect ++;
            	total ++;
            }
            correctRate = 100.00*correct/total;
            incorrectRate = 100 - correctRate;
            System.out.println("KNN Classifier Evaluation:");
            System.out.println("Total Num:      " + total);
            System.out.println("Correct Num:    " + correct);
            System.out.println("Incorrect Num:  " + incorrect);
            System.out.println("Correct Rate:   " + nf.format(correctRate) + " %");
            System.out.println("Incorrect Rate: " + nf.format(incorrectRate) + " %");
        }
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }
	}
}
