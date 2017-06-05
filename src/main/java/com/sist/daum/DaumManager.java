package com.sist.daum;

/**
 * @author user
 *	��ȭ ==> ��α� �˻� (XML, JSON)
 *	============================== JAXP(DOM, SAX)
 *								   JAXV(o)
 *								   XML => class(�𸶼�)
 *								   class => XML(����)
 *
 *	//�������� xml�Ѱ��ٶ�
 *	<channel>
 *		<item> <!--item�ϳ��� ��α� �ϳ� �ִ� 20�� ��۱��� --> �±׻��� �±״� Ŭ����, �±׻��� ���ڴ� ����, ���̹������� RSS�� ���� �ֻ����� �ִ�.
 *			<description>��ȭ���</description>
 *		</item>
 *	</channel>
 */
import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
public class DaumManager {
	private String key;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	/*
	 * ������ ���� : ���� ���� ���� ������ �����͸�(� �������� �˾ƺ���) ��� �����´�.
	 * String[] feel = {"���","�θǽ�","�ŷ�","��ſ�","����",
				"�Ҹ�","����","����","����","����","����",
				"�ູ","����","����","���","����","�ź�",
				"����","���","���","����","����","�޸Ӵ���",
				"�ڱ�","���","�׼�","����","���","�̽��׸�",
				"��Ÿ��","��","������","���","ǳ��","�ϻ�",
				"����","����","����","�׸���","ȣ��","���","��Ȥ"};
		String[] genre = {
				"���","��Ÿ��","����","���","����",
				"�θǽ�","����","������","���͸�","��ť���͸�",
				"�ڹ̵�","�̽��͸�","����","SF","�׼�","�ִϸ��̼�"	
		};
	 * */
	
	public String daumReviewData(int page, String title){
		//List<String> list=new ArrayList<String>();
		String review="";
		
		try {
			//String key="61ffeb36aeadc7fdf0fc2e572f1b462c";//�߿������� xml�� ���ܾ� �Ѵ�.
			URL url=new URL("https://apis.daum.net/search/blog?apikey="+key
					+"&result=20"
					+"&output=xml"
					+"&pageno="+page
					+"&q="+URLEncoder.encode(title, "UTF-8")
			);//3���� ���� �����̴�.
			System.out.println("url : "+url);
			JAXBContext jc=JAXBContext.newInstance(Channel.class);
			//xml=>class , class => xml
			
			Unmarshaller un=jc.createUnmarshaller();
			Channel channel=(Channel)un.unmarshal(url);//�Ľ�
			List<Item> temp=channel.getItem();
			
			
			for(Item i: temp){
				//System.out.println(i.getDescription());
				review+=i.getDescription()+"\n";
			}
			
		} catch (Exception e) {
			System.out.println("DaumManager daumReviewData() : "+e.getMessage());
			e.printStackTrace();
		}
		
		return review;		
	}
	
	public void daumReviewSave(String title) {
		/*Scanner scanner=new Scanner(System.in);
		System.out.println("��ȭ�� : ");
		String title=scanner.next();*/		
		//DaumManager dm=new DaumManager();
		
		String total="";
		for(int i=1; i<=3; i++){
			//String reviw=dm.daumReviewData(i, title);
			String reviw=daumReviewData(i, title);
			total+=reviw; //�켱 ���Ϸ� ����� ����ȭ ��Ų�� ����Ŭ�� �ִ´�.
		}
		try {
			FileWriter fw=new FileWriter("c:\\review_data\\daum.txt");
			fw.write(total);
			fw.close();
		} catch (Exception e) {
			System.out.println("daumReviewSave() :"+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		DaumManager dm=new DaumManager();
		dm.setKey("61ffeb36aeadc7fdf0fc2e572f1b462c");
		dm.daumReviewSave("��Ʈ���̺�");
	}
}
