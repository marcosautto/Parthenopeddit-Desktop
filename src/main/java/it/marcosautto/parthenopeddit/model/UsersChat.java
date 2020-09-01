package it.marcosautto.parthenopeddit.model;

import java.util.ArrayList;

public class UsersChat extends Chat{
    private String of_user_id;
    private String last_opened_on;
    private int other_user_chat_id;
    private User of_user;
    private UsersChat other_user_chat;
    
    private Message last_message;
    private ArrayList<Message> chat_log;

    public UsersChat(int id, String of_user_id,
            String last_opened_on,
            int other_user_chat_id,
            User of_user,
            UsersChat other_user_chat,
            Message last_message,
            ArrayList<Message> chat_log) {
        super(id, "users_chat");
        this.of_user_id = of_user_id;
        this.last_opened_on = last_opened_on;
        this.other_user_chat_id = other_user_chat_id;
        this.of_user = of_user;
        this.other_user_chat = other_user_chat;
        this.last_message = last_message;
        this.chat_log = chat_log;
    }
}
