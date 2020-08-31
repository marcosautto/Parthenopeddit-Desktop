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
    private int replies_to_message_int;
    private String sender_id;
    private int received_id;
    private User sender_user;
    private Chat receiver_chat;
    private Message replies_to_message;
}
