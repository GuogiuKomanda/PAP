package lt.pap.dao;

import java.util.List;

import lt.pap.model.ModelGroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelGroupRepository extends JpaRepository<ModelGroup, Long> {

    List<ModelGroup> findByMakeId(Long id);
    
}
