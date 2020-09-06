package it.marcosautto.parthenopeddit.factory;

public class AlertFactory {
    private AlertType alert;

    public AlertType getAlert(String alertType, String name){

        if(alertType.equals("MEMBER_ALERT"))
            alert = new GroupMemberAlert(name);
        else if(alertType.equals("INVITE_ALERT"))
            alert =  new GroupInviteAlert(name);
        else            //QUERY_LENGTH_ALERT
            alert =  new SearchAlert();

        return alert;
    }
}
