/**
 * @author wangzhenyu
 * 链表类，实现节点的增加、查询、删除、遍历等功能
 */
public class LinkedList {
	public Node first; // 定义一个头结点
	private int pos = 0;// 节点的位置

	public LinkedList() {
		this.first = null;
	}

	// 插入一个头节点
	public void addFirstNode(String name, String mobile, String address) {
		Node node = new Node(name, mobile, address);
		node.next = first;
		first = node;
	}

	// 插入一个节点
	public void add(int index, String name, String mobile, String address) {
		Node node = new Node(name, mobile, address);
		Node current = first;
		Node previous = first;
		while (pos != index) {
			previous = current;
			current = current.next;
			pos++;
		}
		node.next = current;
		previous.next = node;
		pos = 0;
	}


	// 根据节点的name值删除节点
	public void removeByName(String name) {
		Node current = first;
		Node previous = first; // 记住上一个节点
		while (current!=null) {
			if (current.name.equals(name)) {
				if (current == first) {
					first = first.next;
				} else {
					previous.next = current.next;
				}					
			}
			if(current.next==null){
				break;
				}
			previous = current;
			current = current.next;
		}
	}
	// 根据节点的mobile值删除节点
		public void removeByMobile(String mobile) {
			Node current = first;
			Node previous = first; // 记住上一个节点
			while (current!=null) {
				if (current.mobile.equals(mobile)) {
					if (current == first) {
						first = first.next;
					} else {
						previous.next = current.next;
					}					
				}
				if(current.next==null){
					break;
					}
				previous = current;
				current = current.next;
			}
		}
		// 根据节点的address值删除节点
		public void removeByAddress(String address) {
			Node current = first;
			Node previous = first; // 记住上一个节点
			while (current!=null) {
				if (current.address.equals(address)) {
					if (current == first) {
						first = first.next;
					} else {
						previous.next = current.next;
					}					
				}
				if(current.next==null){
					break;
					}
				previous = current;
				current = current.next;
			}			
		}

	// 根据name查找节点
	public void searchByName(String name) {
		Node current = first;
		while (current!= null) {
			if (current.name.equals(name))
				System.out.println("name:" + current.name + "   mobile:" + current.mobile
						+ "   address:" + current.address);
			if(current.next==null){
			break;
			}
			current = current.next;
		}
	}
	
	// 根据mobile查找节点
	public void searchByMobile(String mobile) {
		Node current = first;
		while (current!= null) {
			if (current.mobile.equals(mobile))
				System.out.println("name:" + current.name + "   mobile:" + current.mobile
						+ "   address:" + current.address);
			if(current.next==null){
			break;
			}
			current = current.next;
		}
		
	}

	// 根据address查找节点
	public void searchByAddress(String address) {
		Node current = first;
		while (current!= null) {
			if (current.address.equals(address))
				System.out.println("name:" + current.name + "   mobile:" + current.mobile
						+ "   address:" + current.address);
			if(current.next==null){
			break;
			}
			current = current.next;
		}
	}

	// 显示出所有的节点信息
	public void displayAllNodes() {
		Node current = first;
		while (current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}

	// 计算出链表节点数
	public int countNodes() {
		Node current = first;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
}
