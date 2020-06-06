package com.lnwazg.workflow.engine;

import com.lnwazg.workflow.engine.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理代理类
 */
public class BusinessProcessorProxy {
    /**
     * 业务处理器
     */
    private BusinessProcessor businessProcessor;

    /**
     * 下一个工作任务集合
     */
    private Map<String, String> nextWorks = new HashMap<String, String>();

    /**
     * 执行实际业务逻辑
     *
     * @param txnContext 交易上下文
     * @throws BusinessException
     */
    public void process(WorkFlowContext txnContext) throws BusinessException {
        if (businessProcessor != null) {
            businessProcessor.process(txnContext);
        }
    }

    public BusinessProcessor getBusinessProcessor() {
        return businessProcessor;
    }

    public void setBusinessProcessor(BusinessProcessor businessProcessor) {
        this.businessProcessor = businessProcessor;
    }

    public Map<String, String> getNextWorks() {
        return nextWorks;
    }

    public void setNextWorks(Map<String, String> nextWorks) {
        this.nextWorks = nextWorks;
    }
}
