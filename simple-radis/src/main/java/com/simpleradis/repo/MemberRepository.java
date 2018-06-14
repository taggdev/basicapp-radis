package com.simpleradis.repo;

import com.simpleradis.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface MemberRepository extends CrudRepository<Member, String> {}
/*public interface MemberRepository {
	void save(Member member);
	Member find(Long id);
	Map<Long, Member> findAll();
	void update(Member member);
	void delete(Long id);
}*/
