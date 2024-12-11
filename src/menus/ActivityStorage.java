package menus;

import utility.Utility;

import java.util.Scanner;

public class ActivityStorage extends SeasonOption implements MenuScreen {

    ActivityStorage(Scanner scanner) {
        super(scanner,"""
            == Sommarförråd ==
             Fotboll
             Gångstavar
             Kubb
             Boule
             Fiskeutrustning
            ""","""
            == Vinterförråd ==
             Skidor
             Pulka
             Pimpeltrustning
             Bastutält
             Snöskyffel
            ""","""
            == Sommar- och Vinterförråd ==
             Fotboll
             Gångstavar
             Kubb
             Boule
             Fiskeutrustning
             Skidor
             Pulka
             Pimpeltrustning
             Bastutält
             Snöskyffel
            """,
                "Förråd");
    }
}

