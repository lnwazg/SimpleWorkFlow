package com.lnwazg.workflow;

import com.lnwazg.workflow.engine.BusinessProcessorProxy;
import com.lnwazg.workflow.engine.WorkFlowContext;
import com.lnwazg.workflow.engine.WorkFlowEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        //获取引擎实例
        WorkFlowEngine workFlowEngine = (WorkFlowEngine) context.getBean("workFlowEngine");
        //往引擎中注册工作流
        workFlowEngine.register("placeAnOrderFlow", (Map<String, BusinessProcessorProxy>) context.getBean("placeAnOrderFlow"));

        //初始化一个工作流的上下文对象
        WorkFlowContext workFlowContext = new WorkFlowContext();
        //给该上下文对象指定要处理的目标工作流Id
        workFlowContext.setWorkFlowId("placeAnOrderFlow");
        //给上下文对象注册到引擎
        workFlowEngine.setThreadLocalTxnContext(workFlowContext);
        //执行引擎
        workFlowEngine.processWork();
    }
}
