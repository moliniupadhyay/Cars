package dao;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import entity.*;


public interface ICrimeAnalysisService {

	boolean createIncident(Incidents incident);

	boolean updateIncidentStatus(int incidentId, String status);

	List<Incidents> getIncidentsInDateRange(String startDate2, String endDate2);


    List<Incidents> searchIncidentsByType(String incidentType);

    List<Incidents> getAllIncidents();  
    
    boolean createCase(Cases newCase);

    Cases getCaseDetails(int caseId);

    boolean updateCaseDetails(Cases updatedCase);

    List<Cases> getAllCases();

	
	
	


}
