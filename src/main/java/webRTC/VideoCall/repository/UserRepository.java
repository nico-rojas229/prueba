package webRTC.VideoCall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webRTC.VideoCall.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByIdUser (int idUser);

}
