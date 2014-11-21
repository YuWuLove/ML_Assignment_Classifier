package buaa.ml;
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.util.ArrayList; 
import java.util.List;

public class TestKNN {
	
	/**
     * �������ļ��ж�ȡ����
     * @param datas �洢���ݵļ��϶���
     * @param path �����ļ���·��
     */ 
    public void read(List<List<Double>> datas, String path, int buff){ 
        try { 
            BufferedReader br = new BufferedReader(new FileReader(new File(path))); 
            String data = br.readLine(); 
            List<Double> l = null; 
            while (data != null) {
            	if (data.contains("@"))
            	{
            		data = br.readLine();
            		continue;
            	}
            	String t[] = data.split(",");
                l = new ArrayList<Double>(); 
                for (int i = 0; i < (t.length - buff); i++) {
                	if (t[i].equals("m"))
                		l.add(Double.parseDouble("1"));
                	else if(t[i].equals("f"))
                		l.add(Double.parseDouble("0"));
                	else
                		l.add(Double.parseDouble(t[i])); 
                } 
                datas.add(l); 
                data = br.readLine(); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
}
