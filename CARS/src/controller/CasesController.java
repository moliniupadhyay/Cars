package controller;



import java.util.List;
import java.util.Scanner;

import dao.CrimeDao;
import entity.Cases;

/**
 * CasesController class provides methods to interact with cases in the Crime Analysis and Reporting System.
 */
public class CasesController implements CasesInterface {
    public CrimeDao crimeDao;
    public Scanner sc = new Scanner(System.in);

    /**
     * helps to create a new case .
     */
    public void createCase() {
        crimeDao = new CrimeDao();

        System.out.println("Enter Case ID: ");
        int caseId = sc.nextInt();

        System.out.println("Enter Case Description: ");
        sc.nextLine(); 
        String caseDescription = sc.nextLine();

        System.out.println("Enter Incident ID: ");
        int incidentId = sc.nextInt();

        Cases newCase = new Cases();
        newCase.setCaseId(caseId);
        newCase.setCaseDescription(caseDescription);
        newCase.setIncidentsid(incidentId);

        boolean success = false;

        try {
            success = crimeDao.createCase(newCase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            System.out.println("Case created successfully!");
        } else {
            System.out.println("Failed to create case. Please check the logs for details.");
        }
    }

    /**
     * Retrieves details of a specific case by taking caseId  as input.
     */
    public void getCaseDetails() {
        crimeDao = new CrimeDao();
        System.out.println("Enter Case ID: ");
        int caseId = sc.nextInt();
        sc.nextLine(); 

        Cases caseDetails = crimeDao.getCaseDetails(caseId);

        if (caseDetails != null) {
            System.out.println("Case Details:");
            System.out.println("Case ID: " + caseDetails.getCaseId());
            System.out.println("Case Description: " + caseDetails.getCaseDescription());
        } else {
            System.out.println("Case not found.");
        }
    }

    /**
     * Updates the details of a specific case by taking case.
     */
    public void updateCaseDetails() {
        Scanner sc = new Scanner(System.in);
        crimeDao = new CrimeDao();

        System.out.println("Enter Case ID to update: ");
        int caseId = sc.nextInt();

        System.out.println("Enter New Case Description: ");
        sc.nextLine();  
        String newCaseDescription = sc.nextLine();

        System.out.println("Enter New Incident ID: ");
        int newIncidentId = sc.nextInt();

        Cases updatedCase = new Cases();
        updatedCase.setCaseId(caseId);
        updatedCase.setCaseDescription(newCaseDescription);
        updatedCase.setIncidentsid(newIncidentId);

        boolean success = false;

        try {
            success = crimeDao.updateCaseDetails(updatedCase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            System.out.println("Case details updated successfully!");
        } else {
            System.out.println("Failed to update case details. Please check the logs for details.");
        }
    }

    /**
     * Retrieves a list of all cases .
     */
    public void getAllCases() {
        try {
            crimeDao = new CrimeDao();
            List<Cases> allCases = crimeDao.getAllCases();

            if (allCases != null && !allCases.isEmpty()) {
                System.out.println("List of all Cases:");

                for (Cases aCase : allCases) {
                    System.out.println("Case ID: " + aCase.getCaseId());
                    System.out.println("Case Description: " + aCase.getCaseDescription());
                    System.out.println("Incident ID: " + aCase.getIncidentsid());
                    System.out.println("--------------");
                }
            } else {
                System.out.println("No cases found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
