package buaa.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class DecisionTreeClassifier extends Classifier {

	private void writewekafile(String filename, People set) {
		try {
			FileWriter fw = new FileWriter("weka//" + filename);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("@relation sex\n");
			bw.write("@attribute height real\n");
			bw.write("@attribute weight real\n");
			bw.write("@attribute sex {f,m}\n");
			bw.write("@data\n");

			Iterator it = set.people.iterator();
			while (it.hasNext()) {
				Person p = (Person) it.next();
				if (p.IsMale) {
					bw.write(p.feature.height + "," + p.feature.weight + ","
							+ "m\n");
				} else {
					bw.write(p.feature.height + "," + p.feature.weight + ","
							+ "f\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void BalanceSample(int totaltrainingsample) {
		super.BalanceSample(totaltrainingsample);
		writewekafile("train.txt", this.trainingSet);
		writewekafile("test.txt", this.testSet);

		// this.people.GetBalancePeople(totaltrainingsample,this.trainingSet,this.testSet);;
	}

	public void RandomSample(int totaltrainingsample) {
		super.RandomSample(totaltrainingsample);

		writewekafile("train.txt", this.trainingSet);
		writewekafile("test.txt", this.testSet);
	}

	public void Run() {
		try {

			ArffLoader atf = new ArffLoader(); // Reads a source that is in arff
												// (attribute relation file
												// format) format.

			File inputFile = new File("weka/train.txt");// ����ѵ���ļ�
			atf.setFile(inputFile);
			Instances instancesTrain = atf.getDataSet(); // �õ���ʽ����ѵ������

			instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);// �������λ��

			inputFile = new File("weka/test.txt");// ��������ļ�
			atf.setFile(inputFile);
			Instances instancesTest = atf.getDataSet(); // �õ���ʽ���Ĳ�������

			instancesTest.setClassIndex(instancesTest.numAttributes() - 1); // ���÷������������кţ���һ��Ϊ0�ţ���instancesTest.numAttributes()����ȡ����������

			J48 j48_classifier = new J48();
			j48_classifier.buildClassifier(instancesTrain);
			Evaluation eval_id3 = new Evaluation(instancesTrain); // ����������
			eval_id3.evaluateModel(j48_classifier, instancesTest);// �ò������ݼ�������m_classifier
			System.out
					.println("Decision Tree on Evaluating Inflammation of urinary bladder");
			System.out.println(eval_id3.toSummaryString("=== Summary ===\n",
					false)); // �����Ϣ
			System.out.println(eval_id3
					.toMatrixString("=== Confusion Matrix ===\n"));// Confusion
																	// Matrix

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
	}
}
