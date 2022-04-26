package com.example.jpatest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SeverClientz {

public static void main(String[] args) {
	// data / 참조변수 / null
	Socket socket = null;
	// for 반복문에 무환 루프 씌움
		for(;;) {
			try {
				    //참조변수 	/ 생성자(인스턴스)
					socket = new Socket();
					// 연결 요청 띄움
					System.out.println("[연결 요청]");
					// 참조 변수.연결하다 / 넷 소켓 주소(포트번호만 주면, 호스트의 IP주소는 임의의 IP주소가 된다.)
					socket.connect(new InetSocketAddress("192.168.0.222", 50000));
					// 연결 성공 띄움
					System.out.println("[연결 성공]");
					
					byte[] bytes = null;
					// 문자열 메시지 = null
					String message = null;														// Stream : 내부 반복자를 사용하므로 병렬 처리가 쉽다. 수많은 데이터의 흐름 속에서
					//출력 스트림				/ 참조변수.출력 스트림							//				 각가의 원하는 값을 가공하여 최종 적으로 제공(단일 방향으로 연속적으로 이동)
					OutputStream os = socket.getOutputStream();
					
					message = "test";
					// 문자열 인코딩 된 byte형태로 넘겨준다.
					bytes = message.getBytes("UTF-8");
					
					os.write(bytes);
					os.flush();
					// data 보냈을 시 성공을 띄움
					System.out.println("[데이터 보내기 성공]");
					// data					참조변수.가져오다 입력받을 데이터(참조변수)		/  inputstream : 프로그램이 데이터를 입력받을 때에는 입력(InputStream)이라고 부른다.
					InputStream is = socket.getInputStream();
					// 참조변수 / 생성자 인스턴스
					bytes = new byte[1024];
					// Byte 의 수 읽기
					int readByteCount = is.read(bytes);

					message = new String(bytes, 0, readByteCount, "UTF-8");
					char[] ch = message.toCharArray();
					//  data타입	/ 참조변수		/ 생성자(인스턴스)
					StringBuilder builder = new StringBuilder();
					// for 문 / char : java는 유니코드 기반으로 뮨저룰 표현하기 때문에, 자바의 문자 자료형인 char형은 2바이트를 사용한다.
					for (char c : ch) {

							int i = (int) c;
							
							builder.append(Integer.toHexString(i).toUpperCase());

					}
					// 헥사 값을 만들어 낸다 String으로(문자열)			/ toString 이 인스턴스의 값을 String으로 변환 합니다.
					System.out.println("Hex = " + builder.toString());
					os.close();
					is.close();
			
			} 
			catch (Exception e) 
			{
				//에러 메세지의 발생 근원지를 찾아 단계별로 에러를 출력한다.
				e.printStackTrace();

			}
			// if문 소켓이 닫혔을 경우 처리
			if (!socket.isClosed()) {

					try {

						socket.close();

					} // 		예외 처리
					catch (Exception e) 
					{	//	에러 메세지의 발생 근원지를 찾아 단계별로 에러를 출력한다.
						e.printStackTrace();

					}	

			}

		}
	}
}