package lt.pap.service;

import java.util.List;

import lt.pap.dao.UserRepository;
import lt.pap.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findOne(long id) {
    return userRepository.findOne(id);
  }

  public long count() {
    return userRepository.count();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public boolean exists(Long userId) {
    return userRepository.exists(userId);
  }

  public long countByName(String name) {
    return userRepository.countByName(name);
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }
}
