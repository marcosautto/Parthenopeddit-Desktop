package it.marcosautto.parthenopeddit.model;

//--------WIP--------
/*  ATTENZIONE
 *   La chat non verrà implementata,
 *   ma il model Message devo introdurlo in quanto
 *   viene restituito all'interno del model Group dal
 *   JSON ottenuto dalle API.
 */

public class Message {
    private int id;
    private String body;
    private String timestamp;
    private int replies_to_message_id;
    private String sender_id;
    private int receiver_id;
    private User sender_user;
    private Chat receiver_chat;
    private Message replies_to_message;

    public Message(int i,
            String body,
            String timestamp,
            int replies_to_message_id,
            String sender_id,
            int receiver_id,
            User sender_user,
            Chat receiver_chat,
            Message replies_to_message){
         this.body = body;
         this.timestamp = timestamp;
         this.replies_to_message_id = replies_to_message_id;
         this.sender_id = sender_id;
         this.receiver_id = receiver_id;
         this.sender_user = sender_user;
         this.receiver_chat = receiver_chat;
         this.replies_to_message = replies_to_message;
    }
}
