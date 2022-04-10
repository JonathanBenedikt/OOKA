package org.bonn.ooka.buchungssystem.ss2022;

import java.util.List;

public class HotelRetrieval {

    private static DBAccess access;

    public String getHotelByName(String name)
    {
        openSession();
        // Liste für das Abfragen aller Hotels, die String im Namen besitzen
        List<String> hotelresult = access.getObjects(DBAccess.HOTEL, name);
        String resultString = "";

        for (String str: hotelresult)
        {
            resultString += str+" ";
        }
        resultString = resultString.substring(0,resultString.length()-1);

        return resultString;
    }

    public void openSession()
    {
        if(access == null)
            access = new DBAccess();

        System.out.println("Es wird versucht eine Verbindung zur Datenbank zu öffnen.");
        access.openConnection();
        System.out.println("Verbindungsaufbau zur Datenbank konnte erfolgreich durchgeführt werden.");
    }
}
