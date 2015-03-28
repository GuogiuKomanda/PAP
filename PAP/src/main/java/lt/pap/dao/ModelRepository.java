package lt.pap.dao;

import java.util.List;

import lt.pap.model.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findByModelGroupId(Long id);

}
