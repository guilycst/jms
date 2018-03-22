#Priority queues
- Messages have priority fom 0 to 9, 9 being the most priority
- on activeMQ you must enable it through a policy on installation-path/conf/activemq.xml
- <policyEntry queue=">" prioritizedMessages="true"/>
- messages can be selected using priority property "session.createConsumer(fila, "JMSPriority > 6" )"
