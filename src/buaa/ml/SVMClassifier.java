package buaa.ml;

import java.util.Iterator;

import libsvm.*;

import java.io.*;

public class SVMClassifier extends Classifier {
	
	double accuracy = 1.0;
	
	//重载 TestMethod() 方法
	public double TestMethod()
	{
		System.out.println("---------------------Test---------------------");
		double inacc = 1-accuracy;
		
		System.out.println("Accurate Rate:\t" + accuracy);
		System.out.println("False Rate:\t" + inacc);
		return accuracy;
	}
	
	public void Run()
	/*
	svm_trainfile.txt 存放所需格式的训练数据
	svm_testfile.txt 存放所需格式的测试数据
	svm_result.txt 存放预测结果
	svm_mode.txt 存放训练好的模型
	 */
	{
		Iterator i = this.trainingSet.people.iterator();
		Iterator i1 = this.testSet.people.iterator();
		int lable1 = 0;
		int lable2 = 1;
		double height1, weight1;
		Person p;
		// 将 triainSet 中的数据存放到 svm_trian.txt 中
		try{
			File f = new File("svm_train.txt");
			FileWriter out = new FileWriter(f);
			while(i.hasNext())
			{
				p = (Person) i.next();
				if(p.IsMale == true)
					out.write(lable1+" ");
				else
					out.write(lable2+" ");
				height1 = p.feature.height;
				weight1 = p.feature.weight;
				out.write("1:"+height1+" ");
				out.write("2:"+weight1);
				if(i.hasNext())
					out.write("\r\n");
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 将 testSet 中的数据存放到 svm_test.txt 中
		try{
			File f1 = new File("svm_test.txt");
			FileWriter out1 = new FileWriter(f1);
			while(i1.hasNext())
			{
				p = (Person) i1.next();
				if(p.IsMale == true)
					out1.write(lable1+" ");
				else
					out1.write(lable2+" ");
				height1 = p.feature.height;
				weight1 = p.feature.weight;
				out1.write("1:"+height1+" ");
				out1.write("2:"+weight1);
				if(i1.hasNext())
					out1.write("\r\n");
			}
			out1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String []arg ={ "svm_train.txt", //存放SVM训练数据
        				"svm_model.txt"};
						//"-C 0.5 -w0 1 -w1 10"};  //存放SVM通过训练数据训练出来的模型

		String []parg={"svm_test.txt",   //存放测试数据
						"svm_model.txt",  //调用训练模型
						"svm_result.txt"};  //生成的结果文件
		
		
		try{
			svm_train.main(arg);   
			accuracy = svm_predict.main(parg); 
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
