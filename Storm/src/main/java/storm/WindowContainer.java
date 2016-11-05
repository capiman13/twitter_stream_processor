package storm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import backtype.storm.tuple.Values;

public class WindowContainer {
	private int size;
	private int advance;


	public WindowContainer(int size,int advance){
		this.size=size;
		this.advance=advance;
	}

	public Long getContainerToDiscard(long timestamp){
		return (timestamp-this.size+this.advance)/this.advance*this.advance;
	}

	public List<Long> getContainers(long timestamp){
		List<Long> containers = new ArrayList<Long>();
		long lowlimit0 = (timestamp-this.size+this.advance)/this.advance;
		long lowlimit = lowlimit0*this.advance;

		System.out.println("ts y lowlimit "+timestamp+ " "+lowlimit0+" "+ lowlimit);
		while(lowlimit<=timestamp){
			lowlimit+=this.advance;
			containers.add(lowlimit);
		}
		return containers;
	}

	public static void main(String[] args) {
		WindowContainer c = new WindowContainer(100, 50); //100 50 1600
		Long number= 49L;
		List<Long> cs=c.getContainers(number);
		for (long x:cs){
			System.out.println("containersss " +x);
		}
		System.out.println(c.getContainerToDiscard(number));
		
		
		String cadena = "12341234:hash1:hash2:hast12";
		String[] hashtags = (cadena).split(":");//[0]timestamp and hashtag
		Arrays.copyOfRange(hashtags, 1, hashtags.length);
		for (String hashtag: Arrays.copyOfRange(hashtags, 1, hashtags.length)){
			System.out.println("Spout emit: "+hashtags[0]+ " "+hashtag);
		}
	}

}