package com.sist.daum;

/**
 * @author user
 *	영화 ==> 블로그 검색 (XML, JSON)
 *	============================== JAXP(DOM, SAX)
 *								   JAXV(o)
 *								   XML => class(언마셜)
 *								   class => XML(마셜)
 *
 *	//다음에서 xml넘겨줄때
 *	<channel>
 *		<item> <!--item하나가 블로그 하나 최대 20개 댓글까지 --> 태그사이 태그는 클래스, 태그사이 문자는 변수, 네이버는위에 RSS가 가장 최상위에 있다.
 *			<description>영화댓글</description>
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
	 * 데이터 사전 : 이중 가장 많은 숫자으 데이터만(어떤 성향인지 알아보고) 골라서 가져온다.
	 * String[] feel = {"사랑","로맨스","매력","즐거움","스릴",
				"소름","긴장","공포","유머","웃음","개그",
				"행복","전율","경이","우울","절망","신비",
				"여운","희망","긴박","감동","감성","휴머니즘",
				"자극","재미","액션","반전","비극","미스테리",
				"판타지","꿈","설레임","흥미","풍경","일상",
				"순수","힐링","눈물","그리움","호러","충격","잔혹"};
		String[] genre = {
				"드라마","판타지","공포","멜로","애정",
				"로맨스","모험","스릴러","느와르","다큐멘터리",
				"코미디","미스터리","범죄","SF","액션","애니메이션"	
		};
	 * */
	
	public String daumReviewData(int page, String title){
		//List<String> list=new ArrayList<String>();
		String review="";
		
		try {
			//String key="61ffeb36aeadc7fdf0fc2e572f1b462c";//중요정보는 xml로 숨겨야 한다.
			URL url=new URL("https://apis.daum.net/search/blog?apikey="+key
					+"&result=20"
					+"&output=xml"
					+"&pageno="+page
					+"&q="+URLEncoder.encode(title, "UTF-8")
			);//3바퀴 돌릴 예정이다.
			System.out.println("url : "+url);
			JAXBContext jc=JAXBContext.newInstance(Channel.class);
			//xml=>class , class => xml
			
			Unmarshaller un=jc.createUnmarshaller();
			Channel channel=(Channel)un.unmarshal(url);//파싱
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
		System.out.println("영화명 : ");
		String title=scanner.next();*/		
		//DaumManager dm=new DaumManager();
		
		String total="";
		for(int i=1; i<=3; i++){
			//String reviw=dm.daumReviewData(i, title);
			String reviw=daumReviewData(i, title);
			total+=reviw; //우선 파일로 만들고 정형화 시킨후 오라클에 넣는다.
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
		dm.daumReviewSave("고스트베이비");
	}
}
