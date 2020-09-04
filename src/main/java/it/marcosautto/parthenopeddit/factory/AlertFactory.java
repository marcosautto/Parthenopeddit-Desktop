package it.marcosautto.parthenopeddit.factory;

public class AlertFactory {

    public AlertType getAlert(String alertType, String name){
        if(alertType.equals("MEMBER_ALERT"))
            return new GroupMemberAlert(name);
        else if(alertType.equals("INVITE_ALERT"))
            return new GroupInviteAlert(name);
        else if(alertType.equals("QUERY_LENGTH_ALERT"))
            return new SearchAlert();

        return null;
    }
}
