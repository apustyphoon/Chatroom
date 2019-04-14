package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


 
public class ChatServer {
	
	
	//主函数入口
	public static void main(String[] args) throws IOException {
		//实例化一个服务器类的对象
		ChatServer cs=new ChatServer();
		//调用方法，为指定端口创建服务器
		cs.setUpServer(9003);
	}
 
	private void setUpServer(int port) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server=new ServerSocket(port);
		//打印出当期创建的服务器端口号
		System.out.println("服务器创建成功!端口号："+port);
		ChatServerHandlerExcutePool singleExecutor = new ChatServerHandlerExcutePool(50,10000);
		Socket socket=null;
		while(true) {
			//等待连接进入
			socket=server.accept();
			System.out.println("进入了一个客户机连接："+socket.getRemoteSocketAddress().toString());
			
			//启动一个线程去处理这个对象
//			ServerThread st=new ServerThread(socket);
//			st.start();
			
			//创建一个线程，并加入线程池
			singleExecutor.execute(new ServerThread(socket));
		}
	}
}