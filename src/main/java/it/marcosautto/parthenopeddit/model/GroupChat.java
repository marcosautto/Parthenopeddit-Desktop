package it.marcosautto.parthenopeddit.model;

//--------WIP--------
/*  ATTENZIONE
*   La chat non verrà implementata,
*   ma il model GroupChat devo introdurlo in quanto
*   viene restituito all'interno del model Group dal
*   JSON ottenuto dalle API.
 */

import java.util.ArrayList;

public class GroupChat extends Chat {

    private ArrayList<Message> received_messages;
    private Message last_message;
    private int of_group_id;
    private Group of_group;

    public GroupChat(
            int id,
            ArrayList<Message> received_messages,
            Message last_message,
            int of_group_id,
            Group of_group) {
        super(id, "group_chat");
        this.received_messages = received_messages;
        this.last_message = last_message;
        this.of_group_id = of_group_id;
        this.of_group = of_group;
    }

}
