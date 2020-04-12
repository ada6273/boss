package com.cmcc.coc.cbpsp.alipay.client;

public class ArrayQueue<T> {

	// 默认长度
	private static final int MAX_CAPACITY = 100000;
	// 容量
	int capacity;
	// 最大索引
	int maxIndex;
	// 元素数量
	int size;
	// 定义一个数组，存储数据
	private Object[] queue;
	// 定义对头标志，元素索引从head+1开始，tail结束
	private int head;
	// 定义队尾标志
	private int tail;

	public ArrayQueue() {
		init(16);
	}

	public ArrayQueue(int capacity) {
		init(capacity);
	}

	// 初始化一个数组
	public void init(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new OutOfMemoryError("initialCapacity too large");
		if (capacity <= 0)
			throw new IndexOutOfBoundsException("initialCapacity must be more than zero");
		this.queue = new Object[capacity];
		this.capacity = capacity;
		this.maxIndex = capacity - 1;
		this.head = tail = -1;
		this.size = 0;
	}

	/**
	 * 往队列尾部添加数据
	 * 
	 * @param object
	 *            数据
	 */
	public void push(T object) {
		if (size >= capacity) {
			// 满了
			System.out.println("queue's size more than or equal to array's capacity");
			return;
		}
		if (++tail > maxIndex) {
			// 循环
			tail = 0;
		}
		queue[tail] = object;
		size++;
	}

	/**
	 * 从队列头部拉出数据
	 * 
	 * @return 返回队列的第一个数据
	 */
	public T pop() {
		if (size <= 0) {
			System.out.println("the queue is null");
			return null;
		}
		if (++head > maxIndex) {
			head = 0;
		}
		size--;
		Object old = queue[head];
		queue[head] = null;
		return (T) old;
	}

	/**
	 * 查看第一个数据
	 * 
	 * @return
	 */
	public T peek() {
		if (size <= 0) {
			System.out.println("the queue is null");
			return null;
		}
		return (T) queue[head + 1];
	}

	/**
	 * 清空队列
	 */
	public void empty() {
		for (int i = 0; i < queue.length; i++) {
			queue[i] = null;
		}
		tail = head = -1;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		ArrayQueue<String> queue = new ArrayQueue<>(5);
		System.out.println(queue.size());
		queue.push("a");
		queue.push("b");
		queue.push("c");
		queue.push("d");
		queue.push("e");
		System.out.println(queue.peek());
		System.out.println(queue.size());
		queue.push("f");
		System.out.println(queue.size());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		queue.push("f");
		System.out.println(queue.size());
		System.out.println(queue.peek());

		queue.empty();
		System.out.println(queue.peek());
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
	}
}
