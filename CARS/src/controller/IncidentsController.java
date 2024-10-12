package controller;



import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.CrimeDao;
import entity.Incidents;
import exception.IncidentNumberNotFoundException;
import exception.InvalidDataException;

/**
 * IncidentsController class provides methods to interact with incidents in the Crime Analysis and Reporting System.
 */
public class IncidentsController implements IncidentsInterface {
    public CrimeDao crimeDao;
    public Scanner sc = new Scanner(System.in);

    /**
     *
     *Helps to  creates a new incident .
     */
    public void createIncident() {
        try {
            crimeDao = new CrimeDao();
            Incidents incident = new Incidents();
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter IncidentID: ");
            int incidentId = sc.nextInt();
            sc.nextLine(); 
            incident.setIncidentID(incidentId);

            System.out.println("Enter Incident Type: ");
            String incidentType = sc.nextLine();
            incident.setIncidentType(incidentType);

            System.out.println("Enter Incident Date (yyyy-MM-dd): ");
            String incidentDateStr = sc.next();
            Date incidentDate = parseDate(incidentDateStr);
            incident.setIncidentDate(incidentDate);
            sc.nextLine();

            System.out.println("Enter Location: ");
            String location = sc.nextLine();
            incident.setLocation(location);

            System.out.println("Enter Description: ");
            String description = sc.nextLine();
            incident.setDescription(description);

            System.out.println("Enter Status: ");
            String status = sc.nextLine();
            incident.setStatus(status);

            System.out.println("Enter Victim ID: ");
            int victimID = sc.nextInt();
            incident.setVictimID(victimID);

            System.out.println("Enter Suspect ID: ");
            int suspectID = sc.nextInt();
            incident.setSuspectID(suspectID);

            if (crimeDao.createIncident(incident)) {
                System.out.println("Incident added successfully !!!");
            } else {
                System.out.println("Failed to add incident. Please check the logs for details.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse(dateStr);
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * Updates the status of an incident.
     *
     * @throws IncidentNumberNotFoundException if the incident with the specified ID is not found.
     */
    public void updateIncidentStatus() throws IncidentNumberNotFoundException {
        try {
            crimeDao = new CrimeDao();
            System.out.println("Enter Incident ID: ");
            int incidentId = sc.nextInt();

            System.out.println("Enter New Status: ");
            String newStatus = sc.next();

            if (crimeDao.updateIncidentStatus(incidentId, newStatus)) {
                System.out.println("Incident status updated successfully!");
            } else {
                throw new IncidentNumberNotFoundException();
            }
        } catch (IncidentNumberNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves a list of incidents within a specified date range.
     */
    public void getIncidentsInDateRange() {
        try {
            crimeDao = new CrimeDao();
            System.out.println("Enter Start Date (yyyy-MM-dd): ");
            String startDateStr = sc.next();

            System.out.println("Enter End Date (yyyy-MM-dd): ");
            String endDateStr = sc.next();

            List<Incidents> incidentsList = crimeDao.getIncidentsInDateRange(startDateStr, endDateStr);

            if (incidentsList.isEmpty()) {
                System.out.println("No incidents found within the specified date range.");
            } else {
                System.out.println("Incidents within the date range:");
                for (Incidents incident : incidentsList) {
                    System.out.println(incident);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Searches for incidents based on the specified type.
     */
    public void searchIncidentsByType() {
        crimeDao = new CrimeDao();
        System.out.println("Enter Incident Type to search: ");
        String incidentType = sc.next();

        List<Incidents> incidentsList = crimeDao.searchIncidentsByType(incidentType);

        if (incidentsList.isEmpty()) {
            System.out.println("No incidents found for the specified type.");
        } else {
            System.out.println("Incidents with the specified type:");
            for (Incidents incident : incidentsList) {
                System.out.println(incident);
            }
        }
    }

    /**
     * Retrieves a list of all incidents in the system.
     */
    public void getAllIncidents() {
        crimeDao = new CrimeDao();
        List<Incidents> incidentsList = crimeDao.getAllIncidents();

        if (incidentsList.isEmpty()) {
            System.out.println("No incidents found.");
        } else {
            System.out.println("All Incidents:");
            for (Incidents incident : incidentsList) {
                System.out.println(incident);
            }
        }
    }
}