import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author wangzhenyu
 * when start, application  can reload the persistent file
 * use "add" command to add new address entry
 * use "search" command to get one or more address entries
 * use "delete" command to remove one or more address entries
 * use "display" command to display all the address entries
 * use "!help" command to get help
 * use "!quit" command to quit from the application,and persist the data to file
 */
public class AddressesManage {

	LinkedList list = new LinkedList();
	Scanner reader;
	Scanner according;
	Scanner in;

	public static void main(String[] args) {

		AddressesManage addressesmanage = new AddressesManage();
		Scanner scan = new Scanner(System.in);
		try {
			if(!addressesmanage.isEmpty()){
				addressesmanage.readFile();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			int total = addressesmanage.list.countNodes();// 获取当前链表节点总数
			System.out.print("ab> ");
			String choose = scan.nextLine();
			switch (choose) {
			case "add":// 增加一条记录
				addressesmanage.add(total);
				// addressesmanage.list.displayAllNodes();
				break;
			case "search":// 搜索记录
				addressesmanage.search();
				break;
			case "delete":// 删除一条记录
				addressesmanage.delete();
				break;
			case "display":
				addressesmanage.list.displayAllNodes();
				break;
			case "!help":
				addressesmanage.displayHelp();
				break;
			case "!quit":
				System.out.println("Quit from the application!");
				try {
					addressesmanage.writeFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scan.close();
				System.gc();
				System.exit(0);
				break;
			default:
				addressesmanage.displayHelp();
				break;
			}
		}

	}

	//增加一条记录
	public void add(int total) {
		reader = new Scanner(System.in);
		System.out.print("name:");
		String name = reader.nextLine();
		System.out.print("mobile:");
		String mobile = reader.nextLine();
		System.out.print("address:");
		String address = reader.nextLine();
		if (list.first == null) {
			list.addFirstNode(name, mobile, address);
		} else {
			list.add(total, name, mobile, address);
		}
	}

	//根据name、mobile或者、address进行搜索
	public void search() {
		if (list.first == null) {
			System.out.println("The list is empty!");
		} else {
			System.out.print("by (name|mobile|address):");
			according = new Scanner(System.in);// 选择是根据name、mobile还是address搜索
			in = new Scanner(System.in);// 输入要搜索的name、mobile或者、address
			String input;
			
			String by = according.nextLine();
			switch (by) {
			case "name":
				System.out.print("name:");
				input = in.nextLine();
				list.searchByName(input);
				break;
			case "mobile":
				System.out.print("mobile:");
				input = in.nextLine();
				list.searchByMobile(input);
				break;
			case "address":
				System.out.print("address:");
				input = in.nextLine();
				list.searchByAddress(input);
				break;
			}
		}
	}

	//根据name、mobile或者、address进行删除
	public void delete() {
		if (list.first == null) {
			System.out.println("The list is empty!");
		} else {
			System.out.print("by (name|mobile|address):");
			according = new Scanner(System.in);// 选择是根据name、mobile还是address删除
			in = new Scanner(System.in);// 输入要删除的name、mobile或者、address
			String input;
			String by = according.nextLine();
			switch (by) {
			case "name":
				System.out.print("name:");
				input = in.nextLine();
				list.removeByName(input);

				break;
			case "mobile":
				System.out.print("mobile:");
				input = in.nextLine();
				list.removeByMobile(input);
				break;
			case "address":
				System.out.print("address:");
				input = in.nextLine();
				list.removeByAddress(input);
				break;
			}
		}
	}

	// 显示help
	public void displayHelp() {
		System.out.println("Help message!");
		System.out.println("use \"add\" command to add new address entry");
		System.out
				.println("use \"search\" command to get one or more address entries");
		System.out
				.println("use \"delete\" command to remove one or more address entries");
		System.out
				.println("use \"display\" command to display all the address entries");
		System.out.println("use \"!help\" command to get help");
		System.out
				.println("use \"!quit\" command to quit from the application");
	}
	
	public void writeFile() throws IOException{
		FileWriter fw=null;
		 String s = "";
		try{
		fw = new FileWriter("Test.txt");  
		Node current = list.first;
		while (current != null) {
			s+=current.name+" "+current.mobile+" "+current.address+"\r\n";
			current = current.next;
		}
        fw.write(s,0,s.length());    
        fw.flush();
		}catch(IOException e){
			System.out.println(e.toString());
		}finally{
			fw.close();
		}
	}
	public void readFile() throws IOException{
		File file = new File("Test.txt");
		  List<String> txtlist = new ArrayList<String>();
		  String[] data = null;
		  try {
		   BufferedReader bw = new BufferedReader(new FileReader(file));
		   String line = null;
		   //因为不知道有几行数据，所以先存入list集合中
		   while((line = bw.readLine()) != null){
		    txtlist.add(line);
		   }
		   bw.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  //确定数组长度
		  data = new String[txtlist.size()];
		  for(int i=0;i<txtlist.size();i++){
		   data[i] = (String) txtlist.get(i);
		  }
		  String node[][]=new String[data.length][3];
		  for(int i=0;i<data.length;i++){
			  node[i]=data[i].split(" ");
			  if (list.first == null) {
					list.addFirstNode(node[i][0], node[i][1], node[i][2]);
				} else {
					int index=list.countNodes();
					list.add(index, node[i][0], node[i][1], node[i][2]);
				}
		  }
		  list.displayAllNodes();
		  System.out.println("End loading");
	}
	public Boolean isEmpty() throws IOException{
		File file = new File("Test.txt");
		if(!file.exists()){
			System.out.println("File is not exsit");
			return true;
		}
		FileReader fr=new FileReader(file);//建立FileReader对象，并实例化为fr 
		
		//对FileReader类生成的对象使用read()方法，可以从字符流中读取下一个字符。 
		if(fr.read()==-1)//判断是否已读到文件的结尾 
		{ 
			System.out.println("File is Empty");
			fr.close();
		return true;
		}else{
			System.out.println("Loading from file");
			fr.close();
			return false;
		}
		
	}
}
