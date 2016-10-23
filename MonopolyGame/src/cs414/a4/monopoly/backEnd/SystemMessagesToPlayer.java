package cs414.a4.monopoly.backEnd;

/**
 * Created by TatroIII on 10/22/2016.
 */
public class SystemMessagesToPlayer {

    SystemMessagesToPlayer(){

    }

    public static String getSystemMessage(int messageNumber){

        switch (messageNumber){
            case 0:
                return "Action Compleated";
            case 1:
                return "Bank out of Hotels";
            case 2:
                return "Bank out of Houses";
            case 3:
                return "Bank does not own this Property";
            case 4;
                return "The Property must be a Street";

        }

        return "Invalid Message Number.";
    }

}
