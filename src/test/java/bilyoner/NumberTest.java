package bilyoner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberTest {

	
    @Autowired
    NumberRepository repository;

    Number one, two, eleven, twenty, oneHundred, oneHundredThirteen, twoHundred;

    @Before
    public void setUp() {
        
    	repository.deleteAll();
        
        one = repository.save(new Number(1, new Date()));
		two = repository.save(new Number(2, new Date()));
		eleven = repository.save(new Number(11, new Date()));
		twenty = repository.save(new Number(20, new Date()));
		oneHundred = repository.save(new Number(100, new Date()));
		oneHundredThirteen = repository.save(new Number(113, new Date()));
		twoHundred = repository.save(new Number(200, new Date()));
    }

    @Test
    public void checkMinimumValueForCorrectCase() {
    	Number minimum = repository.findFirstByOrderByNumberAsc();
        assertThat(minimum.getNumber() == 100).isFalse();
    }

    @Test
    public void checkMinimumValueForWrongCase() {
    	Number minimum = repository.findFirstByOrderByNumberAsc();
        assertThat(minimum.getNumber() == 100).isFalse();
    }
    
    @Test
    public void checkMaximumValueForCorrectCase() {
    	Number minimum = repository.findFirstByOrderByNumberDesc();
        assertThat(minimum.getNumber() == 200).isTrue();
    }
 

    @Test
    public void checkMaximumValueForWrongCase() {
    	Number minimum = repository.findFirstByOrderByNumberDesc();
    	assertThat(minimum.getNumber() == 1).isFalse();
    }
    
    @Test
    public void checkNumberValueForCorrectCase() {
    	Number singleRecord = repository.findByNumber(11);
    	assertThat(singleRecord).isNotNull();
    }
    
    @Test
    public void checkNumberValueForWrongCase() {    	
    	Number singleRecord = repository.findByNumber(1761);
    	assertThat(singleRecord).isNull();
    }
    
    @Test
    public void checDescOrderForCorrectCase() {
    	List<Number> desSortedList = repository.findAll(new Sort(Sort.Direction.DESC, "number"));
        assertThat(desSortedList.get(0).number == 200).isTrue();
    }
    
    @Test
    public void checDescOrderForWrongCase() {    	
    	List<Number> desSortedList = repository.findAll(new Sort(Sort.Direction.DESC, "number"));
        assertThat(desSortedList.get(0).number == 3).isFalse();
    }
    
    @Test
    public void checAscOrderForCorrectCase() {
    	List<Number> desSortedList = repository.findAll(new Sort(Sort.Direction.ASC, "number"));
        assertThat(desSortedList.get(0).number == 1).isTrue();
    }
    
    @Test
    public void checAscOrderForWrongCase() {    	
    	List<Number> desSortedList = repository.findAll(new Sort(Sort.Direction.ASC, "number"));
        assertThat(desSortedList.get(0).number == 3).isFalse();
    }
    
    @After
    public void cleanUp() {
    	repository.deleteAll();
    }
    
}