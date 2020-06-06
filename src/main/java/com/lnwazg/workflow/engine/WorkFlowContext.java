package com.lnwazg.workflow.engine;

import com.lnwazg.workflow.engine.util.WorkFlowStatusConstants;

/**
 * 工作流上下文
 */
public class WorkFlowContext {
    /**
     * 工作流的bean id
     */
    private String workFlowId;
    /**
     * 当前工作状态，默认是START
     */
    private String currentWork = WorkFlowStatusConstants.PROCESS_START;
    /**
     * 处理结果，默认是CONTINUE
     */
    private String processResult = WorkFlowStatusConstants.PROCESS_CONTINUE;

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(String currentWork) {
        this.currentWork = currentWork;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }
}
