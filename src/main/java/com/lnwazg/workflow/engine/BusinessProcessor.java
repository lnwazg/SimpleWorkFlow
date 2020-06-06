package com.lnwazg.workflow.engine;

import com.lnwazg.workflow.engine.exception.BusinessException;

/**
 * 业务处理器
 */
public interface BusinessProcessor {
    /**
     * 处理业务
     *
     * @param txnContext 交易上下文
     * @throws BusinessException
     */
    public void process(WorkFlowContext txnContext) throws BusinessException;
}
