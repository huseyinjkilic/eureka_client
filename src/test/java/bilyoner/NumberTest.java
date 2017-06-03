package bilyoner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.After;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberTest {

	private MockMvc mockMvc;
	
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
    public void checkMinimumValueCorrectScenario() {
    	Number minimum = repository.findFirstByOrderByNumberAsc();
        assertThat(minimum.getNumber() == 100).isFalse();
    }

    @Test
    public void checkMinimumValueWrongScenario() {
    	Number minimum = repository.findFirstByOrderByNumberAsc();
        assertThat(minimum.getNumber() == 100).isFalse();
    }
    
    @Test
    public void checkMaximumValueCorrectScenario() {
    	Number minimum = repository.findFirstByOrderByNumberDesc();
        assertThat(minimum.getNumber() == 200).isTrue();
    }
 

    @Test
    public void checkMaximumValueWrongScenario() {
    	Number minimum = repository.findFirstByOrderByNumberDesc();
    	assertThat(minimum.getNumber() == 1).isFalse();
    }
    
    @Test
    public void checkNumberValueCorrectScenario() {
    	Number singleRecord = repository.findByNumber(11);
    	assertThat(singleRecord).isNotNull();
    }
    
    @Test
    public void checkNumberValueWrongScenario() {    	
    	Number singleRecord = repository.findByNumber(1761);
    	assertThat(singleRecord).isNull();
    }
    
    @After
    public void cleanUp() {
        
    	repository.deleteAll();
    }
    
}