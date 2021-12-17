package com.example.chatprogram;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void client()
    {

        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        BufferedReader in2 = null;        //키보드로부터 읽어들이기 위한 입력스트림
        PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress ia = null;              //주소
        String msg;
        MainActivity main = new MainActivity();

        try {
            ia = InetAddress.getByName("192.168.219.100");    //서버로 접속
            socket = new Socket(ia,9000);
            //Thread
            Thread thread1 = new SenderThread(socket);
            Thread thread2 = new ReceiverThread(socket);
            thread1.start();
            thread2.start();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //in2 = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            //System.out.println(socket.toString());
        }catch(IOException e) {
           Log.i("client","error");
        }

        try {

            String data = main.msg;//데이터 가져오기
            out.println(data);//서버로 데이터 전송
            out.flush();
            String str2 = in.readLine();//서버로부터 되돌아오는 데이터 읽어들임
            main.clientTv.setText(str2);
            socket.close();

        }catch(IOException e) {}
    }
}
