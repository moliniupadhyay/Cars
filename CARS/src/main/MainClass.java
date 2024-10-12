package main;


import java.util.Scanner;

import controller.*;
import exception.AuthorizationException;
import exception.IncidentNumberNotFoundException;
import exception.InvalidDataException;


public class MainClass {

   
    public static void main(String[] args) {
        System.out.println("Welcome to Crime Analysis and Reporting System ");

        IncidentsInterface incidentsInterface = new IncidentsController();
        CasesInterface casesInterface = new CasesController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username: ");
        String unm = sc.next();

        System.out.println("Enter password: ");
        String pas = sc.next();
        String ch;

        try {
            if (unm.equals("crime") && pas.equals("crime123")) {
                do {
                    System.out.println("Enter your choice");
                    System.out.println("1. Create Incident");
                    System.out.println("2. Update the status of an incident");
                    System.out.println("3. Get a list of incidents within a date range");
                    System.out.println("4. Search for incidents based on various criteria");
                    System.out.println("5. Get all  incident ");
                    System.out.println("6. Create a new case and associate it with incidents");
                    System.out.println("7. Get details of a specific case");
                    System.out.println("8. Update case details ");
                    System.out.println("9. Get a list of all cases");

                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: {
                            try {
                                incidentsInterface.createIncident();
                            } catch (InvalidDataException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case 2: {
                            try {
                                incidentsInterface.updateIncidentStatus();
                            } catch (IncidentNumberNotFoundException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case 3: {
                            incidentsInterface.getIncidentsInDateRange();
                            break;
                        }
                        case 4: {
                            incidentsInterface.searchIncidentsByType();
                            break;
                        }
                        case 5: {
                            incidentsInterface.getAllIncidents();
                            break;
                        }
                        case 6: {
                            casesInterface.createCase();
                            break;
                        }
                        case 7: {
                            casesInterface.getCaseDetails();
                            break;
                        }
                        case 8: {
                            casesInterface.updateCaseDetails();
                            break;
                        }
                        case 9: {
                            casesInterface.getAllCases();
                            break;
                        }
                        default: {
                            System.out.println("Choose a proper choice");
                            break;
                        }
                    }

                    System.out.println("Do you want to continue? Y | y");
                    ch = sc.next();
                } while (ch.equals("Y") || ch.equals("y"));
            } else {
                throw new AuthorizationException();
            }
            System.out.println("Thank you for using the application");
        } catch (AuthorizationException e) {
            System.out.println(e);
        } finally {
            sc.close();
        }
    }
}