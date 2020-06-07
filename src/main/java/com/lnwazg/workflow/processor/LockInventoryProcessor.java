package com.lnwazg.workflow.processor;

import com.lnwazg.workflow.engine.BusinessProcessor;
import com.lnwazg.workflow.engine.WorkFlowContext;
import com.lnwazg.workflow.engine.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LockInventoryProcessor implements BusinessProcessor {
    private Logger logger = LoggerFactory.getLogger(CreateOrderProcessor.class);

    @Override
    public void process(WorkFlowContext txnContext) throws BusinessException {
        logger.info("LockInventoryProcessor processing...");
        txnContext.setProcessResult("CONTINUE");
//        txnContext.setProcessResult("FAIL");
    }
}
