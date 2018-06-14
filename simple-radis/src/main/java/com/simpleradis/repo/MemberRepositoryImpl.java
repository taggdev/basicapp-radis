package com.simpleradis.repo;

/*@Component
public class MemberRepositoryImpl implements MemberRepository {


	private static final String KEY = "Member";
	 
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Member> hashOperations;

	@Autowired
	public MemberRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	
	
	@Override
	public void save(Member customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public Member find(Long id) {
		return hashOperations.get(KEY, id);
	}
 
	@Override
	public Map<Long, Member> findAll() {
		return hashOperations.entries(KEY);
	}
 
	@Override
	public void update(Member customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY, id);
	}

}*/
