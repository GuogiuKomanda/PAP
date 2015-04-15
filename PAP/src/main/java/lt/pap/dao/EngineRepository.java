package lt.pap.dao;

import lt.pap.model.Engine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    public Engine findByName(String name);//sita
    
    public Engine findByCode(String code);//done
    
    public Engine findByNameAndCode(String name, String code);//turetu veikt ir toks

}