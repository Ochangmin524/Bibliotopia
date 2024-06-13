package Booktopia.Bibliotopia.repository;

import Booktopia.Bibliotopia.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
