package buaa.ml;

import java.util.Iterator;

import libsvm.*;

import java.io.*;

public class SVMClassifier extends Classifier {
	
	double accuracy = 1.0;
	
	//���� TestMethod() ����
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
	svm_trainfile.txt ��������ʽ��ѵ������
	svm_testfile.txt ��������ʽ�Ĳ�������
	svm_result.txt ���Ԥ����
	svm_mode.txt ���ѵ���õ�ģ��
	 */
	{
		Iterator i = this.trainingSet.people.iterator();
		Iterator i1 = this.testSet.people.iterator();
		int lable1 = 0;
		int lable2 = 1;
		double height1, weight1;
		Person p;
		// �� triainSet �е����ݴ�ŵ� svm_trian.txt ��
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
		
		// �� testSet �е����ݴ�ŵ� svm_test.txt ��
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
		
		String []arg ={ "svm_train.txt", //���SVMѵ������
        				"svm_model.txt"};
						//"-C 0.5 -w0 1 -w1 10"};  //���SVMͨ��ѵ������ѵ��������ģ��

		String []parg={"svm_test.txt",   //��Ų�������
						"svm_model.txt",  //����ѵ��ģ��
						"svm_result.txt"};  //���ɵĽ���ļ�
		
		
		try{
			svm_train.main(arg);   
			accuracy = svm_predict.main(parg); 
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
