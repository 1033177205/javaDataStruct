package ArrayList;

public class LRUArrayList<E> {
	private static final int DEFAULT_CAPACITY = 20;
	private ArrayList<E> arrlist = new ArrayList<E>(DEFAULT_CAPACITY);
	
	LRUArrayList(){
	}
	
	/**
	 * ArrayList 简单实现一个LRU 学习使用
	 * @param data
	 */
	public void saveData(E data) {				//O(n)
		//1.先查询这个数据是否存在
		int index = arrlist.indexOf(data);		//O(n)
		
		//2.判断是否存在
		if(index == -1) {
			//3.如果不存在，先判断数据是否满，把最后一位删除，再把数据插到头部
			if(DEFAULT_CAPACITY == arrlist.size()) {
				arrlist.removeLast();			//O(1)
				arrlist.addFrist(data);			//O(n)
			}
			else {
				//5.如果数据没满，就插入到头部
				arrlist.addFrist(data);			//O(n)
			}
		}
		else {
			//4.如果存在，就把节点删除，再在头部插入
			arrlist.remove(index);				//O(n)
			arrlist.addFrist(data);				//O(n)
		}
	}
	
	@Override
	public String toString() {
		return arrlist.toString();
    }
}
