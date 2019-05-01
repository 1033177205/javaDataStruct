package ArrayList;

/**
 * 遗留事项：排序
 * @author CGR
 *
 */

public class ArrayList<E> {
	
	/**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * 
     */
    private E[] elementData; // non-private to simplify nested class access
    
    /**
     * 用户添加的元素个数
     */
    private int size;   //数组当前大小
	
	/**
	 * 默认构造函数，创建10个元素大小
	 */
	@SuppressWarnings("unchecked")
	ArrayList(){
		 this.elementData = (E[])new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * 用户传参的构造函数
	 * @param capacity 
	 */
	@SuppressWarnings("unchecked")
	ArrayList(int initialCapacity) {
		if(initialCapacity <= 0) {
			throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
		}
		
		this.elementData = (E[])new Object[initialCapacity];
	}
	
	/**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.  This method does *not* check if the index is
     * negative: It is always used immediately prior to an array access,
     * which throws an ArrayIndexOutOfBoundsException if index is negative.
     */
	private void rangeCheck(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
	/**
     * A version of rangeCheck used by add and addAll.
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    
    private static final int MAX_ARRAY_SIZE = 1024;
    /**
     * 扩容函数
     * @param minCapacity
     */
    @SuppressWarnings("unchecked")
	private void resize(int newCapacity) {
        // overflow-conscious code
        if (newCapacity - DEFAULT_CAPACITY < 0)
            newCapacity = DEFAULT_CAPACITY;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = MAX_ARRAY_SIZE;
        // minCapacity is usually close to size, so this is a win:
        E[] data = (E[])new Object[newCapacity];
        for(int i=0; i<size; i++) {
        	data[i] = elementData[i];
        }       
        elementData = data;
    }
    
    /**
     * 快速添加第一个元素 
     * @param e
     * @return
     */
    public boolean addFristFast(E e) {
    	addFast(0, e);
    	return true;
    }
    
    /**
     * 添加第一个元素
     * @param e
     * @return
     */
    public boolean addFrist(E e) {
    	add(0, e);
    	return true;
    }
    
    /**
     * 添加元素，添加在最后一个位置
     * @param e
     * @return
     */
    public boolean addLast(E e) {
    	add(size, e);
    	return true;
    }
    
	/**
	 * 快速添加操作,把index位拷贝到最后。
	 * @param index
	 * @param e
	 * @return
	 */
	public boolean addFast(int index, E e) {
		rangeCheckForAdd(index);
		if(size == elementData.length)
			resize(elementData.length << 1);
		
		elementData[size]	= elementData[index];
		elementData[index] = e;
		size++;
		return true;
	}
	
	/**
	 * 添加元素操作
	 * @param index
	 * @param e
	 */
	public boolean add(int index, E e) {
		rangeCheckForAdd(index);
		if(size == elementData.length)
			resize(elementData.length << 1);
		
		for(int i=size-1; i>=index; i--) {
			elementData[i+1] = elementData[i];
		}
		
		elementData[index] = e;
		size++;
        return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public E removeLast() {
		return remove(size-1);
	}
	
	/**
	 * 
	 * @return
	 */
	public E removeFristFast() {
		return removeFast(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public E removeFrist() {
		return remove(0);
	}
	
	/**
	 * 按下标删除元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		rangeCheck(index);
		if(size == (elementData.length/4))
			resize(elementData.length>>1);
		
		E oldValue = elementData[index];
		
		for(int i=index; i<size-1; i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		elementData[size] = null;
		return oldValue;
	}
	
	/**
	 * 快速删除元素
	 * @param index
	 * @return
	 */
	public E removeFast(int index) {
		rangeCheck(index);
		if(size == (elementData.length/4))
			resize(elementData.length>>1);
		
		E oldValue = elementData[index];
		elementData[index] = elementData[size-1];
		size--;
		elementData[size] = null;
		return oldValue;
	}
	
	/**
	 * 快速删除元素e
	 * @param e
	 * @return
	 */
	public boolean removeElementFast(E e) {
		int index = indexOf(e);
		if(index != -1) {
			removeFast(index);
			return true;
		}
		return false;
	}
	
	/**
	 * 删除元素e,只删除一个
	 * @param e
	 * @return
	 */
	public boolean removeElement(E e) {
		int index = indexOf(e);
		if(index != -1) {
			remove(index);
			return true;
		}
		return false;
	}
	
	/**
	 * 查询是否包含元素e
	 * @param e
	 * @return
	 */
	public boolean contains(E e) {
        return indexOf(e) >= 0;
    }
	
	/**
	 * 返回这个元素的第一个下标，返回-1序列没有这个元素
	 * @param e
	 * @return
	 */
	public int indexOf(E e) {
		for(int i=0; i<size; i++) {
			if(e.equals(elementData[i]))
				return i;
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("addr "+this+obj);
        return (this == obj);
    }
	
	/**
	 * 返回这个元素最后一个下标
	 * @param e
	 * @return
	 */
	public int lastIndexOf(E e) {
		for(int i=size-1; i>=0; i--) {
			if(e.equals(elementData[i]))
				return i;
		}
		return -1;
	}
	
	/**
	 * 根据元素下标获取数据
	 * @param index
	 * @return
	 */
	public E get(int index) {
        rangeCheck(index);

        return elementData[index];
    }
	
	/**
	 * 根据下标设置参数
	 * @param index
	 * @param element
	 * @return
	 */
	public E set(int index, E e) {
        rangeCheck(index);

        E oldValue = elementData[index];
        elementData[index] = e;
        return oldValue;
    }
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
        return size == 0;
    }
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }
	
	/**
	 * 覆盖toString方法
	 * @param o
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ArrayList: size = %d, capacity = %d\n", size, elementData.length));
		sb.append("[ ");
		for(int i=0; i<size; i++) {
			sb.append(elementData[i]);
			sb.append(" ");
		}
		sb.append("]\n");
		
        return sb.toString();
    }
}
