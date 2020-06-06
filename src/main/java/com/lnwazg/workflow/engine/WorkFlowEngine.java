package com.lnwazg.workflow.engine;

import com.lnwazg.workflow.engine.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作流引擎
 */
public class WorkFlowEngine {
    private Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);

    private static ThreadLocal<WorkFlowContext> txnContextThreadLocal = new ThreadLocal<WorkFlowContext>();

    /**
     * 工作流集合，beanId-->Map<处理器名称-->处理器对象>
     */
    private Map<String, Map<String, BusinessProcessorProxy>> workFlowRegistry = new HashMap<String, Map<String, BusinessProcessorProxy>>();

    public WorkFlowEngine(Map<String, Map<String, BusinessProcessorProxy>> workFlowRegistry) {
        this.workFlowRegistry = workFlowRegistry;
    }

    /**
     * 执行业务工作流
     */
    public void processWork() {
        WorkFlowContext txnContext = txnContextThreadLocal.get();
        if (txnContext == null) {
            logger.error("txnContext is null!");
            return;
        }
        try {
            String nextWorkValue = null;
            BusinessProcessorProxy nextBusinessProcessorProxy = null;
            // 获取渠道类型的工作流
            Map<String, BusinessProcessorProxy> workFlow = workFlowRegistry.get(txnContext.getWorkFlowId());
            if (workFlow == null) {
                // 没有配置工作流，一般不可能，所以是严重异常
                throw new BusinessException("workflow not found, bean id=" + txnContext.getWorkFlowId());
            }
            BusinessProcessorProxy businessProcessorProxy = workFlow.get(txnContext.getCurrentWork());//START
            if (txnContext.getProcessResult() != null) {
                nextWorkValue = businessProcessorProxy.getNextWorks().get(txnContext.getProcessResult());//checkCanRollback
                nextBusinessProcessorProxy = workFlow.get(nextWorkValue);
            }
            // 结束
            if (nextBusinessProcessorProxy == null) {
                return;
            }
            txnContext.setProcessResult(null);
            txnContext.setCurrentWork(nextWorkValue);
            logger.debug("Process work ,currentWork=" + nextWorkValue);
            nextBusinessProcessorProxy.process(txnContext);
        } catch (Exception e) {
            // 系统异常
            logger.error(e.getMessage(), e);
            return;
        }
        // 处理下一流程
        processWork();
    }

    /**
     * 设置当前线程的TxnContext对象
     * @param txnContext
     */
    public static void setThreadLocalTxnContext(WorkFlowContext txnContext) {
        txnContextThreadLocal.set(txnContext);
    }
}
