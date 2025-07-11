import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import javax.servlet.http.HttpSession;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocketServer {

    // セッションのリストを保持する変数
    private static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session) {//, EndpointConfig config
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        String userid = (String) httpSession.getAttribute("userid");
        System.out.println("チャットイン:"+userid);

        // セッションをリストに追加
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        String userid = (String) httpSession.getAttribute("userid");
        String username = (String) httpSession.getAttribute("username");

        // メッセージ受信時の処理
        System.out.println(userid + ":" + username + ":" + message);
        
        // ユーザーIDとメッセージ内容を組み合わせてクライアントへブロードキャスト
        broadcast(userid + ":" + username + ":" + message);
    }

    @OnClose
    public void onClose(Session session) {//, CloseReason closeReason
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        String userid = (String) httpSession.getAttribute("userid");
        System.out.println("チャットアウト:"+userid);
        
        // セッションが閉じられたときの処理
        sessions.remove(session);
    }

    // セッションにメッセージを送信するユーティリティメソッド
    private void sendMessageToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            System.out.println("メッセージの送信に失敗しました(chat)");
        }
    }

    // すべてのセッションにメッセージを送信するユーティリティメソッド
    private void broadcast(String message) {
        for (Session session : sessions) {
            sendMessageToSession(session, message);
        }
    }
}
