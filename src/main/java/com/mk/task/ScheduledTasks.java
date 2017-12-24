package com.mk.task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron="0/1 0/2 * * * ?") 
    public void executeFileDownLoadTask() {
        // 间隔2分钟,执行工单上传任务     
        Thread current = Thread.currentThread();  
        System.out.println("定时任务1:"+current.getId());
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
    }

    @Scheduled(cron="0/1 0/1 * * * ?") 
    public void executeUploadTask() {
        // 间隔1分钟,执行工单上传任务              
        Thread current = Thread.currentThread();  
        System.out.println("定时任务2:"+current.getId());
        logger.info("ScheduledTest.executeUploadTask 定时任务2:"+current.getId() + ",name:"+current.getName());
    }

    @Scheduled(cron="0/1 0 * * * ?") 
    public void executeUploadBackTask() {
        // 间隔3分钟,执行工单上传任务                          
        Thread current = Thread.currentThread();  
        System.out.println("定时任务3:"+current.getId());
        logger.info("ScheduledTest.executeUploadBackTask 定时任务3:"+current.getId()+ ",name:"+current.getName());
    }    
    
    @Scheduled(fixedRate=1000)  
    public void testTasks() {      
        logger.info("每20秒执行一次。开始……");  
        //statusTask.healthCheck();  
        logger.info("每20秒执行一次。结束。");  
    }    
    
    
    /*
     * // 每天早八点到晚八点，间隔2分钟执行任务
@Scheduled(cron="0 0/2 8-20 * * ?") 
// 每天早八点到晚八点，间隔3分钟执行任务
@Scheduled(cron="0 0/3 8-20 * * ?") 
// 每天早八点到晚八点，间隔1分钟执行任务
@Scheduled(cron="0 0/1 8-20 * * ?") 
     * */

}