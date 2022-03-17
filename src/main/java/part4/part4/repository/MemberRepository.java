package part4.part4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import part4.part4.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {


}
