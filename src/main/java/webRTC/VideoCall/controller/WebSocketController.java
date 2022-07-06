package webRTC.VideoCall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class WebSocketController {

//    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

//    /**
//     * Controller que maneja los mensajes que se mandan desde html usando SockJS
//     * @param message
//     * @throws Exception
//     */
//    @MessageMapping("/hello/{room}")
//    @SendTo("/topic/greetings")
//    @JsonCreator
//    public Greeting prueba(@DestinationVariable String room, HelloMessage message) throws Exception {
//
//        try {
//
//            logger.info("User send message in room "+room);
//
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
//        /*
//          Devolvemos el mensaje recibido
//         */
//        return new Greeting(HtmlUtils.htmlEscape(message.getText()));
//
//    }

}
