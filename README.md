# SimpleWorkFlow
A very simple work flow engine, which contains only one core java file.

#### 简洁的设计思想
- 基于XML的工作流引擎，整个流程的所有分支走向均可通过XML一目了然地识别。
- 一个起始节点（START），若干分支路线（可自定义）。  
- 每个分支路线都是一个独立的流程处理器。
- 每个分支既可以配置若干子分支，也可以不配置子分支。
- 若该分支配置了若干子分支，则可在流程处理器（从这若干子分支中）选择下一个分支。
- 若该分支不指定子分支，则该分支为结束节点。

#### 术语对照表
- 起始节点      
START    

- 流程处理器  
businessProcessor
  
- 子分支  
nextWorks

#### 以【下单工作流】举例如何配置XML：
```
<bean id="placeAnOrderFlow" class="java.util.HashMap">
    <constructor-arg>
        <map>
            <!--流程起始节点-->
            <entry key="START">
                <bean class="com.lnwazg.workflow.engine.BusinessProcessorProxy">
                    <property name="nextWorks">
                        <map>
                            <entry key="CONTINUE" value="lockInventoryProcessorProxy"/>
                            <entry key="DIRECT_CREATE_ORDER" value="createOrderProcessorProxy"/>
                        </map>
                    </property>
                </bean>
            </entry>
            <!--锁库存 -->
            <entry key="lockInventoryProcessorProxy">
                <bean class="com.lnwazg.workflow.engine.BusinessProcessorProxy">
                    <property name="businessProcessor">
                        <ref bean="lockInventoryProcessor"/>
                    </property>
                    <property name="nextWorks">
                        <map>
                            <entry key="CONTINUE" value="decreaseInventoryProcessorProxy"/>
                        </map>
                    </property>
                </bean>
            </entry>
            <!--扣减库存-->
            <entry key="decreaseInventoryProcessorProxy">
                <bean class="com.lnwazg.workflow.engine.BusinessProcessorProxy">
                    <property name="businessProcessor">
                        <ref bean="decreaseInventoryProcessor"/>
                    </property>
                    <property name="nextWorks">
                        <map>
                            <entry key="CONTINUE" value="createOrderProcessorProxy"/>
                        </map>
                    </property>
                </bean>
            </entry>
            <!--创建订单-->
            <entry key="createOrderProcessorProxy">
                <bean class="com.lnwazg.workflow.engine.BusinessProcessorProxy">
                    <property name="businessProcessor">
                        <ref bean="createOrderProcessor"/>
                    </property>
                </bean>
            </entry>
        </map>
    </constructor-arg>
</bean>
```

#### XML配置规律   
- 流程起始节点，只定义nextWorks，不定义businessProcessor  
- 流程结束节点，只定义businessProcessor，不定义nextWorks  