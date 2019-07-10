package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Collegamento;

public class ArtsmiaDAO {

	public List<Integer> mapObject( Map<Integer,ArtObject> idMap ) {
		
		final String sql = "SELECT * from objects";
		List <Integer>result= new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
if(idMap.get(res.getInt("object_id"))==null) {
				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				idMap.put(res.getInt("object_id"),artObj);}
result.add(res.getInt("object_id"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Collegamento> cercaCollegamenti(){
		List<Collegamento> result=new ArrayList<Collegamento>();
	
	final String sql="SELECT e1.object_id , e2.object_id ,COUNT(*) AS tot FROM  exhibition_objects AS e1 , exhibition_objects AS e2 WHERE e1.object_id < e2.object_id AND e1.exhibition_id = e2.exhibition_id GROUP BY e1.object_id , e2.object_id";
	try {
	
	Connection conn=DBConnect.getConnection();
	PreparedStatement st = conn.prepareStatement(sql);
	ResultSet rs= st.executeQuery();
	while(rs.next()) {
		
	
		result.add(new Collegamento(rs.getInt(1), rs.getInt(2),rs.getInt(3)));
		
	}
	conn.close();
	return result;
	} catch (SQLException e) {
	
		e.printStackTrace();
		return null;
	}
	
	
	
	
	
	
	
		
	}
	
}
