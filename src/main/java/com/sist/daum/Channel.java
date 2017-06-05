package com.sist.daum;

//item ==> 20개 ==> List
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;
/**
 * @author user
 *	database
 *	변수 => 배열 ==> 클래스 ==> 파일 ==> RDBMS(오라클 등 sql기반) ==> XML, JSON ==> mongodb(json기반, nosql)
 *
 *	XML=> <a>aaaa</a> <a href="aaa">
 *
 *
 *          <item>
			<title>홍준표 당권 도전 시사…한국당 중진들 ‘견제구’</title>
			<link>
			http://www.breaknews.com/sub_read.html?uid=512758&section=sc1
			</link>
			<description>
			<![CDATA[
			‘하루 한 개’ 꼴로 이른바 SNS 정치를 해온 홍 전 지사는 특히 친박계 의원들을 ‘바퀴벌레’로 비유하는 등... 사람들하고만 정치를 하겠다는 것인지 걱정이 태산”이라고 우려했다.   이어 “한국당이 나름의 역할을 해야...
			]]>
			</description>
			<pubDate>Mon, 05 Jun 2017 14:31:00 +0900</pubDate>
			<author>브레이크뉴스</author>
			<category>정치</category>
			<media:thumbnail url="http://imgnews.naver.net/image/thumb140/5297/2017/06/05/200516.jpg"/>
			</item>
			
			@XmlElement(name="media:thumnail") //태그명 특수문자가 있는경우 annotation에 name을 준다.
 *	
 */
@XmlRootElement
public class Channel {
	private List<Item> item=new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}

	//DI다 spring에서만 쓰는 것이 아니다.
	//tag값인지 속성값인지 구분해줘야 한다.
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
}
