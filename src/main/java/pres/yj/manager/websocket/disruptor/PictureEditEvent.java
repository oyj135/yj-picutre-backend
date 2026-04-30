package pres.yj.manager.websocket.disruptor;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;
import pres.yj.manager.websocket.model.PictureEditRequestMessage;
import pres.yj.model.entity.User;

/**
 * 图片编辑事件
 */
@Data
public class PictureEditEvent {

    /**
     * 消息
     */
    private PictureEditRequestMessage pictureEditRequestMessage;

    /**
     * 当前用户的 session
     */
    private WebSocketSession session;
    
    /**
     * 当前用户
     */
    private User user;

    /**
     * 图片 id
     */
    private Long pictureId;

}
