package scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
/*
 * ServletContextListener
 * 
 * 1. 웹 어플리케이션(프로젝트)의 LifeCycle을 가진다. (웹 어플리케이션과 함께 동작한다)
 * 2. contextInitialized() : 웹 어플리케이션 시작시 동작
 * 3. contextDestroyed()   : 웹 어플리케이션 종료시 동작
 */
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class StudentListener implements ServletContextListener {
	
	private Scheduler scheduler;
	
    // Scheduler scheduler 생성
    public StudentListener() {
        SchedulerFactory factory = null;
        try {
        	factory = new StdSchedulerFactory();
        	scheduler = factory.getScheduler();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
        	 if(scheduler != null) {
        		 scheduler.shutdown();
        	 }
         } catch(Exception e) {
        	 e.printStackTrace();
         }
    }

    // Job, Trigger 생성
    // scheduler에 Job, Trigger 등록
    // scheduler 시작
    public void contextInitialized(ServletContextEvent arg0)  { 
        /*
         * Cron expression (크론식)
         * 	1. 구성
         * 		초 분 시 일 월 요일 [년도]
         * 	2. 상세
         * 		1) 초 : 0 ~ 59
         * 		2) 분 : 0 ~ 59
         * 		3) 시 : 0 ~ 23
         * 		4) 일 : 1 ~ 30
         * 		5) 월 : 0 ~ 11 또는 JAN FEB MAR APR ...
         * 		6) 요일 : 1(MON) ~ 7(SUN) 또는 MON, TUE, WED ...
         * 	3. 작성
         * 		1) * : 매번
         *  	2) ? : 설정 없음
         *    	3) / : 주기
         *    			A/B : A부터 B마다 동작
         *   4. 작성 예시
         *   	1) 매일 10초마다   : 0/10 * * * * ?
         *   	2) 매일 1분마다    : 0 0/1 * * * ?
         *   	3) 금요일 12시마다 : 0 0 12 ? * FRI
         *   	4) 주말 12시마다   : 0 0 12 ? * SAT,SUN 
         */
    	
    	
    	try {
    		
    		// Job 생성
    		JobDetail job = JobBuilder.newJob(StudentTop3job.class)
    				.withIdentity("job1", "group1")
    				.build();
    		
    		// Trigger 생성
    		
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger1", "group1")
    				.startNow()
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?"))
    				.build();
    		
    		// scheduler에 Job, Trigger 등록
    		scheduler.scheduleJob(job, trigger);
    		
    		// scheduler 실행
    		scheduler.start();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }
	
}
