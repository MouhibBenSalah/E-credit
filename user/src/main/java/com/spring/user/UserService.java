package com.spring.user;


import com.spring.user.client.DemandeCreditClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private  UserRepository userRepository;
    private DemandeCreditClient client;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public FullUserResponse findUserbyDemandeCredit(Integer id) {
        var user = userRepository.findById(id).orElse(
                User.builder().nom("not found").prenom("not found").build()
        );
        var demandesCredit= client.findAllDemandesCreditByUser(id);
        return FullUserResponse.builder()
                .nom(user.getNom())
                .email(user.getEmail())
                .demandesCredit(demandesCredit)
                .build();
    }
}
