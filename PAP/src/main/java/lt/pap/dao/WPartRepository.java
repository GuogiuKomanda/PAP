package lt.pap.dao;

import java.util.List;

import lt.pap.model.WPart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WPartRepository extends JpaRepository<WPart, Long>
{
//select c from Client c where (?1 is null or ?1='' or c.fileNo = ?1) and (?2 is null or ?2='' or c.firstname = ?2) and (?3 is null or ?3='' or c.lastname = ?3)
//    public List<WPart> findByM
}
