package buaa.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class LogisticClassifier extends Classifier {

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

			File inputFile = new File("weka/train.txt");// 锟斤拷锟斤拷训锟斤拷锟侥硷拷
			atf.setFile(inputFile);
			Instances instancesTrain = atf.getDataSet(); // 锟矫碉拷锟斤拷式锟斤拷锟斤拷训锟斤拷锟斤拷锟斤拷

			instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);// 锟斤拷锟斤拷锟斤拷锟轿伙拷锟�

			inputFile = new File("weka/test.txt");// 锟斤拷锟斤拷锟斤拷锟斤拷募锟�
			atf.setFile(inputFile);
			Instances instancesTest = atf.getDataSet(); // 锟矫碉拷锟斤拷式锟斤拷锟侥诧拷锟斤拷锟斤拷锟斤拷

			instancesTest.setClassIndex(instancesTest.numAttributes() - 1); // 锟斤拷锟矫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫号ｏ拷锟斤拷一锟斤拷为0锟脚ｏ拷锟斤拷instancesTest.numAttributes()锟斤拷锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷

			Logistic m_classifier = new Logistic();// Logistic锟斤拷锟皆斤拷锟斤拷一锟斤拷锟竭硷拷锟截癸拷锟斤拷锟斤拷锟�
			String options[] = new String[4];// 训锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
			options[0] = "-R";// cost锟斤拷锟斤拷锟叫碉拷预锟斤拷锟斤拷锟�影锟斤拷cost锟斤拷锟斤拷锟叫诧拷锟斤拷锟斤拷模锟斤拷锟侥憋拷锟斤拷
			options[1] = "1E-5";// 锟斤拷为1E-5
			options[2] = "-M";// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
			options[3] = "100";// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷10锟斤拷
			m_classifier.setOptions(options);

			m_classifier.buildClassifier(instancesTrain); // 训锟斤拷
			Evaluation eval = new Evaluation(instancesTrain); // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
			eval.evaluateModel(m_classifier, instancesTest);// 锟矫诧拷锟斤拷锟斤拷锟捷硷拷锟斤拷锟斤拷锟斤拷m_classifier
			System.out
					.println("Logistic Regression on Evaluating Inflammation of urinary bladder");
			System.out
					.println(eval.toSummaryString("=== Summary ===\n", false)); // 锟斤拷锟斤拷锟较�
			System.out.println(eval
					.toMatrixString("=== Confusion Matrix ===\n"));// Confusion
																	// Matrix
			this.res = eval.errorRate();

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
	}
}
