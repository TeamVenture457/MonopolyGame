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
                return "Action Completed";
            case 1:
                return "Bank out of Hotels";
            case 2:
                return "Bank out of Houses";
            case 3:
                return "Bank does not own this Property";
            case 4:
                return "The Property must be a Street";
            case 5:
                return "The Property did not meet the requirements to be Mortgaged";
            case 6:
                return "The Property does not meet the qualifications to place a House.";
            case 7:
                return "The Property does not meet the qualifications to place a Hotel.";
            case 8:
                return "You do not have enough money to do this!";
            case 9:
                return "Unable to Mortgage Property.";
            case 10:
                return "You do not have enough Money for this transaction.";
            case 11:
                return "You do not own this Property";
            case 12:
                return "Cannot sell House.";
            case 13:
                return "Cannot sell Hotel.";
        }

        return "Invalid Message Number.";
    }

}
