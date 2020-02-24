package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.demo.pojo.Message;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * <p>
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */

@RestController

@ServerEndpoint(value = "/webSocketOneToOne/{param}")

public class WebSocketServer {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount;
    //实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key为用户标识
    private static Map<String, WebSocketServer> connections = new ConcurrentHashMap<>();
    private static Map<String, List<Message>> msg = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String role;
    private List<String> list;
    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("param") String param, Session session) {
        this.session = session;
        this.role = param;             //用户标识
        this.list = new ArrayList<>();    //存离线消息
        connections.put(role, this);     //添加到map中
        //判断消息队列
        if (msg.get(role)!=null){
            for (Message message:msg.get(role)){
                send(message,role);
            }
        }
        addOnlineCount();               // 在线数加
        System.out.println("有新连接加入！新用户：" + role + ",当前在线人数为" + getOnlineCount());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        connections.remove(role);  // 从map中移除
        subOnlineCount();          // 在线数减
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject json = JSONObject.parseObject(message);
        System.out.println((String) json.get("msg"));
        String string = null;  //需要发送的信息
        String to = null;      //发送对象的用户标识
        if (json.get("story")!=null) {
            string = (String) json.get("story");
        }
        if (json.get("to")!=null) {
            to = (String) json.get("to");
        }
        Message ms=new Message();
        ms.setStory(string);
        ms.setFrom(role);//发送方
        ms.setMillion((String) json.get("million"));
        ms.setTime((String) json.get("time"));
        ms.setCode((Integer) json.get("code"));
        ms.setFromurl(json.getString("imageurl"));

       if (connections.containsKey(to))
        send(ms, to);
       else {
           System.out.println(to);
           if(!msg.containsKey(to)){
               msg.put(to,new ArrayList<Message>());//添加消息队列
           }
           msg.get(to).add(ms);
       }

    }
    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    //发送给指定角色
    public void send(Message message, String to) {
        System.out.println("send  " + message.getStory() + "  " + message.getFrom() + "  " + to);
        try {
            //to指定用户
            WebSocketServer con = connections.get(to);
            if (con != null) {
                con.session.getBasicRemote().sendText(JSON.toJSONString(message));
            }

//            //from具体用户
//
//            WebSocketServer confrom = connections.get(from);
//
//            if (confrom != null) {
//
//                confrom.session.getBasicRemote().sendText(from + "说：" + msg);
//
//
//            }

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }


    public static synchronized int getOnlineCount() {

        return onlineCount;

    }


    public static synchronized void addOnlineCount() {

        WebSocketServer.onlineCount++;

    }


    public static synchronized void subOnlineCount() {

        WebSocketServer.onlineCount--;

    }

}


