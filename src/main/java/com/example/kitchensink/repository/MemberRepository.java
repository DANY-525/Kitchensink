package com.example.kitchensink.repository;
import com.example.kitchensink.entities.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member,String> {
}
