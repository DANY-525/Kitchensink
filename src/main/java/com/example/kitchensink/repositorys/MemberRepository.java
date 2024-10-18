package com.example.kitchensink.repositorys;
import com.example.kitchensink.entities.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member,String> {
    Optional<Member> findByEmail(String email);

}
