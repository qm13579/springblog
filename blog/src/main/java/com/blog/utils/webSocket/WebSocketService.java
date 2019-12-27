package com.blog.utils.webSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@ServerEndpoint(value = "/websocket/{sid}")
@Component
public class WebSocketService {

    //final 保证线程安全
    private static  int onLineCount = 0;
    //concurrent 包的线程安全set，用来存放每个客户端对应的websocket对象
    private static CopyOnWriteArrayList<WebSocketService> webSocketSet= new CopyOnWriteArrayList<WebSocketService>();

    //创建一个线程安全的map,用来存放每个客户端的websocket对象

    //与某个客户端会话，通过它给客户端发送数据
    private Session session;
    //接受sid
    private String sid="";

    //连接建立成功调用对象
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        webSocketSet.add(this);  //加入set中
        addOnlineCount();
        log.info("有新窗口监听"+sid+"：当前人数"+getOnLineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e){
            log.error("webcocket Io异常");
        }
    }

    //连接关闭调用
    @OnClose
    public void onClose(){
        webSocketSet.remove(this); //重set中删除
        subOnlineCount();
        log.info("有一连接关闭！，当前在线人数为"+getOnLineCount());
    }

    //收到客户端消息后调用方法
    @OnMessage
    public void onMessage(String message,Session Session){
        log.info(session.toString());
        log.info("收到来自窗口"+sid+"的信息："+message);
        //群发消息
        for (WebSocketService item: webSocketSet) {
            try{
                item.sendMessage("消息收到了哦");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

//    @OnError
    public void onError(){
        log.error("发生错误");
    }

    //实现服务端主动推动
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
    //自定义群发消息
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        log.info("推送消息"+sid+"推送内容:"+message);
        for (WebSocketService item: webSocketSet) {
            try{
                if(sid==null){
                    log.info(item.session.getId());
                    item.sendMessage(message);
                }else if ( item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e){
                continue;
            }
        }
    }


    public static synchronized int getOnLineCount(){
        return onLineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketService.onLineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketService.onLineCount--;
    }


}
