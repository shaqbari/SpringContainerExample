package com.sist.daum;

//item ==> 20�� ==> List
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;
/**
 * @author user
 *	database
 *	���� => �迭 ==> Ŭ���� ==> ���� ==> RDBMS(����Ŭ �� sql���) ==> XML, JSON ==> mongodb(json���, nosql)
 *
 *	XML=> <a>aaaa</a> <a href="aaa">
 *
 *
 *          <item>
			<title>ȫ��ǥ ��� ���� �û硦�ѱ��� ������ ����������</title>
			<link>
			http://www.breaknews.com/sub_read.html?uid=512758&section=sc1
			</link>
			<description>
			<![CDATA[
			���Ϸ� �� ���� �÷� �̸��� SNS ��ġ�� �ؿ� ȫ �� ����� Ư�� ģ�ڰ� �ǿ����� �������������� �����ϴ� ��... ������ϰ� ��ġ�� �ϰڴٴ� ������ ������ �»ꡱ�̶�� ����ߴ�.   �̾� ���ѱ����� ������ ������ �ؾ�...
			]]>
			</description>
			<pubDate>Mon, 05 Jun 2017 14:31:00 +0900</pubDate>
			<author>�극��ũ����</author>
			<category>��ġ</category>
			<media:thumbnail url="http://imgnews.naver.net/image/thumb140/5297/2017/06/05/200516.jpg"/>
			</item>
			
			@XmlElement(name="media:thumnail") //�±׸� Ư�����ڰ� �ִ°�� annotation�� name�� �ش�.
 *	
 */
@XmlRootElement
public class Channel {
	private List<Item> item=new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}

	//DI�� spring������ ���� ���� �ƴϴ�.
	//tag������ �Ӽ������� ��������� �Ѵ�.
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
}
