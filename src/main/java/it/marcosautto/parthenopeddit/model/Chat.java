package it.marcosautto.parthenopeddit.model;

//--------WIP--------
/*  ATTENZIONE
 *   La chat non verrà implementata,
 *   ma il model Chat devo introdurlo in quanto
 *   viene restituito all'interno del model Group dal
 *   JSON ottenuto dalle API.
 */

public class Chat {
    private int id;
    private String type;

    public Chat(int id, String type){
        this.id = id;
        this.type = type;
    }
}
