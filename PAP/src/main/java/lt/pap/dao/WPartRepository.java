package lt.pap.dao;

import java.util.List;

import lt.pap.model.WPart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WPartRepository extends JpaRepository<WPart, Long>
{
//select c from Client c where (?1 is null or ?1='' or c.fileNo = ?1) and (?2 is null or ?2='' or c.firstname = ?2) and (?3 is null or ?3='' or c.lastname = ?3)
//    public List<WPart> findByM
	
	@Query("SELECT wp FROM WPart wp WHERE (?1 is null or ?1='' or wp.modelEngine.model.modelGroup.make.id = ?1)")
    public List<WPart> findByFilters( String makeId);
	
	
	/*
	@NamedQuery(name = "EventLog.viewDatesInclude", 
		    query = "SELECT el FROM EventLog el WHERE el.timeMark >= :dateFrom AND " 
		    + "el.timeMark <= :dateTo AND " 
		    + "el.name IN :inclList") */
}
