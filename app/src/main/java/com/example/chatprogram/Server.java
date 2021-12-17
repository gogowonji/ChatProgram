package com.example.chatprogram;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void server()
    {
        Socket socket = null;                //Client와 통신하기 위한 Socket
        ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
        BufferedReader in = null;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;                //Client로 데이터를 내보내기 위한 출력 스트림
        MainActivity main = new MainActivity();
        try{
            server_socket = new ServerSocket(9000);
            //Thread
            Thread thread1 = new SenderThread(socket);
            Thread thread2 = new ReceiverThread(socket);
            thread1.start();
            thread2.start();
        }catch(IOException e)
        {
            Log.i("server","error");
        }
        try {

            socket = server_socket.accept();    //서버 생성 , Client 접속 대기

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    //입력스트림 생성
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); //출력스트림 생성

            String str = null;
            str = in.readLine();            //Client로부터 데이터를 읽어옴

            //System.out.println("Client로 부터 온 메세지 : " + str);

            out.write(str);
            out.flush();
            main.serverTv.setText(str);

            socket.close();
        }catch(IOException e){

        }
    }
}
