package ArrayList;

import java.util.Random;

/**
 * 用自己实现的ArrayList类实现LRU缓存淘汰算法
 * @author CGR
 * @version
 */


public class ArrayListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUArrayList<Integer> lru = new LRUArrayList();
		
		Random ran = new Random();
		int data = 0;
		
		for(int i=0; i<50; i++) {
			data = ran.nextInt(50);
			lru.saveData(data);
			System.out.println("data = "+data);
			System.out.println(lru);
		}
		
	}

}
