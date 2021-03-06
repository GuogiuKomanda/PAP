package lt.pap.service;

import java.util.List;

import lt.pap.dao.ModelRepository;
import lt.pap.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModelService {
	@Autowired
	private ModelRepository modelRepository;

	public List<Model> findAll() {
		return modelRepository.findAll();
	}

	public Model findOne(Long id) {
		return modelRepository.findOne(id);
	}

	public Model save(Model model) {
		return modelRepository.save(model);
	}

	public List<Model> findByModelGroupId(Long modelGroupId) {
		return modelRepository.findByModelGroupId(modelGroupId);
	}

	public List<Model> findByModelGroupMakeId(Long makeId) {
		return modelRepository.findByModelGroupMakeId(makeId);
	}

}
