//package com.example.accessingdata;
//
//import com.example.accessingdata.Repository.PlayerRepository;
//import com.example.accessingdata.Service.PlayerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.ApplicationEventPublisher;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
//public class AccessingDataIntegrationTests {
//    PlayerRepository repository;
//    PlayerService service;
//
//    public AccessingDataIntegrationTests(PlayerRepository repository) {
//        this.repository = repository;
//    }
//
//    @BeforeEach
//    public void setup() {
//        repository.deleteAll();
//        service = new PlayerService(repository);
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateWithNoChange() {
//        //        call update user but don't change emails
//
//        service.addNewPlayer("player1", 10);
//        service.addNewPlayer("player2", 2);
//        service.updateUser("user1", "email1@email.com");
//        service.updateUser("user2", "email2@email.com");
//        ArrayList<User> correctUserList = new ArrayList<>();
//        User user1 = new User();
//        user1.setName("user1");
//        user1.setEmail("email1@email.com");
//        User user2 = new User();
//        user2.setName("user2");
//        user2.setEmail("email2@email.com");
//        correctUserList.add(user1);
//        correctUserList.add(user2);
//        ArrayList<UserEntity> correctUserEntityList = new ArrayList<>();
//        UserEntity ue1 = mapper.toEntity(user1);
//        UserEntity ue2 = mapper.toEntity(user2);
//        correctUserEntityList.add(ue1);
//        correctUserEntityList.add(ue2);
//
//        ArrayList<User> getList = service.getUser();
//        assertEquals(getList.size(), 2); //two users added to repository
//        for (int i = 0; i < getList.size(); i++) {
//            assertEquals(getList.get(i).getEmail(), correctUserList.get(i).getEmail());
//            assertEquals(getList.get(i).getName(), correctUserList.get(i).getName());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateWithChange() {
//        service.addNewPlayer("user1", "email1@email.com");
//        service.addNewPlayer("user2", "email2@email.com");
//        service.updateUser("user1", "newemail1@email.com");
//        service.updateUser("user2", "newemail2@email.com");
//        ArrayList<User> correctUserList = new ArrayList<>();
//        User user1 = new User();
//        user1.setName("user1");
//        user1.setEmail("newemail1@email.com");
//        User user2 = new User();
//        user2.setName("user2");
//        user2.setEmail("newemail2@email.com");
//        correctUserList.add(user1);
//        correctUserList.add(user2);
//        ArrayList<UserEntity> correctUserEntityList = new ArrayList<>();
//        UserEntity ue1 = mapper.toEntity(user1);
//        UserEntity ue2 = mapper.toEntity(user2);
//        correctUserEntityList.add(ue1);
//        correctUserEntityList.add(ue2);
//
//        ArrayList<User> getList = service.getUser();
//        assertEquals(getList.size(), 2); //two users added to repository
//        for (int i = 0; i < getList.size(); i++) {
//            assertEquals(getList.get(i).getEmail(), correctUserList.get(i).getEmail());
//            assertEquals(getList.get(i).getName(), correctUserList.get(i).getName());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateNotInDatabase() {
//        service.updateUser("user1", "newemail1@email.com");
//        service.updateUser("user2", "newemail2@email.com");
//        ArrayList<User> correctUserList = new ArrayList<>();
//        User user1 = new User();
//        user1.setName("user1");
//        user1.setEmail("newemail1@email.com");
//        User user2 = new User();
//        user2.setName("user2");
//        user2.setEmail("newemail2@email.com");
//        correctUserList.add(user1);
//        correctUserList.add(user2);
//        ArrayList<UserEntity> correctUserEntityList = new ArrayList<>();
//        UserEntity ue1 = mapper.toEntity(user1);
//        UserEntity ue2 = mapper.toEntity(user2);
//        correctUserEntityList.add(ue1);
//        correctUserEntityList.add(ue2);
//
//        ArrayList<User> getList = service.getUser();
//        assertEquals(getList.size(), 2); //two users added to repository
//        for (int i = 0; i < getList.size(); i++) {
//            assertEquals(getList.get(i).getEmail(), correctUserList.get(i).getEmail());
//            assertEquals(getList.get(i).getName(), correctUserList.get(i).getName());
//        }
//    }
//}
//
