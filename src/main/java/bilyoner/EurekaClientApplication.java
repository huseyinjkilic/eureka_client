package bilyoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}

@RestController
class ServiceInstanceRestController {
	
	@Autowired
	private NumberRepository repository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{applicationName}/{Number}")
    public String insertSingleRecordBy(@PathVariable("Number") int number)  {
         {
        	 Number singleRecord = repository.findByNumber(number);
        	 if(singleRecord == null) {
        		repository.save(new Number(number, new Date()));
             	return "OK";	 
        	 } else {
        		 return "Already in database";
        	 }
         }
    }
    
    @RequestMapping("/{applicationName}/get/{Number}")
    public Number getSingleRecordByNumber(@PathVariable("Number") int number)  {
         {
     		//TODO: make something for null scenario..        	     		
    		Number singleRecord = repository.findByNumber(number);
    		System.out.println(singleRecord);
        	return singleRecord;
         }
    }
    
    @RequestMapping("/{applicationName}/getall/desc")
    public List<Number> getAllRecordByOrderByDesc()  {
         {
        	 return repository.findAll(new Sort(Sort.Direction.DESC, "number"));
         }
    }
    
    @RequestMapping(value = {"/{applicationName}/getall/asc", "/{applicationName}/getall"})
    public List<Number> getAllRecordByOrderByAsc()  {
         {
        	 return repository.findAll(new Sort(Sort.Direction.ASC, "number"));
         }
    }
    
    @RequestMapping("/{applicationName}/maximum")
    public Number getMaximumRecord()  {
         {
        	 return repository.findFirstByOrderByNumberDesc();	
         }
    }
    
    @RequestMapping("/{applicationName}/minimum")
    public Number getMinimumRecord()  {
         {
        	 return repository.findFirstByOrderByNumberAsc();
         }
    }
    
    @RequestMapping("/{applicationName}/delete/{number}")
    public String deleteSingleRecordByNumber(@PathVariable("number") int number) {
         {
 			int status = repository.deleteByNumber(number);
 			
 			if(status == 1 ) {
 				System.out.println("Record " + number +" has been successfully deleted");
 				return "Record " + number +" has been successfully deleted";
 			} else {
 				System.out.println("Record " + number + " not found");
 				return "Record " + number + " not found";
 			}
         }
    }
    
    @RequestMapping("/{applicationName}/deleteAll")
    public void deleteAllRecordByNumber() {
 			repository.deleteAll();
    }
    
    @RequestMapping("/{applicationName}/eurekaServiceInstanceInformation")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
