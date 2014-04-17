/**
 * @author wangzhenyu
 * 链表节点类
 */
public class Node {
	protected Node next; // 指针域
	protected String name;// 姓名
	protected String mobile;// 电话
	protected String address;// 地址

	public Node(String name, String mobile, String address) {
		this.name = name;
		this.mobile = mobile;
		this.address = address;
	}

	// 显示此节点
	public void display() {
		System.out.println("name:" + name + "   mobile:" + mobile
				+ "   address:" + address);
	}
}
