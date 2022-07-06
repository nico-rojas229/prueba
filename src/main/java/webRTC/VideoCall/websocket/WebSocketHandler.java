package webRTC.VideoCall.websocket;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import webRTC.VideoCall.user.SocketRooms;
import webRTC.VideoCall.user.UserDto;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

//TODO: Edi 10.03.2022
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * Logger para los logs
     */
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    /**
     * Lista de los rooms
     */
    private List<SocketRooms> rooms = new CopyOnWriteArrayList<>();
    /**
     * Lista de las sesiones de webSocket
     */
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    /**
     * Lista de las sessions que guarda las sesiones de la pareja de usuarios que se va a conectar, eso se pasa a la
     * informacion del room y se vacia
     */
    private List<WebSocketSession> peerConnection = new CopyOnWriteArrayList<>();
    /**
     * Lista donde se setean los datos de los usuarios que se pasara a la informacion del room y despues se vacia
     */
    private List<UserDto> users = new CopyOnWriteArrayList<>();
    /**
     * Contabilidad de los usuarios
     */
    private Integer containtsUsers = 0;
    /**
     * Variable para controlar la creacion de los rooms
     */
    private Integer sameRoom = 0;
    /**
     * Variable para determinar si un room se ha cerrado por completo
     */
    private Integer closed = 0;
    /**
     * Variable para llevar la contabilidad de los message en la conexion webSocket
     */
    private Boolean messages = false;

    /**
     * Metodo que se ejecuta cuando el usuario establece conexion con webSocket
     * @param session
     */
    @Override
    public void afterConnectionEstablished (WebSocketSession session) {

        try {

            /*
              Sumamos el usurio que se conecta, mantenemos la variable sameRoom en 0 mientras no se crea un room y nos
              traemos el id del usuario por medio del url
             */
            containtsUsers = containtsUsers + 1;
            sameRoom = 0;
            String url = Objects.requireNonNull(session.getUri()).getPath();
            int index = url.lastIndexOf("/") + 1;
            String id = url.substring(index);

            /*
              Agregamos la session de webSocket a la lista de sesiones y mostramos la informacion del usuario
              que se conecto
             */
            sessions.add(session);

            logger.info("User connected!");
            logger.info("Id: "+id);
            /*logger.info("SessionId: "+session.getId());*/

        } catch (Exception e) {
            logger.error(e.getMessage(), e);       // Mensaje de error
        }

    }

    /**
     * Metodo que se ejecuta cuando se cierra la conexion webSocket del usuario
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed (WebSocketSession session, CloseStatus status) {

        try {

            /*
              Al usuario desconectarse se remueve la session del usuario de la lista de sesiones,
              se resta de la contabilidad de usuarios y nos traemos el id del url que tiene el usuario
             */
            containtsUsers = containtsUsers - 1;
            boolean closing = false;
            String url = Objects.requireNonNull(session.getUri()).getPath();
            int index = url.lastIndexOf("/") + 1;
            String id = url.substring(index);

            /*
              For para recorrer los rooms buscando el room que tenga el id del usuario que se desconecto, al encontrarlo
              recorremos ese room, cerramos la conexion webSocket del mismo y a lo ultimo removemos dicho room de la
              lista
             */
            for (SocketRooms item : rooms){

                if (item.getSessionId().equalsIgnoreCase(id)) {

                    for (WebSocketSession webSocketSession: item.getSessions()) {
                        webSocketSession.close();
                    }

                    /*for (UserDto user: item.getUsers()) {
                        user.getSession().close();
                    }*/

                    rooms.remove(item);

                }

                /*
                  Si en la lista rooms ya no existe la session que se cerro, indicamos que se esta cerrando el room y
                  vamos sumando la variable closed que se supone que cuando sea 2 el room se habra cerrado por completo
                 */
                if (!rooms.contains(item)) {
                    closing = true;
                    closed++;
                }

            }

            /*
              Removemos la session que se cerro de la lista sessions
             */
            sessions.remove(session);

            /*
              Mostramos la informacion del usuario que se desconecto
             */
            logger.info("User disconnected!");
            logger.info("Id: "+id);
            /*logger.info("SessionId: "+session.getId());*/

            /*
              Si los 2 usuarios de un room ya no tienen conexion con el socket y el room ya se removio de la lista,
              mostramos el id del room que se cerro
             */
            if (closing && closed == 2) {
                logger.info("RoomId: "+id+" closed...");
                closed = 0;
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);       // Mensaje de error
        }

    }

    /**
     * Metodo que se ejecuta cuando se envia informacion a la conexion webSocket
     * @param session
     * @param message
     */
    @Override
    public void handleTextMessage (WebSocketSession session, TextMessage message) {

        SocketRooms socketRooms = new SocketRooms();
        String url = Objects.requireNonNull(session.getUri()).getPath();
        int index = url.lastIndexOf("/") + 1;
        String id = url.substring(index);

        try {

            /*
              Ciclo for para recorrer la lista de sesiones de webSocket
             */
            for (WebSocketSession webSocketSession : sessions) {

                /*
                  Si en la lista sessions hay otras sesiones
                 */
                if (!session.getId().equals(webSocketSession.getId())) {

                    /*
                      Aqui validamos que ambos usuarios tengan el mismo id para que pueda realizarse la conexion
                      entre ellos
                     */
                    if (session.getUri().getPath().equalsIgnoreCase(Objects.requireNonNull(webSocketSession.getUri()).getPath())) {

                        webSocketSession.sendMessage(message);

                        String data = message.getPayload();
                        String type = (new JSONObject(data).isNull("type"))?"":(new JSONObject(data).get("type").toString());

                        /*
                        Cuando se envia un message de una session a otra, agregamos esas 2 a la lista peerConnection
                        para crear el room de esos 2 usuarios
                         */
                        if (!messages && sameRoom == 0) {
                            peerConnection.add(session);
                            peerConnection.add(webSocketSession);
                            messages = true;
                        }

                        /*
                          Si llega la informacion del usuario, la seteamos y la guardamos en la informacion de los rooms
                         */
                        if (type.equalsIgnoreCase("data")) {

                            String fullName = (new JSONObject(data).isNull("fullname"))?"":(new JSONObject(data)
                                    .get("fullname").toString());
                            Integer idAffiliate = (new JSONObject(data)
                                    .getInt("idAffiliate"));
                            String username = (new JSONObject(data).isNull("username"))?"":(new JSONObject(data)
                                    .get("username").toString());
                            String role = (new JSONObject(data).isNull("role"))?"":(new JSONObject(data)
                                    .get("role").toString());

                            UserDto userDto = new UserDto();

                            userDto.setFullName(fullName);
                            userDto.setIdAffiliate(idAffiliate);
                            userDto.setUsername(username);
                            userDto.setRole(role);
                            userDto.setSession(session);
                            users.add(userDto);

                            Thread.sleep(2000);
                            webSocketSession.sendMessage(message);
                            logger.info(role + "Data registered successfully!");

                        }

                    }

                }

            }

            /*
              Si se envio un message al socket, si son 2 usuarios los que tratan de conectarse y no se
              ha creado un room; cambiamos nuestra variable boolean a false para que no cree mas de un room por pareja,
              seteamos el id del room y le añadimos los 2 usuarios, luego de eso lo agregamos a la lista rooms;
              a lo ultimo limpiamos la lista peerConnection para cuando se vaya a crear el siguiente room para la
              siguiente pareja de usuarios
             */
            if (messages && sameRoom == 0 && peerConnection.size() == 2) {
                messages = false;
                sameRoom = 1;
                socketRooms.setSessionId(id);
                socketRooms.setSessions(peerConnection);
                socketRooms.setUsers(users);
                rooms.add(socketRooms);
                logger.info("RoomId: "+id+" created successfully!");
                users = new CopyOnWriteArrayList<>();
                peerConnection = new CopyOnWriteArrayList<>();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);       // Mensaje de error
        }

    }

}
