package com.lnwazg.workflow.processor;

import com.lnwazg.workflow.engine.BusinessProcessor;
import com.lnwazg.workflow.engine.WorkFlowContext;
import com.lnwazg.workflow.engine.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderProcessor implements BusinessProcessor {
    private Logger logger = LoggerFactory.getLogger(CreateOrderProcessor.class);

    @Override
    public void process(WorkFlowContext workFlowContext) throws BusinessException {
        logger.info("CreateOrderProcessor processing...");
        workFlowContext.setProcessResult("CONTINUE");
    }
}
