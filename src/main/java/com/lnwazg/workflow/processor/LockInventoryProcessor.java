package com.lnwazg.workflow.processor;

import com.lnwazg.workflow.engine.BusinessProcessor;
import com.lnwazg.workflow.engine.WorkFlowContext;
import com.lnwazg.workflow.engine.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LockInventoryProcessor implements BusinessProcessor {
    private Logger logger = LoggerFactory.getLogger(LockInventoryProcessor.class);

    @Override
    public void process(WorkFlowContext workFlowContext) throws BusinessException {
        logger.info("LockInventoryProcessor processing...");
        workFlowContext.setProcessResult("CONTINUE");
//        workFlowContext.setProcessResult("FAIL");
    }
}
