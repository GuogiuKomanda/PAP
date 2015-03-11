package lt.pap.dao;

import lt.pap.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Long countByName(String name);

    //User findByName(String name, String lastName);

}