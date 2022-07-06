package webRTC.VideoCall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webRTC.VideoCall.entity.UserEntity;
import webRTC.VideoCall.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save (UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById (String id) {
        return userRepository.findById(id);
    }

    public UserEntity findByIdUser (int idUser) {
        return userRepository.findByIdUser(idUser);
    }

}
