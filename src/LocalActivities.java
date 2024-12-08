import java.util.Scanner;

public class LocalActivities implements MenuScreen {

    //använd scannern från Main
    Scanner myScan;

    String yearRoundActivities = """
            Följande finns året runt:
            1. ICA Supermarket Järvsö, Turistvägen 45, 827 52 Järvsö (1,2 km bort).
            2. Restaurang Bergshotellet, Vallmovägen 62, 827 51 Järvsö (900 meter bort).
            3. Järvsö Crêperie, Stenevägen 12, 827 47 Järvsö (650 meter bort).
            """;
    String winterActivities = """
            De här vinteraktiviteterna rekommenderar vi:
            1. Järvsöbacken, Rödmyravägen 17, 827 50 Järvsö (300 meter bort).
            2. Järvsö skidstation (längd), Alpstigen 6, 827 51 Järvsö (550 meter bort).
            3. Järvsö skiduthyrning, Rödmyravägen 22, 827 50 Järvsö (400 meter bort).
            4. Järvsöbaden Spa, Turistvägen 4, 827 51 Järvsö (900 meter bort).
            """;
    String summerActivities = """
            1. Trappstigens vandringsled, Bergvägen 24, 827 60 Järvsö (2 km bort).
            2. Järvsö Fisketurer, Turistvägen 70, 827 54 Järvsö (1,5 km bort).
            3. Järvsö Mountainbike-park, Vallmovägen 20, 827 51 Järvsö (800 meter bort).
            4. Järvsö Kajak, Strandvägen 23, 827 51 Järvsö (900 meter bort).
            """;

    @Override
    public void printMenu() {

    }

    @Override
    public void printExitInstructions() {

    }

    @Override
    public void exitClass() {

    }

    //läsning från scannern, val vinter eller sommar
    public void chooseSeason() {

    }


    //Välj visa aktiviteter, att man utifrån kommer in i den interfaceade metoden då

    //Välj alla aktiviteter eller vinter- eller sommaraktiviteter

    //switch case som visar alla aktiviteter, vinter- eller sommaraktiviteter samt anrop till exit-metoden om skriver exit via scanner

    //interfacad exit-metod som clearar terminalfönstret och tar tillbaks en till huvudmenyn (anropar main)



}
