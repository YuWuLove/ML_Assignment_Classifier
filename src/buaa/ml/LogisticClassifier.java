package buaa.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class LogisticClassifier extends Classifier{
	
	private void writewekafile(String filename,People set)
	{
		try {
			FileWriter fw = new FileWriter("weka//"+filename);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("@relation sex\n");
			bw.write("@attribute height real\n");
			bw.write("@attribute weight real\n");
			bw.write("@attribute sex {f,m}\n");
			bw.write("@data\n");
			
			Iterator it = set.people.iterator();
			while(it.hasNext())
			{
				Person p = (Person) it.next();
				if(p.IsMale)
				{
					bw.write(p.feature.height+","+p.feature.weight+","+"m\n");
				}
				else
				{
					bw.write(p.feature.height+","+p.feature.weight+","+"f\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void BalanceSample(int totaltrainingsample)
	{
		super.BalanceSample(totaltrainingsample);
		writewekafile("train.txt",this.trainingSet);
		writewekafile("test.txt",this.testSet);		
		
		//this.people.GetBalancePeople(totaltrainingsample,this.trainingSet,this.testSet);;
	} 
	
	public void RandomSample(int totaltrainingsample)
	{
		super.RandomSample(totaltrainingsample);
		
		writewekafile("train.txt",this.trainingSet);
		writewekafile("test.txt",this.testSet);		
	}
	
	public void Run()
	{
		try
		{		
			
			ArffLoader atf = new ArffLoader(); //Reads a source that is in arff (attribute relation file format) format.

	        File inputFile = new File("weka\\train.txt");//读入训练文件
	        atf.setFile(inputFile);
	        Instances instancesTrain = atf.getDataSet(); // 得到格式化的训练数据
	       
	        instancesTrain.setClassIndex(instancesTrain.numAttributes()-1);//设置类别位置
	       
	       
	        inputFile = new File("weka\\test.txt");//读入测试文件
	        atf.setFile(inputFile);         
	        Instances instancesTest = atf.getDataSet(); // 得到格式化的测试数据
	       
	        instancesTest.setClassIndex(instancesTest.numAttributes() - 1); //设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
			
			
			 Logistic m_classifier=new Logistic();//Logistic用以建立一个逻辑回归分类器
		     String options[]=new String[4];//训练参数数组
		     options[0]="-R";//cost函数中的预设参数  影响cost函数中参数的模长的比重
		     options[1]="1E-5";//设为1E-5
		     options[2]="-M";//最大迭代次数
		     options[3]="10";//最多迭代计算10次
		     m_classifier.setOptions(options);
		     		       
		     m_classifier.buildClassifier(instancesTrain); //训练  
		     Evaluation eval = new Evaluation(instancesTrain); //构造评价器
		     eval.evaluateModel(m_classifier, instancesTest);//用测试数据集来评价m_classifier
		     System.out.println("Logistic Regression on Evaluating Inflammation of urinary bladder");
		     System.out.println(eval.toSummaryString("=== Summary ===\n",false));  //输出信息
		     System.out.println(eval.toMatrixString("=== Confusion Matrix ===\n"));//Confusion Matrix
		}
		catch(Exception e)
		{
			
		}
	}
}
