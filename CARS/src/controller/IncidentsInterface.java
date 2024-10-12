package controller;



import exception.IncidentNumberNotFoundException;
import exception.InvalidDataException;

public interface IncidentsInterface {

	void createIncident() throws InvalidDataException;

	void updateIncidentStatus() throws IncidentNumberNotFoundException;

	void getIncidentsInDateRange();

	void searchIncidentsByType();

	void getAllIncidents();

}
