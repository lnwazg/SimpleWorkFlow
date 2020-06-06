# SimpleWorkFlow
A very simple work flow engine, which contains only one core java file.

流程起始节点，只定义nextWorks，不定义businessProcessor
流程结束节点，只定义businessProcessor，不定义nextWorks

以下单为例：
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