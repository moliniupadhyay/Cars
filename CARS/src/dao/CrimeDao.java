package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import entity.*;
import util.MyDBConnection;
/**
 * CrimeDao class provides data access methods for the Crime Analysis and Reporting System.
 */
public class CrimeDao implements ICrimeAnalysisService {
	Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet;

    /**
     * 
     *  it creates a new incident 
     *  @param the input is input object
     *  @return boolean value 
     *       * */
    @Override
    public boolean createIncident(Incidents incident) {
    	try  {
    		 connection = MyDBConnection.getMyDbConnection();

            String sql = "INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Location, Descriptions, Statuss, VictimID, SuspectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, incident.getIncidentID());
                preparedStatement.setString(2, incident.getIncidentType());
                preparedStatement.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
                preparedStatement.setString(4, incident.getLocation());
                preparedStatement.setString(5, incident.getDescription());
                preparedStatement.setString(6, incident.getStatus());
                preparedStatement.setInt(7, incident.getVictimID());
                preparedStatement.setInt(8, incident.getSuspectID());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
    /**
     * 
     *  it creates a updates a incident by taking incident id as input and changes the status of incident
     *  @param the input is input object and status string
     *  @return boolean value
     * */
    @Override
    public boolean updateIncidentStatus(int incidentId,String status) {
    	try {
            connection = MyDBConnection.getMyDbConnection();

            String sql = "UPDATE Incidents SET Statuss = ? WHERE IncidentID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, incidentId);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 
     *  it stores all  incidents in a list which are in the given date range
     *  @param the input is input starting date and ending date 
     *  @return list of incidents
     * */
    @Override
    
    public List<Incidents> getIncidentsInDateRange(String startDateStr, String endDateStr) {
        List<Incidents> incidentsList = new ArrayList<>();
        try {
            connection = MyDBConnection.getMyDbConnection();
            String sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = new java.sql.Date(dateFormat.parse(startDateStr).getTime());
                Date endDate = new java.sql.Date(dateFormat.parse(endDateStr).getTime());
                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Incidents incident = new Incidents();
                        incident.setIncidentID(resultSet.getInt("incidentID"));
                        incident.setIncidentType(resultSet.getString("incidentType"));
                        incident.setIncidentDate(resultSet.getDate("incidentDate"));
                        incident.setLocation(resultSet.getString("location"));
                        incident.setDescription(resultSet.getString("descriptions"));
                        incident.setStatus(resultSet.getString("statuss"));
                        incident.setVictimID(resultSet.getInt("victimID"));
                        incident.setSuspectID(resultSet.getInt("suspectID"));
                        incidentsList.add(incident);
                    }
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incidentsList;
    }
    /**
     * 
     *  it creates a list of incident based on incident type
     *  @param the input is type of incident
     *  @return list of incidents
     *  
     * */

    
    public List<Incidents> searchIncidentsByType(String incidentType) {
        List<Incidents> incidentsList = new ArrayList<>();

        try {
            connection = MyDBConnection.getMyDbConnection();

            String sql = "SELECT * FROM Incidents WHERE IncidentType = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, incidentType);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Incidents incident = new Incidents();
                        
               
                        incident.setIncidentID(resultSet.getInt("IncidentID"));
                        incident.setIncidentType(resultSet.getString("IncidentType"));
                        incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                        incident.setLocation(resultSet.getString("Location"));
                        incident.setDescription(resultSet.getString("Descriptions"));
                        incident.setStatus(resultSet.getString("Statuss"));
                        incident.setVictimID(resultSet.getInt("VictimID"));
                        incident.setSuspectID(resultSet.getInt("SuspectID"));

                        incidentsList.add(incident);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return incidentsList;
    }

    /**
     * 
     *  it creates a list of  incident which are present in incident table
     *  
     * */
    

    @Override
    public List<Incidents> getAllIncidents() {
        List<Incidents> incidentsList = new ArrayList<>();

        try {
            connection = MyDBConnection.getMyDbConnection();

            String sql = "SELECT * FROM Incidents";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Incidents incident = new Incidents();
                    
                 
                    incident.setIncidentID(resultSet.getInt("IncidentID"));
                    incident.setIncidentType(resultSet.getString("IncidentType"));
                    incident.setIncidentDate(resultSet.getDate("IncidentDate"));
                    incident.setLocation(resultSet.getString("Location"));
                    incident.setDescription(resultSet.getString("Descriptions"));
                    incident.setStatus(resultSet.getString("Statuss"));
                    incident.setVictimID(resultSet.getInt("VictimID"));
                    incident.setSuspectID(resultSet.getInt("SuspectID"));

                    incidentsList.add(incident);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return incidentsList;
    }
    /**
     * 
     *  it creates a new case 
     *  
     * */

    @Override
    public boolean createCase(Cases newCase) {
        boolean success = false;

        try {
            connection = MyDBConnection.getMyDbConnection();

            String insertCaseSQL = "INSERT INTO Cases (caseID, caseDescription, incidentID) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertCaseSQL)) {
                preparedStatement.setInt(1, newCase.getCaseId());
                preparedStatement.setString(2, newCase.getCaseDescription());
                preparedStatement.setInt(3, newCase.getIncidentsid());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    
                    success = true;
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
    /**
     * 
     *  it creates displays case based on caseid
     *  
     * */

    @Override
    public Cases getCaseDetails(int caseId) {
        Cases caseDetails = null;

        try {
            connection = MyDBConnection.getMyDbConnection();

            String selectCaseSQL = "SELECT * FROM Cases WHERE caseID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectCaseSQL)) {
                preparedStatement.setInt(1, caseId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        caseDetails = new Cases();
                        caseDetails.setCaseId(resultSet.getInt("caseID"));
                        caseDetails.setCaseDescription(resultSet.getString("caseDescription"));
                        
                    }
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return caseDetails;
    }
    /**
     * 
     *  it updates the cases based on  cases id 
     *  
     * */

    @Override
    public boolean updateCaseDetails(Cases updatedCase) {
        try {
            connection = MyDBConnection.getMyDbConnection();

            String updateCaseSQL = "UPDATE Cases SET caseDescription = ?, incidentID = ? WHERE caseID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateCaseSQL)) {
                preparedStatement.setString(1, updatedCase.getCaseDescription());
                preparedStatement.setInt(2, updatedCase.getIncidentsid());
                preparedStatement.setInt(3, updatedCase.getCaseId());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                
                e.printStackTrace(); 
            }
        }
    }
    /**
     * 
     *  it creates a list of all cases  present in the table 
     *  
     * */

    @Override
    public List<Cases> getAllCases() {
        List<Cases> casesList = new ArrayList<>();

        try {
            connection = MyDBConnection.getMyDbConnection();

            String getAllCasesSQL = "SELECT * FROM Cases";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(getAllCasesSQL)) {

                while (resultSet.next()) {
                    Cases caseObj = new Cases();
                    caseObj.setCaseId(resultSet.getInt("caseID"));
                    caseObj.setCaseDescription(resultSet.getString("caseDescription"));
                    caseObj.setIncidentsid(resultSet.getInt("incidentID")); // Use the correct column name

                    casesList.add(caseObj);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace(); 
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                
                e.printStackTrace(); 
            }
        }

        return casesList;
    }

}
