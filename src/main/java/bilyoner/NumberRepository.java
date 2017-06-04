package bilyoner;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumberRepository extends MongoRepository<Number, Integer> {
    
	public Number findByNumber(int number);
    public List<Number> findAll();
    public Number findFirstByOrderByNumberAsc();
    public Number findFirstByOrderByNumberDesc();
    public Integer deleteByNumber(Integer number);
    List<Number> findAll(Sort sort);
}
